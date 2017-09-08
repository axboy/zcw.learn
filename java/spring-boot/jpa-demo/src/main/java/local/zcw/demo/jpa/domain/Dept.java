package local.zcw.demo.jpa.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 作者 zcw
 * 时间 2017/9/8 10:25
 * 描述 部门类
 */
@Data
@Table(name = "jpa_dept")
@Entity
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column
    private String title;

    @OneToMany(mappedBy = "dept", fetch = FetchType.EAGER)
    private List<User> users;
}
