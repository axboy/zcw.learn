# 字符串

1. String对象是不可变的，修改都是创建全新对象

2. StringBuilder只生成一个对象，如果大致知道字符串长度，预先定义好长度可避免多次重新分配缓冲

3. StringBuilder是java se5引入的，之前用的是StringBuffer，StringBuffer是线程安全，开销会大一些

- String上的操作
方法|参数|应用
---|---|---
构造函数|无、String、StringBuffer、StringBuilder、char[]、byte[]|创建对象
length()||
charAt()||
getChars(),getBytes()||todo
toCharArray()||返回对应的字符数组
...|...|...

