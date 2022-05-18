package org.lhq.design.state;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Context {
	private State state;
}
