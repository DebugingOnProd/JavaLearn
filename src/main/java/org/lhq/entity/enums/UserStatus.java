package org.lhq.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: JavaLearn
 * @description:
 * @author: wang.defa
 * @create: 2022-03-16 11:18
 */
@Getter
@AllArgsConstructor
public enum UserStatus {
	ONLINE("在线",200),
	OFFLINE("离线",201);
	private String desc;
	private Integer type;
}
