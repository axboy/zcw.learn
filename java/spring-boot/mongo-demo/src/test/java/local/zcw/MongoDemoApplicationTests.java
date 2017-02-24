package local.zcw;

import local.zcw.dao.RoleDao;
import local.zcw.dao.UserDao;
import local.zcw.doman.Address;
import local.zcw.doman.Role;
import local.zcw.doman.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDemoApplicationTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Before
    public void setUp() {
    }

    //@Test
    public void init() {
        Role role = new Role("ADMIN");
        roleDao.save(role);
        Role role1 = new Role("ROLE");
        roleDao.save(role1);
    }

    @Test
    public void test() throws Exception {
        System.out.println("start----------------");
        User user = new User("zcw", 23);
        Address address = new Address("浙江省", "杭州市", "江干区");
        user.setAddress(address);
        List<Role> roles = new ArrayList<>();
        roles.add(roleDao.findByName("ADMIN"));
        roles.add(roleDao.findByName("ROLE"));
        user.setRoles(roles);
        userDao.save(user);
    }
}
