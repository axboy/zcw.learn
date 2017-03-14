package local.zcw.validate;

/**
 * Created by zcw on 17/3/14.
 */
public class Main {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.setStr("12345678111");
        System.out.println(MyCheck.check(myClass));
    }
}
