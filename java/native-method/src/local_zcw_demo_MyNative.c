#include "jni.h"
#include "local_zcw_demo_MyNative.h"
#include <stdio.h>

JNIEXPORT void JNICALL Java_local_zcw_demo_MyNative_sayHello(JNIEnv * env, jclass obj)
{
    printf("hello by c\n");
}