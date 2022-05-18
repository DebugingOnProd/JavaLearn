package org.lhq.design.state;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StopState implements State{
	@Override
	public void doAction(Context context) {
		log.info("停止状态");
		context.setState(this);
	}
	@Override
	public String toString() {
		return "游戏停止";
	}
}
