package cn.wazitang.mvc.model;

/**
 * Created by zcw on 2016/12/06.
 */
public class User {

    private String id;

    private String name;

    /**
     * 空构造函数，jackson对对象和json的转换一定要此空构造
     */
    public User() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
