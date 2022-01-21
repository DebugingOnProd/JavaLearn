package org.lhq.jdbc.wrappers;

import java.io.Serializable;

public interface Compare<Children, R> extends Serializable {
	Children eq(boolean condition, R column, Object val);
	default Children eq(R column,Object val){
		return eq(true,column,val);
	};
}
