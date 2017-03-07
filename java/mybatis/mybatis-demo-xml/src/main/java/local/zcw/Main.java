package local.zcw;

import local.zcw.dao.UserDao;
import local.zcw.model.User;

/**
 * Created by zcw on 2017/03/05.
 */
public class Main {

    public static void main(String args[]) {
        UserDao userDao = new UserDao();
        User user = userDao.selectUserById(1);
        System.out.println(user.getUsername());

        User user1 = new User(4, "333", 33, "man");
        userDao.insertUser(user1);
        //userDao.updateUser(user1);
    }
}
