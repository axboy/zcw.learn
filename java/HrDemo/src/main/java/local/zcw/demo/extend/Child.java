package local.zcw.demo.extend;

/**
 * Created by zcw on 2017/05/10.
 */
class Child extends Parent {

    public String str = "this is child str";        //本类和父类的单独存储

    Child(){
        System.out.println("Child()");
    }

    public void output() {
        System.out.println("child.output()");       // child.output()
        super.output();                             // parent.output()   this is parent str0
        System.out.println(super.str + 1);          // this is parent str1
        System.out.println(this.str + 2);           // this is child str2
    }
}

class Child1 extends Parent {

    //public String str = "this is child str";

    public void setStr() {
        this.str = "this is child str";
    }

    public void output() {
        System.out.println("child1.output()");      // child1.output()
        super.output();                             // parent.output()    this is child str0
        System.out.println(super.str + 1);          // this is child str1
        System.out.println(this.str + 2);           // this is child str2
    }
}
