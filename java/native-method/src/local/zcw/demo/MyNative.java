package local.zcw.demo;

/**
 * 作者 zcw
 * 时间 2017/10/24 18:04
 * 版本 1.0.0
 * 描述 TODO
 */
public class MyNative {

    static {
        System.loadLibrary("MyNative");
    }

    public static native void sayHello();

    public static void main(String[] args) {
        MyNative.sayHello();
    }
}