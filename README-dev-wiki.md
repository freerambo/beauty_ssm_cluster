#开发参考

##okhttp 库用法（用于http通讯）
- [Github](https://github.com/square/okhttp)
- 示例
    - [Get Example](https://github.com/square/okhttp/blob/master/samples/guide/src/main/java/okhttp3/guide/GetExample.java)
    - [Post Example](https://github.com/square/okhttp/blob/master/samples/guide/src/main/java/okhttp3/guide/PostExample.java)
- 网上用法介绍
    - [OkHttp使用教程](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0106/2275.html)
    - [OkHttpClient类api](https://square.github.io/okhttp/3.x/okhttp/okhttp3/OkHttpClient.html)
    - [Request类api](https://square.github.io/okhttp/3.x/okhttp/okhttp3/Request.html)
    - [OkHttpClient类api]()

##JAXB 库用法（用于xml格式化）
- 注意：主要使用
    - `XmlRootElement`定义根节点;
    - `XmlElement`定义其他节点，要注意定义在get方法上。
    
- [官方文档](https://jaxb.java.net/tutorial/index.html)
- [反序列化api，含示例](https://docs.oracle.com/javase/7/docs/api/javax/xml/bind/Unmarshaller.html)
- 常见问题
    - [问题1](http://stackoverflow.com/questions/41875849/how-to-unmarshal-nested-child-elements-in-java-with-same-tag-name)
    - [问题2](http://stackoverflow.com/questions/16364547/how-to-parse-xml-to-java-object)

##jackson dataformat xml的XmlMapper（可用于xml格式化，不过最终没有使用）
- [参考](http://www.sojson.com/yasuoyihang.html)

##mybatis 框架用法
- [处理类关系中的一对一映射关系](https://www.ibm.com/developerworks/cn/opensource/os-cn-ibatis/)
- [association实现延迟加载](http://www.cnblogs.com/selene/p/4631244.html)
- [表实体对应关系的映射demo](http://www.cnblogs.com/selene/p/4627446.html)

###mybatis 逆向工程
- 使用 mybatis-generator-maven-plugin 插件  
    用途对mysql数据库进行逆向工程，自动生成model类和mapper类  
    由于mapper缺少自定义dao操作，因此使用dao类对mapper类进行继承的方式进行使用。  
    注意：由于配置文件指定targetProject为MAVEN，因此自动生成的model和mapper类都将在maven的target文件夹中，需先执行mybatis-generator:generate，或使用package。

##跨域资源共享
###规范说明
- [W3C 定义](https://www.w3.org/TR/2014/REC-cors-20140116/)
- [阮一峰解释](http://www.ruanyifeng.com/blog/2016/04/cors.html)
###网上套件
- [另外一个实现](https://mvnrepository.com/artifact/com.thetransactioncompany/cors-filter)
- [ebay 的实现](https://mvnrepository.com/artifact/org.ebaysf.web/cors-filter)
###其他资料
- [解释如何自定义一个filter 实现跨域处理机制](https://www.tianmaying.com/tutorial/cross-origin-rest-service)
- [同源和跨域解释](https://segmentfault.com/a/1190000007366644)
- [访问控制说明](https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Access_control_CORS)

##HTTPS配置
##网站认证方式
###spring security 框架
- [讲述如何在rest服务上集成spring security](http://www.baeldung.com/securing-a-restful-web-service-with-spring-security)
    - [深刻讲述authenticationManager等服务配置](http://docs.spring.io/spring-security/site/docs/3.0.x/reference/core-services.html)
    - @EnableGlobalMethodSecurity 可以指定 securedEnabled=true 也可以指定
     prePostEnabled=true，区别在于 前者只能够用于应用简单的基于角色的约束，
     后者可以基于spring el 表达式来指定约束可以参考：
     [spring-security-reference](https://vincentmi.gitbooks.io/spring-security-reference-zh/content/3.8_method_security.html)
     [Expression-Based Access Control](http://docs.spring.io/spring-security/site/docs/3.1.x/reference/el-access.html)

###BASIC AUTH
- [Secure Spring REST API using Basic Authentication](http://websystique.com/spring-security/secure-spring-rest-api-using-basic-authentication/)  
    含sever 配置讲解、源码、postman测试和java代码测试  
    摘要： This Guide explains securing REST API using Basic Authentication with help of examples involving two separate clients [Postman & a Spring RestTemplate based Java app] trying to get access to our REST API.
- [AngularJS+Spring Security using Basic Authentication](http://websystique.com/spring-security/angularjs-basic-authentication-using-spring-security/)  
    摘要：This post shows how an AngularJS application can consume a REST API which is secured with Basic authentication using Spring Security.

###OAUTH 认证
- [Secure Spring REST API using OAuth2(建议采用)](http://websystique.com/spring-security/secure-spring-rest-api-using-oauth2/)
    摘要：a simple guide showing what is required to secure a REST API using Spring OAuth2.
- [Spring Security: oauth2 運作原理解析筆記](http://blog.smlsun.com/2014/01/spring-security-oauth2.html)
- [OAuth原理初探](http://www.im47.cn/post/2012/2012-08-04-oauth2)
- [spring security & oauth2(描述如何实现oauth client)](http://www.jianshu.com/p/6b211e845b16)
- [spring-security-oauth2 server(描述如何实现oauth server)](http://www.jianshu.com/p/028043425b09)
    
##微信说明

###模版消息
- [官方接口文档](http://mp.weixin.qq.com/debug/cgi-bin/readtmpl?t=tmplmsg/faq_tmpl)
- [在线调试工具](https://mp.weixin.qq.com/debug/cgi-bin/apiinfo?t=index&type=%E6%B6%88%E6%81%AF%E6%8E%A5%E5%8F%A3%E8%B0%83%E8%AF%95&form=%E6%96%87%E6%9C%AC%E6%B6%88%E6%81%AF)
- [网上示例](http://www.cnblogs.com/txw1958/p/wechat-template-message.html)

##tools
- [json 在线工具](http://www.sojson.com/yasuoyihang.html)
- [时间戳，各种编码互转](http://tool.chinaz.com/Tools/unixtime.aspx)
- [各种奇怪工具](http://tool.lu/c/developer)

