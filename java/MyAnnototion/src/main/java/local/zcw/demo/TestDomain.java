package local.zcw.demo;

/**
 * Created by zcw on 17/3/15.
 */
public class TestDomain {

    private String str;

    @MyCheckAnnotation
    public void setStr(String str){
        this.str = str;
    }

    public String getStr(){
        return this.str;
    }
}
