package org.lhq.jdbc.sql;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum  WrapperKeyword implements ISqlFragment {
	APPLY(null);

	private final String keyword;
	@Override
	public String getSqlFragment() {
		return keyword;
	}
}
