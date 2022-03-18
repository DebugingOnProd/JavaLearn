package org.lhq.entity;

import lombok.Data;
import org.lhq.entity.enums.UserStatus;

/**
 * @program: JavaLearn
 * @description:
 * @author: wang.defa
 * @create: 2022-03-16 11:16
 */
@Data
public class ImUser {
	private Integer userId;
	private String username;
	private UserStatus userStatus;
}
