package org.lhq.design.adapter;

import lombok.Data;

import java.util.Date;


@Data
public class CreateAccount {
	private String number;      // 开户编号
	private String address;     // 开户地
	private Date accountDate;   // 开户时间
	private String desc;        // 开户描述
}
