package com.github.cruiser.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationServerConfiguration.class);

    private static String REALM;

    @Value("${server.security.oauth2.client_id}")
    private String CLIENT_ID;

    //"my-trusted-client"
    @Value("${server.security.oauth2.secret}")
    private String SECRET;

    @Value("${server.security.oauth2.access_token_timeout}")
    private int ACCESS_TOKEN_TIMEOUT;

    @Value("${server.security.oauth2.refresh_token_timeout}")
    private int REFRESH_TOKEN_TIMEOUT;


    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;


    @Value("${server.security.oauth2.realm}")
    public void setREALM(String REALM) {
        LOG.debug("realm string is:" + REALM);
        AuthorizationServerConfiguration.REALM = REALM;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient(CLIENT_ID)
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                .secret(SECRET)
                .accessTokenValiditySeconds(ACCESS_TOKEN_TIMEOUT).//Access token is only valid for 2 minutes.
                refreshTokenValiditySeconds(REFRESH_TOKEN_TIMEOUT);//Refresh token is only valid for 10 minutes.
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        LOG.debug("realm string is:" + REALM);
        oauthServer.realm(REALM);
    }

}