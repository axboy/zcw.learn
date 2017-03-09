//
//  MyObject.m
//  oc-helloworld
//
//  Created by zcw on 17/3/9.
//  Copyright © 2017年 local. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "MyObject.h"

@implementation MyObject

- (int) getVarInt{
    printf("do func getVarInt\n");
    return varInt;
}

- (void) setVarInt:(int)newInt{
    printf("do func setVarInt\n");
    varInt = newInt;
}

- (void) initValue:(int)newInt strValue:(NSString *)newStr{
    printf("do func initValue\n");
    varInt = newInt;
    varStr = newStr;
}

- (void) setVarStr:(NSString *)newStr{
    printf("do setVarStr\n");
    varStr = newStr;
}

- (NSString*) getVarStr{
    return varStr;
}

+ (void) funcPrint{
    printf("print ---------\n");
}

@end
