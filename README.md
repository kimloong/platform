# 注册中心（服务注册与发现）
## 启动单一注册中心
```
java me.kimloong.resource.sample.ResourceSampleApplication
```
## 启动多个注册中心
此处为显示效果进行了分行
* 实例1
```
java me.kimloong.resource.sample.ResourceSampleApplication
--server.port=8761
--eureka.instance.hostname=registry1.kimloong.me
--eureka.client.register-with-eureka=true
--eureka.client.fetch-registry=true
--eureka.client.service-url.defaultZone=http://registry2.kimloong.me:8762/eureka/
```

* 实例2
```
java me.kimloong.resource.sample.ResourceSampleApplication
--server.port=8762
--eureka.instance.hostname=registry2.kimloong.me
--eureka.client.register-with-eureka=true
--eureka.client.fetch-registry=true
--eureka.client.service-url.defaultZone=http://registry1.kimloong.me:8761/eureka/
```

* 实例3
```
java me.kimloong.resource.sample.ResourceSampleApplication
--server.port=8763
--eureka.instance.hostname=registry3.kimloong.me
--eureka.client.register-with-eureka=true
--eureka.client.fetch-registry=true
--eureka.client.service-url.defaultZone=http://registry1.kimloong.me:8761/eureka/
```

# 配置中心


# 用户账号认证(UAA:User Account and Authentication)

## 如何运行本项目
1. 启动arangodb
```
sudo docker run -p 8529:8529 -e ARANGO_ROOT_PASSWORD=root --name user-center-db -d arangodb
```
2. 访问arangodb
```
http://localhost:8529
```
3. 启动服务
* 使用`maven`执行`spring-boot:run`

4. 注册用户
```
[POST] http://localhost:8080/uc/users
Header:
  无
Body:
  {
    "username":"user",
    "password":"123456"
  }
```

5. 在浏览器请求以下链接(以下链接为模拟第三方客户端登录)
```
http://localhost:8080/uc/oauth/authorize?response_type=code&client_id=microblog-client&redirect_uri=http://localhost:8082/microblog-client
```
请求会跳转至登录页面，输入用户名密码(`步骤4`注册的用户)，请求会再次跳转至
```
http://localhost:8082/microblog-client?code=I5l6xR
```
复制跳转链接中的`code`（即这里的`I5l6xR`）

6. 获取`token`
```
[POST] http://localhost:8080/uc/oauth/token?grant_type=authorization_code&redirect_uri=http://localhost:8082/microblog-client&code=I5l6xR
Header:
  Authorization:Basic bWljcm9ibG9nLWNsaWVudDpzZWNyZXQxMjM=
Body:
  无
```

`Header`里的`Authorization`为`clientId`:`clientSecret`进行`Base`加密得来的，可以到[这里](http://tool.chinaz.com/Tools/Base64.aspx)
响应如下：
```
{
  "access_token": "f5970310-ed94-4c0f-8acb-809fe0e626c8",
  "token_type": "bearer",
  "expires_in": 43199,
  "scope": "read"
}
```

7. 向`Resource Server`请求
我们从上一步中得到了`access_token`就可以使用它来请求资源服务器，这里以[microblog](https://github.com/kimloong/microblog)项目为例
```
[GET] http://localhost:8081/microblog/hello
Header:
  Authorization Bearer f5970310-ed94-4c0f-8acb-809fe0e626c8
```

## 解决问题

### 1. 使用`Bearer Token`访问`UAA`无法认证通过而跳转至登录页面
* 问题描述
`Spring Boot`从`1.4.x`升级到`1.5.x`时，请求的`Bearer Token`无法被正确解析
* 问题原因
升级后，`WebSecurityConfig`的`config`方法变成在`ResourceServerConfig`的`config`方法之前执行，导致`FilterChainProxy`的`filters`顺序发生改变，请求被`WebSecurityConfig`注册的`Filter`处理了，而处理`Bearer Token`的`OAuth2AuthenticationProcessingFilter`则是由`ResourceServerConfig`注册，未能被正确执行

* 解决方案
在配置文件中指定`security.oauth2.resource.filter-order: 1`

* 参考
> [migrating SpringBoot 1.4.4 to 1.5.1 cause oauth2 NullPointerException on ResourceServer](https://github.com/spring-projects/spring-security-oauth/issues/995)

### 2. `Client`接入，登录验证成功携带`code`返回`Client`时，报`401 Unauthorized`
* 问题描述
`Client`控制台报错信息
```
There was an unexpected error (type=Unauthorized, status=401).
Authentication Failed: Could not obtain access token
.....
Possible CSRF detected - state parameter was present but no state could be found
```
* 问题原因
通过源码跟踪，发现登录验证成功后在类`AuthorizationCodeAccessTokenProvider`获取`preservedState`为空而抛出了异常，猜可能跟`cookie`同源机制有关

* 解决方案
将`Authorization Server`与`Client`的`Context Path`设置成不一样即可，在两者不同host时，则不会有问题。

* 参考
> [Unable to get EnableOauth2Sso Working — BadCredentialsException: Could not obtain access token](http://stackoverflow.com/questions/31383514/unable-to-get-enableoauth2sso-working-badcredentialsexception-could-not-obta)

### 3. 登录验证成功且请求完`access token`后（`/oauth/check_token`阶段）报错
* 问题描述
报`Client`报`500 Internal Server Error`，`Server`报`403 Forbidden`

* 问题原因
`Server`端的`/oauth/check_token`默认为`denyAll()`,所以无法进请求成功

* 解决方案
``` java
public class OAuth2ProviderConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("fullyAuthenticated");
    }
}
```

* 参考
> [How to use RemoteTokenService?](http://stackoverflow.com/questions/26250522/how-to-use-remotetokenservice)

### 4. 使用`Bearer Token`访问`Resource Server`报`insufficient scope for this resource`
* 问题描述
在`Resource Server`的`ResourceServerConfig`配置了如下权限控制
```
@Override
public void configure(HttpSecurity http) throws Exception {

    http.antMatcher("/**")
            .authorizeRequests()
            .anyRequest().access("#oauth2.hasScope('read')");
}
```
同时使用`UserInfoTokenServices`进行`Token`验证
```
@Bean
public ResourceServerTokenServices tokenService() {
    return new UserInfoTokenServices(
            resourceServerProperties.getUserInfoUri(),
            resourceServerProperties.getClientId());
}
```

* 问题原因
该报错为`Resource Server`端的报错，实际`UAA`已经完成了Token的验证并返回了`Principal`,`UserInfoTokenServices`仅作为获取用户信息，所以对与`Resource Server`端来讲，这是一个认证用户，而无法使用`scope`来进行权限验证。如果想验证`scope`。

* 解决方案
1. 改用`RemoteTokenServices`,配置`security.oauth2.resource.token-info-uri`;
因为`ResourceServerTokenServicesConfiguration`已有注册一个`RemoteTokenServices`因此可以直接省略;
实际`user-info-uri`与`token-info-uri`都有返回`scope`但

2. 使用`hasRole('USER')`验证是登录用户
```
@Override
public void configure(HttpSecurity http) throws Exception {

    http.antMatcher("/**")
            .authorizeRequests()
            .anyRequest().access("hasRole('USER')");
}
```
3. 依旧使用`#oauth2.hasScope('read')`,重写`UserInfoTokenServices`，查阅参考中的评论

* 参考
> [#oauth2.hasScope and Resource Server](https://github.com/spring-projects/spring-boot/issues/5096)


# 参考文档
> [Spring REST API + OAuth2 + AngularJS](http://www.baeldung.com/rest-api-spring-oauth2-angularjs)