package com.github.cruiser.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

/**
 * @EnableGlobalMethodSecurity 可以指定 securedEnabled=true 也可以指定
 * prePostEnabled=true，区别在于 前者只能够用于应用简单的基于角色的约束，
 * 后者可以基于spring el 表达式来指定约束
 * 可以参考：
 *[spring-security-reference](https://vincentmi.gitbooks.io/spring-security-reference-zh/content/3.8_method_security.html)
 *[Expression-Based Access Control](http://docs.spring.io/spring-security/site/docs/3.1.x/reference/el-access.html)
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    @SuppressWarnings("unused")
    @Autowired
    private OAuth2SecurityConfiguration securityConfig;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }
}
