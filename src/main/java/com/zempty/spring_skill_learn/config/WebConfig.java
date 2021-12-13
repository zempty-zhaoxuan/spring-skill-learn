package com.zempty.spring_skill_learn.config;

import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zempty.spring_skill_learn.interceptor.TestInterceptor;
import com.zempty.spring_skill_learn.reponse.UserModifyResponse;
import com.zempty.spring_skill_learn.request.RequestPackage;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //配置拦截器，配置拦截器的访问路径等
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");


//        添加 LocaleChangeInterceptor,通过用户请求的 url 中添加参数来指定Locale (可以使用在 SessionLocaleResolver/CookieLocaleResolver)中
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("lang");//拦截 lang 参数
//        registry.addInterceptor(localeChangeInterceptor);

    }


    //使用该方法修改返回值的时候发现无效
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter messageConverter = new FastJsonHttpMessageConverter();
//        messageConverter.setSupportedMediaTypes(Arrays.asList(
//                MediaType.APPLICATION_JSON_UTF8,
//                new MediaType(MediaType.TEXT_HTML, StandardCharsets.UTF_8)
//        ));
//        SerializeFilter[] modifyResponses = {new UserModifyResponse()};
//        messageConverter.getFastJsonConfig().setSerializeFilters(modifyResponses);
//        converters.add(messageConverter);
//    }


    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {

        FastJsonHttpMessageConverter fastJsonHttpMessageConverter =
                new FastJsonHttpMessageConverter();
        //创建FastJson对象并设定序列化规则
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //添加自定义valueFilter
        fastJsonConfig.setSerializeFilters(new UserModifyResponse());
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        //规则赋予转换对象
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        StringHttpMessageConverter stringHttpMessageConverter =
                new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return new HttpMessageConverters(fastJsonHttpMessageConverter, stringHttpMessageConverter);
    }


    // 注入提请封装参数的实体类进入 spring
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new RequestPackage());
    }
}
