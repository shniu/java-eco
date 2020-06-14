# Java Ecosystem

Java ecosystem

## 模块

- eco-ratelimiter

通用限流框架的实现

- eco-algorithm

整合数据结构和算法的实现，工作和学习中用到的算法也会放在这里

## Java 项目

#### Spring boot

- [ityouknow/spring-boot-examples](https://github.com/ityouknow/spring-boot-examples)

about learning Spring Boot via examples. Spring Boot 教程、技术栈示例代码，快速简单上手教程。 http://www.ityouknow.com/

- [xuwujing/springBoot-study](https://github.com/xuwujing/springBoot-study)

SpringBoot学习的相关工程并辅以博文讲解。主要包括入门的Hello World、自定义配置的获取、集成mybatis的xml和注解使用、集成jpa的使用、集成druid进行项目的监控、 项目打包、使用logback日志文件管理、添加过滤器和拦截器、多数据源、Restful风格的服务、集成elasticsearch、redis、netty、集成jsp和thymeleaf、集成storm、kafka等相关技术。

## 知识点

```
// https://dzone.com/articles/test-driven-development-with-spring-boot-rest-api
<dependency>
        <groupId>com.jayway.restassured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>2.8.0</version>
       <scope>test</scope>
</dependency>
<dependency>
        <groupId>org.codehaus.groovy</groupId>
        <artifactId>groovy-all</artifactId>
        <version>2.4.5</version>
</dependency>
```

```
http://www.importnew.com/29579.html  SpringBoot | 第十三章：测试相关（单元测试、性能测试）
https://www.baeldung.com/spring-boot-testing
https://www.baeldung.com/category/testing/
http://www.everydayunittesting.com/2018/03/real-life-tdd-test-case-analysis-part-i.html  需要读一下
```

### Maven

```
// https://mromeh.com/2017/12/04/jenkins-pipeline-for-continuous-delivery-and-deployment/

// 跳过集成测试
mvn -Dintegration-tests.skip=true clean package

// 跳过单元测试
mvn verify -Dunit-tests.skip=true

mvn verify sonar:sonar -Dintegration-tests.skip=true -Dmaven.test.failure.ignore=true

mvn -Dtest=ApplicationSanityCheck_ITT surefire:test
```

