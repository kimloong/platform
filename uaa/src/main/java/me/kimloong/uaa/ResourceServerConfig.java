package me.kimloong.uaa;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Resource Server配置
 *
 * @author KimLoong
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                //下面这句很重要，将匹配的request才归为该resource server下
                // 从而不会影响到WebSecurityConfig
                .requestMatchers().antMatchers("/users/**")
                .and().authorizeRequests()
                //用户注册不需要权限
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .anyRequest().access("#oauth2.hasScope('read')");
    }
}
