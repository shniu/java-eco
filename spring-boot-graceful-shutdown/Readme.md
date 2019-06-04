
## Graceful shutdown

1. The JVM receives the SIGTERM signal and starts shutting down the Spring container.
2. A Spring EventListener listens for a ContextClosedEvent and is invoked once the shutdown is started.
3. The EventListener updates a Spring Boot HealthIndicator and puts it "out of service".
4. The context shutdown is delayed using a Thread.sleep to allow the load balancer to see the updated HealthIndicator status and stop forwarding requests to this instance.
5. When the Thread.sleep is finished, the Tomcat container is gracefully shutdown. First by pausing the connector, no longer accepting new request. Next, by allowing the Tomcat thread pool a configurable amount of time to finish the active threads.
6. Finally, the Spring context is closed.

## 项目

- [spring-boot-graceful-shutdown](https://github.com/timpeeters/spring-boot-graceful-shutdown)

This project adds graceful shutdown behavior to Spring Boot.

- [hiatus-spring-boot](https://github.com/jihor/hiatus-spring-boot)

A Spring Boot 2 starter for graceful work interruption or shutdown.

## Blog

- [SpringBoot系列: 如何优雅停止服务](https://www.cnblogs.com/harrychinese/p/SpringBoot-graceful-shutdown.html)

项目中的推荐的做法是, 引入 hiatus-spring-boot, 并参考上面示例编写一个Tomcat Connector 监听类, 这样既能优雅应对 kill pid, 又能做到主动截留.

- [docker 微服务的优雅关闭](https://www.cnblogs.com/harrychinese/p/springboot_Dockerize_SpringBoot_App.html)

使用 docker stop 关闭容器时, 只有 init(pid 1)进程能收到中断信号, 如果容器的pid 1 进程是 sh 进程, 它不具备转发结束信号到它的子进程的能力, 所以我们真正的java程序得不到中断信号, 也就不能实现优雅关闭. 解决思路是: 让pid 1 进程具备转发终止信号, 或者将 java 程序配成 pid 1 进程.

- [Docker Awareness in Java](https://efekahraman.github.io/2018/04/docker-awareness-in-java)