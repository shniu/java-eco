# tdd-starter

Tdd in action

## Tdd 实践

- [Spring Boot: REST + TDD from scratch](https://hackernoon.com/spring-boot-rest-tdd-from-scratch-15f13ed799e0)

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

