package local.zcw.demo.jpa.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 作者 zcw
 * 时间 2017/9/8 10:24
 * 描述 角色类
 */
@Data
@Table(name = "jpa_role")
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    /**
     * mappedBy定义了roles为双向关系的被维护端，user为维护者
     * 主导权在user
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;
}
