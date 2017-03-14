package local.zcw.validate;

/**
 * Created by zcw on 17/3/14.
 */
public class MyClass {

    @Validate(min = 3,max = 6)
    private String str;

    public String getStr(){
        return this.str;
    }

    public void setStr(String str){
        this.str = str;
    }
}
