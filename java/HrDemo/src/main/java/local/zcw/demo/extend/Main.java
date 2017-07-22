package local.zcw.demo.extend;

/**
 * Created by zcw on 2017/05/10.
 * 测试继承、多态
 */
public class Main {
    //静态方法的行为不具有多态
    //通过super.xxx，不具有多态，域访问操作有编译器解析
    //在子类的构造函数中，必须调用父类的构造函数，且必须在首行。若无，默认会加上
    //
    public static void main(String[] args) {
        Child child = new Child();
        child.output();

        System.out.println("========================分割线");

        Child1 child1 = new Child1();
        child1.setStr();
        child1.output();

        System.out.println("========================分割线");

        Parent parent = new Child();
        parent.output();        //调用的是child.output()

        System.out.println("========================分割线");

        Parent p1 = new Parent();
        System.out.println(p1 instanceof Child);
        if(p1 instanceof Child){
            Child c1 = (Child) p1;
            System.out.println(c1.getClass());
        }

    }
}
