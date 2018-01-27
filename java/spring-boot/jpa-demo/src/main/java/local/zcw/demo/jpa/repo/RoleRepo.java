package local.zcw.demo.jpa.repo;

import local.zcw.demo.jpa.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 作者 zcw
 * 时间 2017/9/8 11:10
 * 描述 TODO
 */
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
}
