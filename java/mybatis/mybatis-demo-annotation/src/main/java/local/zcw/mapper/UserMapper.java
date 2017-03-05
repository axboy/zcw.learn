package local.zcw.mapper;

import local.zcw.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zcw on 2017/03/05.
 */
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    public User findById(Integer id);

    @Insert("insert into user(id,username,age,sex) values(#{id},#{username},#{age},#{sex})")
    public int save(User user);
}
