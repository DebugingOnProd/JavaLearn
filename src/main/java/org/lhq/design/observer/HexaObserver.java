package org.lhq.design.observer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HexaObserver extends Observer {
	public HexaObserver(Subject subject){
		this.subject = subject;
		this.subject.attach(this);
	}

	@Override
	public void update() {
		log.info( "十六进制字符串: " + Integer.toHexString( subject.getState() ).toUpperCase() );
	}
}
