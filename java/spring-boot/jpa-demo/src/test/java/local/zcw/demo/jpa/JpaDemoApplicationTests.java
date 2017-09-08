package local.zcw.demo.jpa;

import local.zcw.demo.jpa.domain.Base;
import local.zcw.demo.jpa.domain.Dept;
import local.zcw.demo.jpa.domain.Role;
import local.zcw.demo.jpa.domain.User;
import local.zcw.demo.jpa.repo.BaseRepo;
import local.zcw.demo.jpa.repo.DeptRepo;
import local.zcw.demo.jpa.repo.RoleRepo;
import local.zcw.demo.jpa.repo.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaDemoApplicationTests {

    @Autowired
    private BaseRepo baseRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private DeptRepo deptRepo;

    @Test
    public void deleteAll() {
        userRepo.deleteAll();
        roleRepo.deleteAll();
        deptRepo.deleteAll();
    }


    @Test
    public void init() {
        Dept dept1 = new Dept();
        dept1.setTitle("dept1");
        deptRepo.save(dept1);
        Dept dept2 = new Dept();
        dept2.setTitle("dept2");
        deptRepo.save(dept2);
        Dept dept3 = new Dept();
        dept3.setTitle("dept3");
        deptRepo.save(dept3);

        Role role1 = new Role();
        role1.setTitle("role1");
        roleRepo.save(role1);
        Role role2 = new Role();
        role2.setTitle("role2");
        roleRepo.save(role2);
        Role role3 = new Role();
        role3.setTitle("role3");
        roleRepo.save(role3);

        User user1 = new User();
        user1.setUsername("name1");
        user1.setPassword("pwd1");
        user1.setDept(dept1);
        List<Role> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);
        user1.setRoles(roles);
        userRepo.save(user1);

        User user2 = new User();
        user2.setUsername("name2");
        user2.setPassword("pwd2");
        user2.setDept(dept2);
        List<Role> roles2 = new ArrayList<>();
        roles2.add(role3);
        user2.setRoles(roles2);
        userRepo.save(user2);
    }

    @Test
    public void testBase() {
        Base base = new Base();
        baseRepo.save(base);
    }

    @Test
    public void testDept() {
        List<Dept> list = deptRepo.findAll();
        list.forEach(dept -> {
            System.out.println(dept.getId());
            System.out.println(dept.getUsers() + "\n");
        });
    }

    @Test
    public void testUser() {
        List<User> list = userRepo.findAll();
        list.forEach(user -> {
            System.out.println(user.getId());
            System.out.println(user.getDept());
            System.out.println(user.getRoles() + "\n");
        });
    }

    @Test
    public void testRole() {
        List<Role> list = roleRepo.findAll();
        list.forEach(role -> {
            System.out.println(role.getId());
            System.out.println(role.getUsers() + "\n");
        });
    }
}
