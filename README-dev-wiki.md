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
- 使用了 mybatis-generator-maven-plugin 插件对mysql数据库进行逆向工程，自动生成model类和mapper类（使用注释方式描述sql语句）
    由于mapper缺少自定义dao操作，因此使用dao类对mapper类进行继承的方式进行使用。
    注意：由于配置文件指定targetProject为MAVEN，因此自动生成的model和mapper类都将在maven的target文件夹中，需先执行mybatis-generator:generate，或使用package。

##微信说明

###模版消息
- [官方接口文档](http://mp.weixin.qq.com/debug/cgi-bin/readtmpl?t=tmplmsg/faq_tmpl)
- [在线调试工具](https://mp.weixin.qq.com/debug/cgi-bin/apiinfo?t=index&type=%E6%B6%88%E6%81%AF%E6%8E%A5%E5%8F%A3%E8%B0%83%E8%AF%95&form=%E6%96%87%E6%9C%AC%E6%B6%88%E6%81%AF)
- [网上示例](http://www.cnblogs.com/txw1958/p/wechat-template-message.html)

##tools
- [json 在线工具](http://www.sojson.com/yasuoyihang.html)
- [时间戳，各种编码互转](http://tool.chinaz.com/Tools/unixtime.aspx)
- [各种奇怪工具](http://tool.lu/c/developer)

