# mybatis

[官方文档](http://www.mybatis.org/mybatis-3/zh/index.html) 


- 准备
    创建mybatis数据库，并执行一下sql语句
    ```
    CREATE TABLE `user`(
        `id` INT(8) NOT NULL,
        `username` CHAR(32) NOT NULL,
        `age` INT(3) NOT NULL,
        `sex` CHAR(32) NOT NULL,
        PRIMARY KEY(`id`)
    );
    
    INSERT INTO user (id,username,age,sex) VALUES(1,"zcw",22,"男");
    INSERT INTO user (id,username,age,sex) VALUES(2,"zzz",23,"男");
    ```
 
- 运行
    运行main函数