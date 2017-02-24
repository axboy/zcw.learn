package local.zcw.doman;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * Created by zcw on 2017/02/24.
 */
public class Role {

    @Id
    private String id;

    private String name;

    public Role(String name) {
        this.id = new ObjectId().toString();
        this.name = name;
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
