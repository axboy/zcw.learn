# Objective-C

- 方法调用：

```
    obj.method(arg0);   -->   [obj mehtod:arg0];
```

- 字符串

```
    NSString* str = @"hello world\n";
```

- 类

    类声明，以@interface开始，@end结束，代码块后面是方法列表

    + 类似于c++的静态方法，- 类似于普通方法
    ```
        @interface MyClass : ParentClass
        {
            NSString* username;
            int age;
        }
        - (MyClass)init:(NSString*)name;
        @end
    ```