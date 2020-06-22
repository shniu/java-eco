package io.github.shniu.coffee.commons.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author niushaohan
 * @date 2020/6/20 14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse {
    @Builder.Default
    private ResultCode code = ResultCode.SUCCESS;
    private String message;

    public boolean succeed() {
        return code == ResultCode.SUCCESS;
    }
}
