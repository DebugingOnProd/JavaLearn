package org.lhq.jdbc.sql;

import java.util.function.Predicate;

public enum  MatchSegment {
	GROUP_BY(i -> i == SqlKeyword.GROUP_BY),
	ORDER_BY(i -> i == SqlKeyword.ORDER_BY),
	NOT(i -> i == SqlKeyword.NOT),
	AND(i -> i == SqlKeyword.AND),
	OR(i -> i == SqlKeyword.OR),
	AND_OR(i -> i == SqlKeyword.AND || i == SqlKeyword.OR),
	EXISTS(i -> i == SqlKeyword.EXISTS),
	HAVING(i -> i == SqlKeyword.HAVING),
	APPLY(i -> i == WrapperKeyword.APPLY);

	private final Predicate<ISqlFragment> predicate;

	MatchSegment(Predicate<ISqlFragment> predicate) {
		this.predicate = predicate;
	}

	public boolean match(ISqlFragment segment) {
		return getPredicate().test(segment);
	}

	protected Predicate<ISqlFragment> getPredicate() {
		return predicate;
	}
}
