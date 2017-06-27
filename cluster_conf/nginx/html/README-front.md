#前端说明

##使用框架
- jquery(2.1.4)
- bootstrap-table(1.11.0, 其中bootstrap为3.3.0)
- html5shiv(3.7)
- respond(1.4.2)
- jquery.cookie(1.4.1)

##DEMO
- [参见](https://github.com/cruiser/beauty_ssm_cluster/tree/master/cluster_conf/nginx/html)

##前后端通讯接口

###使用[RESTFUL规范](http://www.ruanyifeng.com/blog/2014/05/restful_api.html)

###详细接口Endpoint
    待整理

##角色配置
- 超级用户
- 短信发送
- 客户经理
- 普通用户

###系统使用手机号+手机验证码方式进行登陆
- 所有用户默认拥有短信发送权限；

- 超级用户由系统初始化时进行指定，超级用户使用手机号和密码进行登录后可以维护客户经理登陆手机号

- 客户经理首次登陆后绑定微信公众号后，可以维护普通用户登录手机号

- 普通用户首次登陆绑定微信公众号后u，可以查看相关信息。

注：因此可以简化后台，只需要配置admin的token验证即可；
###前台中分两种情况：
- 微信页面，未绑定统一重定向到绑定页面，绑定页面通过手机+验证码方式进行绑定，绑定后以后通过openid进行验证。
- 电脑页面，使用用户名密码方式进行验证，验证成功赋予客户经理权限。
