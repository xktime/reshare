# reshare

一个基于SpringBoot以及Vue的论坛应用
   
## 模块介绍  

##### reshare-web-admin:
   - 后台管理前端页面
   - 基于[ElementUIAdmin](https://github.com/imxiaoer/ElementUIAdmin) 项目修改
    
##### reshare-web-app:
   - 应用前端页面

##### reshare-common:
   - 公共配置
   - 模块端口以及数据源配置都在该模块

##### reshare-model:
   - 实体类模块
    
##### reshare-apis:
   - 公共接口模块
   - 启动模块后访问 [swagger接口文档](http://localhost/article/swagger-ui.html)
    
##### reshare-utils:
   - 公共工具模块
    
##### reshare-eureka-registry:
   - Eureka注册中心
    
##### reshare-crawler:
   - 爬虫模块
   - 技术栈：WebMagic

##### reshare-crawler-python:
   - python3 -m pip3 install -i https://pypi.tuna.tsinghua.edu.cn/simple/ scrapy
   - python3 -m pip3 install  -i https://pypi.tuna.tsinghua.edu.cn/simple/ pymongo
    
##### reshare-article:
   - 文章管理模块
    
##### reshare-comment:
   - 评论管理模块
    
##### reshare-user:
   - 用户管理模块
    
## 快速启动

1. 配置最小开发环境：
    * [MySQL](https://dev.mysql.com/downloads/mysql/)
    * [JDK1.8或以上](http://www.oracle.com/technetwork/java/javase/overview/index.html)
    * [Maven](https://maven.apache.org/download.cgi)
    * [Nodejs](https://nodejs.org/en/download/)
    
2. 使用flyway创建所需数据库,建表语句在reshare-model模块下db/migration
    ```bash
        cd reshare
        mvn flyway:migrate
   
3. 启动reshare-crawler模块爬取文章

4. 启动前端页面
    ```bash
        cd reshare/reshare-web-admin #or reshare/reshare-web-app
        npm run dev
   
5. 启动后端对应所需模块
    