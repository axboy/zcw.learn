package local.zcw.initvalue;

/**
 * Created by zcw on 17/3/14.
 */
public class MyClass {
    private String str;

    @Init(value = "zcw")
    public void setStr(String str){
        this.str = str;
    }

    public String getStr(){
        return this.str;
    }

}
