package io.github.shniu.mybatis;

import io.github.shniu.mybatis.domain.entity.User;
import io.github.shniu.mybatis.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author niushaohan
 * @date 2020/6/5 08
 */
@Slf4j
public class BasicMybatisTest {

    @Test
    public void test_sqlSessionFactory() {
        String resource = "mybatis-config.xml";
        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            Configuration configuration = sqlSessionFactory.getConfiguration();
            log.info("configuration: {}", configuration);

            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                User user0 = sqlSession.selectOne("io.github.shniu.mybatis.domain.mapper.UserMapper.selectUser", 100);

                UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
                User user = userMapper.selectUser(10);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_sqlSessionWithDatasource() {

    }
}
