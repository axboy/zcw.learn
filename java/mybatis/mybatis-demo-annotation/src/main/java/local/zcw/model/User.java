package local.zcw.model;

/**
 * Created by zcw on 2017/03/05.
 */
public class User {

    private Integer id;

    private String username;

    private Integer age;

    private String sex;

    public User(Integer id, String username, Integer age, String sex) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
