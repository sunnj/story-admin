package com.story.storyadmin.web;

import com.story.storyadmin.domain.vo.Result;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
public class ExceptionController {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handle401(ShiroException e) {
        logger.error("ShiroException", e);
        return new Result<String>(false, e.getMessage(), null);
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Result handle401(UnauthorizedException ex) {
        logger.error("UnauthorizedException", ex);
        String msg= ex.getMessage();
        String pattern = "\\[(.*?)\\]";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(msg);
        String funcCode;
        if (m.find()) {
            funcCode= m.group(1);
            return new Result<String>(false, String.format("操作失败，权限不足，权限码：%s",funcCode), null);
        }else{
            return new Result<String>(false, "操作失败，权限不足，请联系管理员", null);
        }
    }

//    // 捕捉权限异常
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(UnauthorizedException.class)
//    public Result authorityException(HttpServletRequest request, Throwable ex) {
//        logger.error("Exception:{}", ex);
//        return new Result<String>(false, ex.getMessage(), null);
//    }

//    // 捕捉业务异常
//    @ExceptionHandler(BizException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result bizException(HttpServletRequest request, Throwable ex) {
//        logger.error("Exception:{}", ex);
//        return new Result<String>(false, ex.getMessage(), null);
//    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result globalException(HttpServletRequest request, Throwable ex) {
        logger.error("Exception:{}", ex);
        return new Result<String>(false, "系统异常，请稍后重试", null);
    }

    /**
     * 获取http状态码
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
