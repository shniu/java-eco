package io.github.shniu.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author niushaohan
 * @date 2021/3/30 23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "event")
public class Event {
    @TableId(value = "id", type = IdType.AUTO)
    private long id;
    private String type;
}
