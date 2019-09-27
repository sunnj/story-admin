/*
 *  Copyright HNA©2017. All rights reserved.
 */
package com.story.storyadmin.config.upload.aop;

import java.lang.reflect.Method;

import com.story.storyadmin.config.upload.annotation.FileSlotDisabled;
import com.story.storyadmin.config.upload.entity.FileSlot;
import com.story.storyadmin.config.upload.support.FileResolver;
import com.story.storyadmin.config.upload.support.MultipartContextHolder;
import com.story.storyadmin.config.upload.support.MultipartRequestWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(2)
@Component
public class MultipartAspect {
	protected final Log logger = LogFactory.getLog(MultipartAspect.class);

	@Value("${file.multipart.autoSaveEnabled:true}")
	private Boolean autoSaveEnabled = true;

	@Autowired
	FileResolver fileResolver;

	@Before(value = "@annotation(org.springframework.web.bind.annotation.PostMapping)")
	public void beforAdvice(JoinPoint joinPoint) throws Throwable {
		if (!this.autoSaveEnabled) {
			return;
		}

		// 如果有@FileSlotDisabled注解， 不继续处理。
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		FileSlotDisabled fileSlotDisabledAnnotation = method.getAnnotation(FileSlotDisabled.class);
		if (fileSlotDisabledAnnotation != null) {
			return;
		}

		Object[] args = joinPoint.getArgs();
		if (args == null) {
			return;
		}

		Object target = joinPoint.getTarget();
		if (target instanceof ErrorController) {
			return;
		}

		//
		MultipartRequestWrapper requestWrapper = MultipartContextHolder.currentRequestAttributes();
		if (requestWrapper == null) {
			return;
		}

		if (!requestWrapper.isMultipartRequest()) {
			return;
		}

		for (Object arg : args) {
			if (arg instanceof FileSlot) {
				fileResolver.resolveFile(requestWrapper, (FileSlot) arg);
			}
		}
	}

}
