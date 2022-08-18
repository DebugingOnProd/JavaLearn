package org.lhq.design.factory.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FuckException extends RuntimeException {
	public FuckException(String message) {
		super(message);
	}

}
