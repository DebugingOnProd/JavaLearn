package org.lhq.jdbc.wrappers.lambda;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import org.lhq.jdbc.sql.SharedString;
import org.lhq.jdbc.wrappers.Query;
import org.lhq.jdbc.wrappers.metadata.TableFieldInfo;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.function.Predicate;

public class LambdaWrapper<T> extends AbstractLambdaWrapper<T,LambdaWrapper<T>>
		implements Query<LambdaWrapper<T>,T,SFunction<T,?>> {

	/**
	 * 查询字段
	 */
	private SharedString sqlSelect = new SharedString();

	@Override
	public LambdaWrapper<T> select(SFunction<T, ?>... columns) {
		if (CollectionUtil.isNotEmpty(Arrays.asList(columns))){
			//this.sqlSelect.setStringValue();
		}
		return null;
	}

	@Override
	public LambdaWrapper<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
		return null;
	}


	protected String columnsToString(boolean onlyColumn, SFunction<T, ?>... columns){
		//return Arrays.stream(columns).map(item->{})
		return null;
	}

	@Override
	public String getSqlSelect() {
		return sqlSelect.getStringValue();
	}

	private String getColumn(SerializedLambda lambda, boolean onlyColumn) {
		//Class<?> aClass = lambda.getInstantiatedType();
		//tryInitCache(aClass);
		//String fieldName = PropertyNamer.methodToProperty(lambda.getImplMethodName());
		//ColumnCache columnCache = getColumnCache(fieldName, aClass);
		//return onlyColumn ? columnCache.getColumn() : columnCache.getColumnSelect();
		return null;
	}

	@Override
	public LambdaWrapper<T> eq(boolean condition, SFunction<T, ?> column, Object val) {
		return null;
	}
}
