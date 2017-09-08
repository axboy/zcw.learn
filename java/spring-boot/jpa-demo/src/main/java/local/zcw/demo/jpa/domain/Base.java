package local.zcw.demo.jpa.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 作者 zcw
 * 时间 2017/9/8 10:17
 * 描述 测试几个注解
 */
@Data
@Table(name = "jpa_base")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Base {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "hibernate-uuid")
    private String id;

    @CreatedDate
    @Column(name = "createdDate")
    private Date createdDate;

    @CreatedBy
    @Column(name = "createdBy")
    private String createdBy;

    @LastModifiedDate
    @Column(name = "lastModifiedDate")
    private Long lastModifiedDate;

    @LastModifiedBy
    @Column(name = "lastModifiedBy")
    private String lastModifiedBy;

    @Column(name = "deleted")
    private boolean deleted = false;

    private String memo;

    private Date defCreatedDate;

    private Long defCreatedTime;

    @PrePersist
    public void prePersist() {
        defCreatedDate = new Date();
        defCreatedTime = defCreatedDate.getTime();
    }

    @PostPersist
    public void postPersist() {
        System.out.println("postPersist");
    }

    @PreRemove
    public void preRemove() {
        System.out.println("preRemove");
    }

    @PostRemove
    public void postRemove() {
        System.out.println("postRemove");
    }

    @PreUpdate
    public void preUpdate() {
        System.out.println("preUpdate");
    }
}
