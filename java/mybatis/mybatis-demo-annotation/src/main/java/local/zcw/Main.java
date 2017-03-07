package local.zcw;

import local.zcw.mapper.UserMapper;
import local.zcw.model.User;
import local.zcw.util.SessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by zcw on 2017/03/05.
 */
public class Main {

    public static void main(String args[]) {
        SqlSession session = SessionFactoryUtil.getSqlSessionIntance();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findById(1);
        System.out.println(user.getUsername());

        User user1 = new User(3, "sss", 33, "man");
        System.out.println("插入结果:" + userMapper.save(user1));
        session.commit();

        session.close();
    }
}
