## 拦截器的使用
1. 定义拦截器需要实现HandlerInterceptor,可以实现如下三个方法：
具体实现参考：[TestInterceptor](./src/main/java/com/zempty/merquri_code_learn/interceptor/TestInterceptor.java)
```java
public class TestInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(modelAndView);
        System.out.println(handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(handler);
    }
}
```
拦截器的执行顺序是 preHandle->controller(请求的 url)->postHandle->afterCompletion 
2. 添加自定义拦截器进入 spring mvc web 的配置类中，定义一个配置类实现WebMvcConfigurer，具体可以参看 [WebConfig](./src/main/java/com/zempty/merquri_code_learn/config/WebConfig.java))
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    //配置拦截器，配置拦截器的访问路径等
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");
    }
}
```
拦截器可以配置拦截的具体路径（url) , 每定义一个拦截器都需要添加到该配置类当中。
