package com.epam.resolver;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandlerExceptionResolver extends
        ExceptionHandlerExceptionResolver {

    public CustomExceptionHandlerExceptionResolver() {
        setWarnLogCategory(getClass().getName());
        setOrder(LOWEST_PRECEDENCE - 1);
    }

    @Override
    public String buildLogMessage(Exception exception, HttpServletRequest request) {
        return "MVC exception: " + exception.getLocalizedMessage();
    }

    @Override
    protected ModelAndView doResolveHandlerMethodException(
            HttpServletRequest request, HttpServletResponse response,
            HandlerMethod handlerMethod, Exception exception) {

        ModelAndView mav = super.doResolveHandlerMethodException(request,
                response, handlerMethod, exception);

        mav.addObject("exception", exception);
        mav.addObject("url", request.getRequestURL());
        mav.addObject("timestamp", new Date());
        mav.addObject("status", 500);
        return mav;
    }
}