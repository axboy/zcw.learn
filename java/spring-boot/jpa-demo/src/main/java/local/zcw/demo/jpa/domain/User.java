package local.zcw.demo.jpa.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 作者 zcw
 * 时间 2017/9/8 10:24
 * 描述 用户类
 */
@Data
@Table(name = "jpa_user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "jpa_user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    private Dept dept;
}
