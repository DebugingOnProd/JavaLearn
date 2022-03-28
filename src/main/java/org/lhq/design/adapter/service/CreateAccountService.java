package org.lhq.design.adapter.service;

import cn.hutool.json.JSONUtil;
import org.lhq.design.adapter.CreateAccount;

public class CreateAccountService {
	public void onMessage(String message) {

		CreateAccount  mq = JSONUtil.toBean(message, CreateAccount.class);

		mq.getNumber();
		mq.getAccountDate();

		// ... 处理自己的业务
	}
}
