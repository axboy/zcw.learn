package local.zcw.demo.extend;

/**
 * Created by zcw on 2017/05/10.
 */
class Parent {
    public String str = "this is parent str";

    Parent() {
        System.out.println("Parent()");
    }

    public void output() {
        System.out.println("parent.output()");
        System.out.println(this.str + 0);
    }
}
