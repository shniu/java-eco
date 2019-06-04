
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