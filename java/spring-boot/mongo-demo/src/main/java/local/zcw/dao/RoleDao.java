package local.zcw.dao;

import local.zcw.doman.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zcw on 2017/02/24.
 */
@Repository
public interface RoleDao extends MongoRepository<Role, String> {
    Role findByName(String name);
}
