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
    
### 注意

    linux下mysql默认大小写敏感，win和osx不敏感

- docker中mysql区分大小写修改

    [参考](http://linzr33.blog.163.com/blog/static/5738028201482953256877/)
    
    ```
    apt-get update
    apt-get install vim
    vim /etc/mysql/mysql.conf.d/mysqld.cnf
    # 追加一行：lower_case_table_names=1             1表示不区分大小写，2表示区分大小写
    ```