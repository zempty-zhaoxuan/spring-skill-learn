## 拦截器的使用
1. 定义拦截器需要实现**HandlerInterceptor**,可以实现如下三个方法：
具体实现参考：[TestInterceptor](./src/main/java/com/zempty/spring_skill_learn/interceptor/TestInterceptor.java)
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
2. 添加自定义拦截器进入 spring mvc web 的配置类中，定义一个配置类实现**WebMvcConfigurer**，具体可以参看 [WebConfig](./src/main/java/com/zempty/spring_skill_learn/config/WebConfig.java))
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


## 定义过滤器
过滤器的工作原理：
![](https://raw.githubusercontent.com/zempty-zhaoxuan/pics/master/filter_flow.png)
1. 自定义过滤器一： 继承 Filter 类，详情参考[FirstFilter](src/main/java/com/zempty/spring_skill_learn/filter/FirstFilter.java)
```java
@Component
@Order(1)
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig);

    }

    @Override
    public void destroy() {
        System.out.println("test ........");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 要继续处理请求，必须添加 filterChain.doFilter()
        System.out.println(chain);
        chain.doFilter(request,response);
    }
}

```
@Component 注解把 filter 注入到 spring 当中，@Order 注解定义 filter 的执行先后顺序，值越低执行顺序越靠前。
该种方法定义的 filter 不可以拦截指定的 url ,会拦截所有的 url.

2. 注解定义 filter 使用 @WebFilter+@ServletComponentScan, @WebFilter定义在实体类上面，可以定义允许访问的 url ,@ServletComponentScan定义在启动类上面,注入到
spring 进行管理，详情参考[SecondFilter](src/main/java/com/zempty/spring_skill_learn/filter/SecondFilter.java)
3. java 配置 filter, 详情参考 [FilterConfig](src/main/java/com/zempty/spring_skill_learn/config/FilterConfig.java),可以定义 filter 的执行顺序，和访问的 url