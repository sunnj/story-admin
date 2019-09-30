package com.story.storyadmin.config.mongo;

import com.alibaba.fastjson.JSONObject;
import com.story.storyadmin.config.shiro.LoginUser;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.domain.entity.sysmgr.SysLog;
import com.story.storyadmin.service.sysmgr.SysLogService;
import com.story.storyadmin.utils.DateUtils;
import com.story.storyadmin.utils.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.UUID;

@Aspect
@Order(5)
@Component
public class StorySysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    public static final String ACCOUNT = "匿名用户";
    private static final String DEFAULT_PKG = "com.story.storyadmin.web";
    private static final String SHORT_PKG = "s.c";

    ThreadLocal<Long> startTime = new ThreadLocal<>();
    ThreadLocal<String> logId = new ThreadLocal<>();

    @Pointcut("@annotation(com.story.storyadmin.config.mongo.SysLogAnnotation)")
    public void storySysLog() {
    }

    @Before("storySysLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        startTime.set(System.currentTimeMillis());
        logId.set(UUID.randomUUID().toString());

        LoginUser loginUser = UserContext.getCurrentUser();
        final String method = request.getMethod();
        final String url = request.getRequestURL().toString();
        final String uri = request.getRequestURI();
        final String ip = IPUtils.getIpAddr(request);
        final String account = loginUser != null ? loginUser.getAccount() : ACCOUNT;
        final String clazz = joinPoint.getSignature().getDeclaringTypeName().replaceAll(DEFAULT_PKG, SHORT_PKG);
        final String methodName = joinPoint.getSignature().getName();
        final Object[] args = joinPoint.getArgs();
        final String params = JSONObject.toJSONString(args);
        sysLogService.recordLog(new SysLog(logId.get(), account, ip, method, url, uri, clazz, methodName, DateUtils.currentDate(),
                0L, params), null, null);
    }

    @AfterReturning(returning = "ret", pointcut = "storySysLog()")
    public void doAfterReturning(Object ret) {
        sysLogService.recordLog(null, logId.get(), System.currentTimeMillis() - startTime.get());
    }
}
