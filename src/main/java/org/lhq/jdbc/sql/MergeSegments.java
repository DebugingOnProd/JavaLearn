package org.lhq.jdbc.sql;


import org.lhq.utils.StringPool;

import java.util.Arrays;
import java.util.List;

public class MergeSegments implements ISqlFragment {
	private String sqlSegment = StringPool.EMPTY;
	private boolean cacheSqlSegment = true;


	public void add(ISqlFragment... iSqlFragments) {
		List<ISqlFragment> sqlFragments = Arrays.asList(iSqlFragments);
		cacheSqlSegment = false;
	}

	@Override
	public String getSqlFragment() {
		if (cacheSqlSegment) {
			return sqlSegment;
		}
		cacheSqlSegment = true;
		return null;
	}
}
