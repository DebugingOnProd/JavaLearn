package org.lhq.design.observer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OctalObserver extends Observer {
	public OctalObserver(Subject subject){
		this.subject = subject;
		this.subject.attach(this);
	}

	@Override
	public void update() {
		log.info( "八进制字符串: " + Integer.toOctalString( subject.getState() ) );
	}
}
