package io.github.shniu.mybatis.domain.mapper;

import io.github.shniu.mybatis.domain.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author niushaohan
 * @date 2020/6/7 13
 */
public interface UserMapper {
    @Select("select * from `user` where id = #{id}")
    User selectUser(int id);

    @Select("select * from `user`")
    List<User> selectList();
}
