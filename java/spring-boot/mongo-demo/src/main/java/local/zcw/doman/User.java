package local.zcw.doman;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcw on 2017/02/24.
 */
public class User {

    @Id
    private String id;

    private String username;

    private Integer age;

    private Address address;

    private List<Role> roles = new ArrayList<Role>();

    /**
     * 构造函数
     */
    public User(String username, Integer age) {
        this.id = new ObjectId().toString();
        this.username = username;
        this.age = age;
    }

    ////set,get方法
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
