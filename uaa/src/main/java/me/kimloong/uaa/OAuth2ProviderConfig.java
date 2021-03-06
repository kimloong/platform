package me.kimloong.uaa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * oauth 2.0 provider config
 *
 * @author KimLoong
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2ProviderConfig extends AuthorizationServerConfigurerAdapter {

    private static final String MICROBLOG_CLIENT = "microblog-client";
    private static final String MICROBLOG_RESOURCE = "microblog";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(MICROBLOG_CLIENT)
                .authorizedGrantTypes("authorization_code")
                .scopes("read")
                .redirectUris("http://localhost:8082/microblog-client")
                .secret("secret123")
                .autoApprove("read")
                .and()
                .withClient(MICROBLOG_RESOURCE)
                .secret("secret123")
                .and()
                .withClient("resource-sample")
                .authorizedGrantTypes("client_credentials", "authorization_code")
                .redirectUris("http://localhost:8082/microblog-client")
                .secret("secret123")
                .scopes("read")
                .autoApprove("read");
    }
}
