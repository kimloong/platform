# platform

## start registry
```
--server.port=8761
--eureka.instance.hostname=registry1.kimloong.me
--eureka.client.register-with-eureka=true
--eureka.client.fetch-registry=true
--eureka.client.service-url.defaultZone=http://registry2.kimloong.me:8762/eureka/
```

## 问题
### 1 Spring Boot从1.4.x升级到1.5.x时，请求的Bearer Token无法被正确解析
#### 原因
升级后，`WebSecurityConfig`的`config`方法变成在`ResourceServerConfig`的`config`方法之前执行，导致`FilterChainProxy`的`filters`顺序发生改变，请求被`WebSecurityConfig`注册的`Filter`处理了，而处理`Bearer Token`的`OAuth2AuthenticationProcessingFilter`则是由`ResourceServerConfig`注册，未能被正确执行

#### 解决
在配置文件中指定`security.oauth2.resource.filter-order: 1`

>> [migrating SpringBoot 1.4.4 to 1.5.1 cause oauth2 NullPointerException on ResourceServer](https://github.com/spring-projects/spring-security-oauth/issues/995)