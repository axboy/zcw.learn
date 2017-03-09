//
//  MyObject.h
//  oc-helloworld
//
//  Created by zcw on 17/3/9.
//  Copyright © 2017年 local. All rights reserved.
//

#ifndef MyObject_h
#define MyObject_h

#import <Foundation/Foundation.h>

@interface MyObject : NSObject{
    
    @private
    int varInt;
    
    NSString* varStr;
}

//成员方法

- (void) initValue:(int) newInt strValue:(NSString*) newStr;       //测试多个参数

- (int) getVarInt;

- (void) setVarInt: (int) newInt;

- (NSString*) getVarStr;

- (void) setVarStr: (NSString*) newStr;

//类方法
+ (void) funcPrint;         //测试类方法

@end

#endif /* MyObject_h */
