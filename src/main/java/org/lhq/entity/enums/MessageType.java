package org.lhq.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: JavaLearn
 * @description:
 * @author: wang.defa
 * @create: 2022-03-16 11:04
 */
@Getter
@AllArgsConstructor
public enum MessageType {
	READ("已读",200),
	SEND("已发送",205),
	DELIVERED("以送达",201),
	HEART_BEAT("心跳",202);
	private String desc;
	private Integer type;

}
