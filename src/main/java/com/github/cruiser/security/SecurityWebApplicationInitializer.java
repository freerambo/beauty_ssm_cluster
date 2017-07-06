package com.github.cruiser.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 详见：5.1.3 AbstractSecurityWebApplicationInitializer with Spring MVC
 * 详见：http://docs.spring.io/spring-security/site/docs/current/apidocs/org/springframework/security/web/context/AbstractSecurityWebApplicationInitializer.html
 * 下述语句将用springSecurityFilterChain 注册 DelegatingFilterProxy，等同下面的
 * web.xml 配置：
 * <pre>
 *     <filter>
 *         <filter-name>springSecurityFilterChain</filter-name>
 *         <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
 *     </filter>
 *     <filter-mapping>
 *         <filter-name>springSecurityFilterChain</filter-name>
 *         <url-pattern>/*</url-pattern>
 *     </filter-mapping>
 * </pre>
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
