package com.zempty.spring_skill_learn.request;

import com.zempty.spring_skill_learn.entity.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class RequestPackage implements HandlerMethodArgumentResolver {

    // 用来包装 User, 这样 User 就可以在 controller 作为参数使用
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        System.out.println(parameter);
        return User.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        System.out.println(parameter);
        System.out.println(mavContainer);
        System.out.println(webRequest);
        System.out.println(binderFactory);
        return webRequest.getNativeRequest(HttpServletRequest.class).getAttribute("user");
    }

}
