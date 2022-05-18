package org.lhq.design.state;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StartState implements State{
	@Override
	public void doAction(Context context) {
		log.info("开始状态");
		context.setState(this);
	}

	@Override
	public String toString() {
		return "游戏开始";
	}
}
