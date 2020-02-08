1. 打包的jar中通过MANIFEST.MF文件中的Main-Class进行启动
2. 也可以在解压根目录直接运行 java org.springframework.boot.loader.JarLauncher进行启动
3. JarLauncher注册了自己的jar解析器， 会对jar或目录进行处理
4. JarLauncher通过直接调用Start-Class的静态main方法，所以是同一个进程
5. 使用spring-boot-dependencies进行手动依赖，但是需要修改各种插件
6. 切换到jetty/undertow容器
7. 响应式容器 jetty/tomcat/undertow都支持
8. SpringBootApplication注解(SpringbootConfiguration)
9. 通过UserAutoConfigure验证自动装配 spring.factories
10. 自定义派生注解@StringRepository