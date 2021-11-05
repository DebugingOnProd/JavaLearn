package org.lhq.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: JavaLearn
 * @description:
 * @author: wang.defa
 * @create: 2021-11-04 19:24
 */

@Data
@Accessors(chain = true)
public class User {
    private Long userId;
    private String username;
    private String nickname;
}
