package local.zcw.dao;

import local.zcw.model.User;
import local.zcw.util.SessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by zcw on 2017/03/05.
 */
public class UserDao {

    SqlSession sqlSession = null;

    public UserDao() {
        sqlSession = SessionFactoryUtil.getSqlSessionIntance();
    }

    /**
     * 根据用户id来查找用户
     *
     * @param id
     * @return
     */
    public User selectUserById(Integer id) {
        return (User) sqlSession.selectOne("local.userMapper.selectById", 1);
    }

    /**
     * 新增用户
     *
     * @param user
     */
    public void insertUser(User user) {
        int result = sqlSession.insert("local.userMapper.insertUser", user);
        System.out.println("插入数据" + result);
    }

    public void updateUser(User user) {
        int result = sqlSession.update("local.userMapper.updateUser", user);
        System.out.print("修改结果" + result);
    }
}
