#服务器配置

##配置JDK
- 下载
- 解压缩到/usr/lib/jvm/jdk1.8.0_subversion/
- 配置可执行文件执行的权限 chmod +x *
- 配置可执行文件路径 update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk1.8.0_05/jre/bin/java 300
- 配置环境变量(/etc/environment)
    config java settings
    export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_111
    export JRE_HOME=${JAVA_HOME}/jre
    export CLASSPATH=.:${JRE_HOME}/lib
    export PATH=${JRE_HOME}/bin:$PATH

##配置TOMCAT 9
- 下载 wget
- 解压缩到/opt/tomcat9
- 配置环境变量(/etc/environment)
    config tomcat settings
    export CATALINA_HOME="/opt/tomcat9"
- 配置用户
    <!--Access to the HTML interface.-->
    <role rolename="manager-gui"/>
    <!--Access to the tools-friendly plain text interface that is described in this document, and to the "Server Status" page.-->
    <role rolename="manager-script"/>
    <user username="user1" password="user1's password" roles="manager-gui"/>
    <user username="user2" password="user2's password" roles="manager-script"/>

- 后台运行配置(可选)
    [参考Unix daemon](https://tomcat.apache.org/tomcat-9.0-doc/setup.html#Unix_daemon)
    
- 配置maven tomcat 自动deploy
    [settings 配置](https://maven.apache.org/settings.html)
    [Tomcat7 Maven Plugin配置](http://tomcat.apache.org/maven-plugin-trunk/tomcat7-maven-plugin/usage.html)

- 配置mysql 
    略
