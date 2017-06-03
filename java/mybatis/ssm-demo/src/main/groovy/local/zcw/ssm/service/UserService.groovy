package local.zcw.ssm.service

import local.zcw.ssm.dao.UserDao
import local.zcw.ssm.domain.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.annotation.Resource

/**
 * Created by zcw on 2017/06/03.
 */
@Service("userService")
@Transactional
class UserService {

    @Resource
    UserDao userDao

    User getUserById(int id) {
        return userDao.selectById(id)
    }

    void insertUser(User user) {
        userDao.insertUser(user)
    }
}
