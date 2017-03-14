package local.zcw.initvalue;

/**
 * Created by zcw on 17/3/14.
 */
public class Main {

    public static void main(String[] args){
        MyClass myClass = MyFactory.create();
        System.out.println(myClass.getStr());
    }
}
