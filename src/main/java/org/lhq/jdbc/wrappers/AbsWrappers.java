package org.lhq.jdbc.wrappers;

import org.lhq.jdbc.sql.ISqlFragment;
import org.lhq.jdbc.sql.MergeSegments;

public abstract class AbsWrappers<T, R, Children
		extends AbsWrappers<T, R, Children>> extends Wrapper<T>
		implements Compare<Children, R> {

	private T entity;
	protected MergeSegments expression;
	protected final Children typedThis = (Children) this;
	private Class<T> entityClass;



	protected Children doIt(boolean condtion, ISqlFragment... sqlFragment) {
		if (condtion) {
			expression.add(sqlFragment);
		}
		return typedThis;
	}
	public Children setEntity(T entity){
		this.entity = entity;
		return typedThis;
	}

	@Override
	public T getEntity() {
		return entity;
	}

	@Override
	public Children eq(R column, Object val) {
		return null;
	}
}
