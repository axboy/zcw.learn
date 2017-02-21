package local.zcw.service;


import local.zcw.dao.UserDao;
import local.zcw.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by zcw on 2016/12/18.
 */
@Repository
public class UserService implements UserDetailsService {

    @Resource
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user; //3
    }
}
