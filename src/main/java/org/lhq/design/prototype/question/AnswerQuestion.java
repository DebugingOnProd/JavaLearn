package org.lhq.design.prototype.question;

import lombok.Data;

@Data
public class AnswerQuestion {

	private String name;  // 问题
	private String key;   // 答案

	public AnswerQuestion() {
	}

	public AnswerQuestion(String name, String key) {
		this.name = name;
		this.key = key;
	}

	// ...get/set
}
