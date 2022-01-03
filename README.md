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


1. 自定义过滤器一： 继承 Filter 类，详情参考[FirstFilter](./src/main/java/com/zempty/spring_skill_learn/filter/FirstFilter.java)
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

## 使用 ContextValueFilter (fastJson) 修改返回值

1.定义一个类继承 ContextValueFilter , 该类用来处理修改返回值的逻辑操作，详情见 [UserModifyResponse](./src/main/java/com/zempty/spring_skill_learn/reponse/UserModifyResponse.java)
这里可以根据需求修改要返回的值。
2.注入消息转换器，详细定义可参考如下，详情见 [WebConfig](./src/main/java/com/zempty/spring_skill_learn/config/WebConfig.java)：
```java
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
```

这里遇到一个坑,一开始使用的是  public void configureMessageConverters configureMessageConverters(List<HttpMessageConverter<?>> converters)来注入修改返回值但是结果无效，后来使用上面的代码重新注入
HttpMessageConverters 才可以使用,相关的文档参考：

[Custom Jackson deserializer does not get registered in Spring](https://stackoverflow.com/questions/39891911/custom-jackson-deserializer-does-not-get-registered-in-spring)


[springboot 修改fastjson序列化javabean并添加自定义注解](https://www.i4k.xyz/article/qq_33446715/82289796)




## 提前封装实体类，后续接口使用

1. 定义一个继承 HandlerMethodArgumentResolver 的类，实现其抽象方法，详情可见 [RequestPackage](./src/main/java/com/zempty/spring_skill_learn/request/RequestPackage.java)
2. 注入 spring ，详情配置如下：
```java
 // 注入提请封装参数的实体类进入 spring
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new RequestPackage());
    }
```
UserController 中 "/user" 接口可以用来验证测试。


## i18n 国际化的支持
spring boot 默认对 i18n 的支持，默认使用 AcceptHeaderLocaleResolver 来解析浏览器头部的 Accept-Language 来获取 Locale 切换不同语言。

![](https://raw.githubusercontent.com/zempty-zhaoxuan/pics/master/accept-language.png)

springboot 默认使用 i18n 的步骤如下：
### 使用 spingboot 默认的 i18n 功能
1. 准备各种语言文件，语言文件格式为： xxxx.properties (默认的)，xxxx_en_US.properties /xxxx_zh_CN.properties 即：xxxx_国家代码.properties。
2. spring boot 配置文件中指定语言文件的位置：
```java

spring.messages.encoding=UTF-8
spring.messages.basename=static/i18n/messages,static/i18n/zempty

```
可以切换到 v5.0 tag 进行测试 UserController 中的 /i18n 接口进行测试.

### 使用自定义的 i18n 功能

关于 i18n 中的几个 LocaleResolver :

SessionLocaleResolver: Locale 使用 session 来保存，文件语言的切换在 session 作用域内有效；
CookieLocaleResolver: Locale 使用 cookie 来保存，文件语言切换通过 cookie 来保存；
FixedLocaleResolver: 使用固定的 Locale ,就是不切换语言，使用意义不大；
AcceptHeaderLocaleResolver: 这个是 springboot 默认使用的 LocaleResolver ,通过客户端指定 Accept-Language 头部信息来切换语言。

关于自定义的 LocaleResolver 可以参考 [SessionLocaleConfig](./src/main/java/com/zempty/spring_skill_learn/config/SessionLocaleConfig.java)来进行配置注入：
```java 

@Configuration
public class SessionLocaleConfig  {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.CHINA);
        return sessionLocaleResolver;
    }
}

```
我曾在这里犯了一个错误，我把方法名定义成 sessionLocaleResolver 程序会报错：
>>  Request processing failed; nested exception is java.lang.UnsupportedOperationException: Cannot change HTTP accept header - use a different locale resolution strategy

**这里应该注意，注入的 bean name 应该是 localeResolver。**

当在使用 SessionLocaleResolver 或者 CookieLocaleResolver 的时候可以配置 LocaleChangeInterceptor 的拦截器，来指定请求参数获取 Locale ,细节可参考 [WebConfig](./src/main/java/com/zempty/spring_skill_learn/config/WebConfig.java)
```java
    //配置拦截器，配置拦截器的访问路径等
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");


//        添加 LocaleChangeInterceptor,通过用户请求的 url 中添加参数来指定Locale (可以使用在 SessionLocaleResolver/CookieLocaleResolver)中
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");//拦截 lang 参数
        registry.addInterceptor(localeChangeInterceptor);

    }

```
这样客户端访问的时候便可以使用 http://xxxxx?lang=zh_CN 形式的访问来切换语言。

## FreeMarker 的使用
1. 引入 freemarker 的依赖：
>> implementation 'org.springframework.boot:spring-boot-starter-freemarker'

2. 配置 application.properties 关于 freemarker 的配置,常用配置如下所示：

![](https://raw.githubusercontent.com/zempty-zhaoxuan/pics/master/spring_freemarker.png)

测试可以在 UserController 中的 /freemarker 接口中进行测试,访问接口，跳到 [helloworld.ftl](./src/main/resources/templates/helloworld.ftl)

## ELK 日志系统的搭建
1. application.properties 中配置 log 文件的生成位置：
>> logging.file.name= logs/application.log
2. 配置 ElasticSearch + LogStash + Kibana 
![](https://raw.githubusercontent.com/zempty-zhaoxuan/pics/master/202112282224439.png)

该项目中的 logstash.conf 需要复制，粘贴到 LogStash 的配置文件 logstash.conf 中使用。
具体软件安装步骤，可 google 搜索教程安装。
参考文章:

[Spring Boot Logs with Elasticsearch, Logstash and Kibana(ELK)](https://medium.com/hprog99/spring-boot-logs-with-elasticsearch-logstash-and-kibana-elk-c61a378f3cb4)


## 多数据源的配置（datasource)

