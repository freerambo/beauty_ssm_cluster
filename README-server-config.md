#服务器配置

##配置JDK
- 下载
- 解压缩到/usr/lib/jvm/jdk1.8.0_subversion/
- 配置可执行文件执行的权限 chmod +x *
- 配置可执行文件路径 update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk1.8.0_05/jre/bin/java 300
- 配置环境变量(/etc/environment)
    `config java settings`
    `export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_111`
    `export JRE_HOME=${JAVA_HOME}/jre`
    `export CLASSPATH=.:${JRE_HOME}/lib`
    `export PATH=${JRE_HOME}/bin:$PATH`

##配置TOMCAT 9
- 下载 wget
- 解压缩到/opt/tomcat9
- 配置环境变量(/etc/environment)
    `config tomcat settings`
    `export CATALINA_HOME="/opt/tomcat9"`
- 配置用户
    `<!--Access to the HTML interface.-->`
    `<role rolename="manager-gui"/>`
    `<!--Access to the tools-friendly plain text interface that is described in this document, and to the "Server Status" page.-->`
    `<role rolename="manager-script"/>`
    `<user username="user1" password="user1's password" roles="manager-gui"/>`
    `<user username="user2" password="user2's password" roles="manager-script"/>`

- 后台运行配置(可选)
    [参考Unix daemon](https://tomcat.apache.org/tomcat-9.0-doc/setup.html#Unix_daemon)
    [参考2](http://www.cnblogs.com/super119/archive/2011/01/13/1935010.html)
    
- 配置maven tomcat 自动deploy
    [settings 配置](https://maven.apache.org/settings.html)
    [Tomcat7 Maven Plugin配置](http://tomcat.apache.org/maven-plugin-trunk/tomcat7-maven-plugin/usage.html)

- 配置mysql 
    略
    
- 配置nginx
    [安装nginx含防火墙配置](https://www.digitalocean.com/community/tutorials/how-to-install-nginx-on-ubuntu-16-04)
    - 摘录：
        - Content
    
            `/var/www/html: The actual web content, which by default only consists of the default Nginx page you saw earlier, is served out of the /var/www/html directory. This can be changed by altering Nginx configuration files.`
    
        - Server Configuration
    
            `/etc/nginx: The nginx configuration directory. All of the Nginx configuration files reside here.`
            `/etc/nginx/nginx.conf: The main Nginx configuration file. This can be modified to make changes to the Nginx global configuraiton.`
            `/etc/nginx/sites-available: The directory where per-site "server blocks" can be stored. Nginx will not use the configuration files found in this directory unless they are linked to the sites-enabled directory (see below). Typically, all server block configuration is done in this directory, and then enabled by linking to the other directory.`
            `/etc/nginx/sites-enabled/: The directory where enabled per-site "server blocks" are stored. Typically, these are created by linking to configuration files found in the sites-available directory.`
            `/etc/nginx/snippets: This directory contains configuration fragments that can be included elsewhere in the Nginx configuration. Potentially repeatable configuration segments are good candidates for refactoring into snippets.`
    
        - Server Logs
    
            `/var/log/nginx/access.log: Every request to your web server is recorded in this log file unless Nginx is configured to do otherwise.`
            `/var/log/nginx/error.log: Any Nginx errors will be recorded in this log.`
    
    [其中：ufw设置](https://www.digitalocean.com/community/tutorials/how-to-set-up-a-firewall-with-ufw-on-ubuntu-14-04)
    - 摘录命令：
        `ufw status verbose #查询状态`
        `ufw allow ssh/22 #开放端口访问`
    TODO: [负载平衡参考](https://www.nginx.com/resources/deployment-guides/load-balance-apache-tomcat/)

- 配置maven
    
