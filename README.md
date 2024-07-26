# darkblue-parent

<p align="center">
    <img src="docs/images/logo-blue.svg" width="200">
</p>

[![License](https://img.shields.io/badge/License-Apache%202-blue)](https://www.apache.org/licenses/LICENSE-2.0)
[![Version](https://img.shields.io/badge/Version-1.0.0-blue)](https://gitee.com/d-blue/darkblue-parent/releases)

### 介绍

文档地址：https://dblue.org/docs/project-docs/quick-start

预览（Angular Demo）：https://angular.darkblue.dblue.org/

### 软件架构

技术架构如下：

![架构](docs/images/architecture.png)

本系统开发时采用的是 DDD 架构，是对 DDD 的一次尝试。架构如下

![DDD架构](docs/images/DDD-architecture.png)

### 安装教程

1. 初始化数据库脚本，数据库脚本在 `darkblue-parent/darkblue-application/src/main/resources/sql/`目录下，`init_ddl.sql`为数据库表结构，`init_data.sql` 为数据初始化脚本。
2. 由于使用了 QueryDSL，在启动项目之前需要先编译代码 `mvn compile`
3. 修改数据库配置，配置文件在：`application-dev.yaml`
4. 启动项目

