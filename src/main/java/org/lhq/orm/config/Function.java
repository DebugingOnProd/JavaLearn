package org.lhq.orm.config;

import lombok.Data;

@Data
public class Function {
	private String sqlType;
	private String funcName;
	private String sql;
	private Object resultType;
	private String parameterType;
}
