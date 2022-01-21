package org.lhq.jdbc.sql;

import lombok.AllArgsConstructor;
import org.lhq.utils.StringPool;

@AllArgsConstructor
public enum  SqlKeyword implements ISqlFragment {
	AND("AND"),
	OR("OR"),
	NOT("NOT"),
	IN("IN"),
	NOT_IN("NOT IN"),
	LIKE("LIKE"),
	NOT_LIKE("NOT LIKE"),
	EQ(StringPool.EQUALS),
	NE("<>"),
	GT(StringPool.RIGHT_CHEV),
	GE(">="),
	LT(StringPool.LEFT_CHEV),
	LE("<="),
	IS_NULL("IS NULL"),
	IS_NOT_NULL("IS NOT NULL"),
	GROUP_BY("GROUP BY"),
	HAVING("HAVING"),
	ORDER_BY("ORDER BY"),
	EXISTS("EXISTS"),
	NOT_EXISTS("NOT EXISTS"),
	BETWEEN("BETWEEN"),
	NOT_BETWEEN("NOT BETWEEN"),
	ASC("ASC"),
	DESC("DESC");

	private final String keyword;


	@Override
	public String getSqlFragment() {
		return this.keyword;
	}
}
