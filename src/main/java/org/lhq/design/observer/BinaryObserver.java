package org.lhq.design.observer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BinaryObserver extends Observer {
	public BinaryObserver(Subject subject){
		this.subject = subject;
		this.subject.attach(this);
	}

	@Override
	public void update() {
		log.info( "二进制字符串: " + Integer.toBinaryString( subject.getState() ) );
	}
}
