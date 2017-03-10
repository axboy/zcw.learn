//
//  People.mm
//  oc-helloworld
//
//  Created by zcw on 17/3/10.
//  Copyright © 2017年 local. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "People.h"

People::People(int age)
{
    this->age = age;
}

int People::getAge()
{
    std::cout << "cpp cout age:" << this->age << std::endl;
    return this->age;
}

//void People::setAge(<#int age#>)
//{
//    this->age = age;
//}


