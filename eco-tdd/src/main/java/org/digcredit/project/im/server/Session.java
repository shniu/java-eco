package org.digcredit.project.im.server;

import lombok.Builder;
import lombok.Data;

/**
 * @author niushaohan
 * @date 2021/2/8 15
 */
@Data
@Builder
public class Session {
    private String userId;
    private String username;
}
