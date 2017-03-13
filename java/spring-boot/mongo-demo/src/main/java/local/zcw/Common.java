package local.zcw;

import org.springframework.data.annotation.*;

import java.util.Date;

/**
 * Created by zcw on 3/13/17.
 */
public class Common {

    @Id
    protected String id;

    /**
     * 创建时间
     */
    @CreatedDate
    protected Date dateCreated;

    /**
     * 创建者的ID
     */
    @CreatedBy
    protected String createdBy;

    /**
     * 最后修改日期
     */
    @LastModifiedDate
    protected Date lastModifiedDate;

    /**
     * 最后更新者的ID
     */
    @LastModifiedBy
    protected String lastModifiedBy;

    /**
     * 是否已经逻辑删除
     */
    protected boolean delete;

    ///////////////////setter、getter方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
