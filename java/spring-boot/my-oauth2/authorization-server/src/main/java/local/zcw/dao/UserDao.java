package local.zcw.dao;

import local.zcw.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import org.springframework.data.repository.CrudRepository;

/**
 * Created by zcw on 2017/02/12.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
