# gradle

- 简介

    在gradle中，有两个基本概念，项目和任务。每一次gradle的构建都包含一个或多个项目。
    
    - 项目
    
        项目是指我们的构建产物（比如jar包）或实施产物（将应用程序部署到生产环境）。一个项目包含一个或多个任务。
        
    - 任务
    
        任务是指不可分的最小工作单元，执行构建工作（比如编译项目或执行测试）

- 配置文件
    
    1. build.gradle
    
        gradle构建脚本，指定了一个项目和它的任务
    
    2. gradle.properties
    
        gradle属性文件，用来配置构建属性

    3. gradle.settings
    
        gradle设置文件，对于只有一个项目的构建而言是可选的。
    
        如果构建中包含多个项目，设置文件是必须的，它描述了哪一个项目参与构建。
    
        每一个多项目的构建都必须在项目建构的根目录中加入一个设置文件。

[参考博客](http://www.cnblogs.com/davenkin/p/gradle-learning-1.html)