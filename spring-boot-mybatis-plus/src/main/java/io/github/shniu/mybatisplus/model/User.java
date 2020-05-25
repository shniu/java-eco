package io.github.shniu.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DROP TABLE IF EXISTS user;
 *
 * CREATE TABLE user
 * (
 * 	id BIGINT(20) NOT NULL COMMENT '主键ID',
 * 	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
 * 	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
 * 	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
 * 	PRIMARY KEY (id)
 * );
 * @author niushaohan (shaohan.niu@dfgroup.pro)
 * @date 2020/4/19 16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String name;
    private int age;
    private String email;

    public void incr() {
        this.age++;
    }
}
