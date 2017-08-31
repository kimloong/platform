package me.kimloong.uaa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.PostConstruct;

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

    @Autowired
    private Environment environment;

    private JwtAccessTokenConverter jwtTokenEnhancer;

    private TokenStore tokenStore;

    @PostConstruct
    public void postConstruct() {
        jwtTokenEnhancer = jwtTokenEnhancer();
        tokenStore = new JwtTokenStore(jwtTokenEnhancer);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .tokenEnhancer(jwtTokenEnhancer);
    }

    @Bean
    public TokenStore tokenStore() {
        return tokenStore;
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

    private JwtAccessTokenConverter jwtTokenEnhancer() {
        String pwd = environment.getProperty("keystore.password");
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                new ClassPathResource("jwt.jks"), pwd.toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
        return converter;
    }
}
