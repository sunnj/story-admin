/*
 *  Copyright HNA©2017. All rights reserved.
 */
package com.story.storyadmin.config.upload.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.story.storyadmin.config.upload.annotation.FileSlotDisabled;
import com.story.storyadmin.config.upload.support.MultipartContextHolder;
import com.story.storyadmin.config.upload.support.MultipartRequestWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Multipart请求拦截器. 把request对象放到当前Thread中， 供后面的${@link MultipartAspect}使用。
 */
public class MultipartHandlerInterceptor extends HandlerInterceptorAdapter {

	@Value("${file.multipart.autoSaveEnabled:true}")
    private Boolean autoSaveEnabled = true;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        if (!this.autoSaveEnabled) {
            return super.preHandle(request, response, handler);
        }

        MultipartRequestWrapper requestWrapper = new MultipartRequestWrapper(request);

        if (!requestWrapper.isMultipartRequest()) {
            return super.preHandle(request, response, handler);
        }

        HandlerMethod method = (HandlerMethod) handler;

        // 如果有@FileSlotDisabled注解， 不继续处理。
        FileSlotDisabled fileSlotDisabledAnnotation = method
                .getMethodAnnotation(FileSlotDisabled.class);
        if (fileSlotDisabledAnnotation != null) {
            return super.preHandle(request, response, handler);
        }

        RequestMapping requestMappingAnnotation = method
                .getMethodAnnotation(RequestMapping.class);
        if (requestMappingAnnotation == null) {
            return super.preHandle(request, response, handler);
        }

        MultipartContextHolder.setRequestWrapper(requestWrapper);

        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        // 清理
        MultipartContextHolder.resetRequestWrapper();
    }

}
