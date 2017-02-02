#对"优雅的SSM框架"进行完善(页面分离+Nginx负载均衡+Tomcat集群)
- Maven
- Spring（IOC DI AOP 声明式事务处理） 
- SpringMVC（支持Restful风格）
- Hibernate Validate（参数校验）
- Mybatis（最少配置方案）
- Quartz时间调度
- 统一的异常处理
- Redis缓存（ProtoStuff序列化）
- [Redis Sentinel主从高可用方案](http://wosyingjun.iteye.com/blog/2289593)
- [Redis Cluster集群高可用方案](http://wosyingjun.iteye.com/blog/2289220)
- [Druid（数据源配置 sql防注入 sql性能监控)](http://wosyingjun.iteye.com/blog/2306139)
- 前后端分离（Html替代Jsp）  
- Nginx静态加载、负载均衡
- [基于keepalived的nginx高可用方案](http://wosyingjun.iteye.com/blog/2313147) 
- Tomcat集群（Redis共享Session）
- Sping Shiro权限控制（待完善）

###架构图：
![](http://i.imgur.com/xf0UsI7.png)

###TODO
- 公众号主动通知接收机制
    - ~~獲取access_token~~
    - ~~對接模板通知接口~~
    - ~~接收微信服務器推送通知~~
    - 将微信服务器推送通知存入数据库
- 公众号注册及审批网页
- ~~制定商户编号生成规则~~
- 完善商户信息表，如地址、身份证等需要以后在微信公众号或者公司审核人员需要录入的所有信息字段和长度。
- 统计报表等需要进行计算和情分的具体规则和计算公式。