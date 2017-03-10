//
//  main.m
//  oc-helloworld
//
//  Created by zcw on 17/3/9.
//  Copyright © 2017年 local. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "MyObject.h"
#import "People.h"
//#import <stdio.h>

int main(int argc, const char * argv[]) {
    @autoreleasepool {
        // insert code here...
        NSLog(@"Hello, World!\n");
        
        //test c
        printf("c printf func\n");
        int a = 1;
        printf("c output:%d\n",a);
        
        printf("\n=============test cpp\n");
        //test cpp
        //see http://blog.csdn.net/jasonblog/article/details/7880841
        People* p = new People(14);
        printf("people age:%d\n", p->getAge());
        
        printf("\n=============test oc obj\n");
        MyObject * obj = [MyObject new];
        NSString* str = @"hello world\n";
        
        [obj initValue:5 strValue: str];
        [MyObject funcPrint];
    }
    return 0;
}
