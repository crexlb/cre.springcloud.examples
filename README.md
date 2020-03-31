# Cre.Spring Cloud Examples
# SpringCloud 核心学习教程
Spring Cloud 使用的各种示例，以最简单、最实用、最全面为标准
### Spring Cloud Hoxton SR3 发布
Spring Cloud Hoxton SR3 发布
````
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Hoxton.SR3</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
````
这是错误修正版本
基于 Spring Boot 2.2.5 构建
特别说明： 官方跳过了 SR2, 关于SR2 可以参考 Hoxton.SR2 发布了???
升级说明
#### Spring Cloud Config
提供 ConfigTokenProvider 自定义功能
修复 KeyStoreTestEncryptorLocator 的性能问题
#### Spring Cloud OpenFeign
升级 OpenFegin 10.7.4
支持 Spring @MatrixVariable 注解,关于这个注解使用可以参考 SpringMVC之matrixVariable
#### Spring Cloud Gateway
增加 RetryFilter
RouteDefinition 增加服务实例的元数据
支持低于 1 req/s 条件限流
#### Spring Cloud CircuitBreaker
支持自定义断路器的实现
Spring Cloud Commons
Spring Cloud LoadBalancer 中添加了对实例运行状况检查的支持, 类是于 Ribbon ServerList 维护
#### 依赖更新
````
<strong>Spring</strong> <strong>Cloud</strong> <strong>Aws</strong> 2.2.1.RELEASE
<strong>Spring</strong> <strong>Cloud</strong> <strong>Vault</strong> 2.2.2.RELEASE (<strong>issues</strong>)
<strong>Spring</strong> <strong>Cloud</strong> <strong>Circuitbreaker</strong> 1.0.2.RELEASE (<strong>issues</strong>)
<strong>Spring</strong> <strong>Cloud</strong> <strong>Cli</strong> 2.2.1.RELEASE
<strong>Spring</strong> <strong>Cloud</strong> <strong>Gateway</strong> 2.2.2.RELEASE (<strong>issues</strong>)
<strong>Spring</strong> <strong>Cloud</strong> <strong>Zookeeper</strong> 2.2.1.RELEASE
<strong>Spring</strong> <strong>Cloud</strong> <strong>Starter</strong> <strong>Hoxton</strong>.SR3
<strong>Spring</strong> <strong>Cloud</strong> <strong>Build</strong> 2.2.3.RELEASE
<strong>Spring</strong> <strong>Cloud</strong> <strong>Config</strong> 2.2.2.RELEASE (<strong>issues</strong>)
<strong>Spring</strong> <strong>Cloud</strong> <strong>Dependencies</strong> <strong>Parent</strong> 2.2.2.RELEASE
<strong>Spring</strong> <strong>Cloud</strong> <strong>Starter</strong> <strong>Hoxton</strong>.SR3
<strong>Spring</strong> <strong>Cloud</strong> <strong>Starter</strong> <strong>Parent</strong> <strong>Hoxton</strong>.SR3
<strong>Spring</strong> <strong>Cloud</strong> <strong>Sleuth</strong> 2.2.2.RELEASE (<strong>issues</strong>)
<strong>Spring</strong> <strong>Cloud</strong> <strong>Contract</strong> 2.2.2.RELEASE (<strong>issues</strong>)
<strong>Spring</strong> <strong>Cloud</strong> <strong>Gcp</strong> 1.2.2.RELEASE
<strong>Spring</strong> <strong>Cloud</strong> <strong>Bus</strong> 2.2.1.RELEASE
<strong>Spring</strong> <strong>Cloud</strong> <strong>Consul</strong> 2.2.2.RELEASE
<strong>Spring</strong> <strong>Cloud</strong> <strong>Stream</strong> <strong>Horsham</strong>.SR3 (<strong>issues</strong>)
<strong>Spring</strong> <strong>Cloud</strong> <strong>Kubernetes</strong> 1.1.2.RELEASE (<strong>issues</strong>)
<strong>Spring</strong> <strong>Cloud</strong> <strong>Release</strong> <strong>Hoxton</strong>.SR3
<strong>Spring</strong> <strong>Cloud</strong> <strong>Openfeign</strong> 2.2.2.RELEASE (<strong>issues</strong>)
<strong>Spring</strong> <strong>Cloud</strong> <strong>Commons</strong> 2.2.2.RELEASE (<strong>issues</strong>)
<strong>Spring</strong> <strong>Cloud</strong> <strong>Task</strong> 2.2.3.RELEASE
<strong>Spring</strong> <strong>Cloud</strong> <strong>Function</strong> 3.0.3.RELEASE
<strong>Spring</strong> <strong>Cloud</strong> <strong>Netflix</strong> 2.2.2.RELEASE (<strong>issues</strong>)
<strong>Spring</strong> <strong>Cloud</strong> <strong>Security</strong> 2.2.1.RELEASE
<strong>Spring</strong> <strong>Cloud</strong> <strong>Release</strong> <strong>Hoxton</strong>.SR3
<strong>Spring</strong> <strong>Cloud</strong> <strong>Cloudfoundry</strong> 2.2.1.RELEASE
````
### 系列教程：
- [1.微服务技术的历史和未来发展趋势](https://blog.csdn.net/xxxlllbbb/article/details/105107628)

- [2.SpringCloud核心功能](https://blog.csdn.net/xxxlllbbb/article/details/105119475)

3.	SpringCloud-Eureka服务注册中心

4.	SpringCloud-Ribbon负载均衡式服务消费者

5.	SpringCloud-Feign声明式服务消费者

6.	SpringCloud-Hystrix 服务熔断器

7.	SpringCloud-Zuul常用路由网关

8.	SpringCloud-Gatway高级路由网关

9.	SpringCloud-Configs 服务配置中心

10.	SpringCloud-Turbine 服务聚合监控

11.	SpringCloud-Sleuth 服务链路追踪

12.	SpringCloud-Security 服务安全策略

13.	SpringCloud-Bus服务消息总线