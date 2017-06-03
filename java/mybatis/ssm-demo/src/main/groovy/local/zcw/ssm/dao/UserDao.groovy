package local.zcw.ssm.dao

import local.zcw.ssm.domain.User

/**
 * Created by zcw on 2017/06/03.
 */
interface UserDao {
    User selectById(int id)

    void insertUser(User user)
}
