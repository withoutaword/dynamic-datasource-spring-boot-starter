## 介绍
dynamic-datasource-spring-boot-starter 是一个基于springboot的快速集成一主一从读写分离的启动器，适合MyBatis + Druid + MySQL的项目使用。

**示例项目** 可参考项目下的samples目录。
## 使用
1. 下载源码，打包项目到maven本地仓库或私有仓库
2. 引入dynamic-datasource-spring-boot-starter
```
<dependency>
  <groupId>com.zhongxiaohua</groupId>
  <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
  <version>${dynamic-datasource-spring-boot-starter.version}</version>
</dependency>
```
3. 引入相关包

```
<dependency>
  <groupId>org.mybatis.spring.boot</groupId>
  <artifactId>mybatis-spring-boot-starter</artifactId>
  <version>${mybatis.spring.boot.starter.version}</version>
</dependency>
<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>druid-spring-boot-starter</artifactId>
  <version>${druid.spring.boot.starter.version}</version>
</dependency>
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
</dependency>
```

4. 配置数据源

```
spring:
  datasource:
    dynamic:
      # 设置动态切换数据源的切面路径规则，可设置多个
      patterns: com.zhongxiaohua.samples.dao.*
    username: root
    password: root_hua123
    druid:
      # 配置多数据源
      master:
        url: jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&noDatetimeStringSync=true&zeroDateTimeBehavior=convertToNull
      slave:
        url: jdbc:mysql://127.0.0.1:3306/demo_slave?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&noDatetimeStringSync=true&zeroDateTimeBehavior=convertToNull
      #......省略
```
5. 启动时，排除Druid的自动配置类

```
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
```

## 原理
初始化主数据源和从数据源，封装到自定义的数据源，然后通过切面和事务隔离级别在执行数据库操作前判断使用哪个数据源

## 注意
- @Trasactional注解的方法无法切换数据源
- 应该避免在同一个Service方法中写入后立即查询，因为主从同步存在时间差，会有几率查询不出来，如果一定需要，应当在Service方法上添加@Transactional注解以保证主数据源