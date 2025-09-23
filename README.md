# JavaEE 电影管理系统

这是一个基于 Spring Boot 的电影管理系统后端项目，提供电影信息管理和在线购票功能。

## 项目特点

- 提供 RESTful API 接口
- 支持电影信息管理
- 实现座位查询和购票功能
- 使用 JPA 进行数据库操作
- 包含跨域访问支持

## 目录结构

- `src/main/java` - Java 源代码
- `src/main/resources` - 配置文件和数据库脚本
- `src/test` - 单元测试

## 主要功能

1. **电影管理**
   - 获取所有电影列表
   - 查询特定电影详细信息
   - 查看电影座位情况

2. **购票系统**
   - 查询电影票信息
   - 实现购票操作
   - 管理座位状态

## API 接口

### 电影相关接口 `/movies`

- `GET /movies` - 获取所有电影列表
- `GET /movies/{id}` - 获取特定电影详细信息
- `GET /movies/{id}/seats` - 查询电影座位情况

### 票务相关接口 `/tickets`

- `GET /tickets/movie/{movieId}` - 查询某个电影的所有票务信息
- `POST /tickets/` - 保存票务信息
- `POST /tickets/buy` - 实现购票操作

## 数据库设计

- `movie` 表 - 存储电影信息
- `ticket` 表 - 存储购票信息，与电影表建立多对一关系

## 技术栈

- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Boot Test
- H2 Database (或您配置的其他数据库)

## 开发环境要求

- Java 17 或更高版本
- Maven 3.8.x
- IDE (IntelliJ IDEA, Eclipse 或其他 Java IDE)
- MySQL 或其他支持的数据库(根据 application.properties 配置)

## 使用说明

1. 克隆项目到本地
2. 配置数据库连接信息(application.properties)
3. 执行数据库脚本(schema.sql 和 data.sql)
4. 运行 Application.java 启动应用
5. 通过 API 接口进行电影管理和购票操作

## 贡献指南

欢迎贡献代码！请在提交 PR 前确保：
- 代码符合 Java 编码规范
- 添加适当的注释和文档
- 包含相关的单元测试
- 不破坏现有功能

## 许可证

本项目采用 MIT 许可证。详见 LICENSE 文件。