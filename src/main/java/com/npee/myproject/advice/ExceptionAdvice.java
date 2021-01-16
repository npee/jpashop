package com.npee.myproject.advice;

import com.npee.myproject.advice.exception.CustomSigninFailedException;
import com.npee.myproject.advice.exception.CustomUserNotExistsException;
import com.npee.myproject.config.response.CommonResult;
import com.npee.myproject.config.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
// TODO: 패키지 경로 체크
@RestControllerAdvice(basePackages = "com.npee.myproject")
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
//        return responseService.getFailResult(Integer.parseInt(getMessage("unKnown.code")), getMessage("unKnown.message"));
//        // return responseService.getFailResult();
//    }

    @ExceptionHandler(CustomUserNotExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotExistsException(HttpServletRequest request, CustomUserNotExistsException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("userNotExists.code")), getMessage("userNotExists.message"));
    }

    @ExceptionHandler(CustomSigninFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult signinFailedException(HttpServletRequest request, CustomSigninFailedException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("signinFailed.code")), getMessage("signinFailed.message"));
    }

    private String getMessage(String code) {
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}