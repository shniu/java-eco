package io.github.shniu.mybatis.domain.mapper;

import io.github.shniu.mybatis.domain.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author niushaohan
 * @date 2020/6/7 13
 */
@Mapper
@Repository
public interface UserMapper {
    @Select("select * from `my_user1` where uid = #{uid}")
    User selectUser(long uid);

    @Select("select * from `my_user1`")
    List<User> selectList();

    @Insert({
            "insert into my_user1 (name, id_card, mobile, email, created_at) VALUES (",
            " #{name}, #{idCard}, #{mobile}, #{email}, #{createdAt}",
            ")"
    })
    @Options(useGeneratedKeys = true, keyProperty = "uid", keyColumn = "uid")
    int insert(User user);
}
