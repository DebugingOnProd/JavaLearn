package org.lhq.jdbc.wrappers;

import org.lhq.jdbc.wrappers.metadata.TableFieldInfo;

import java.util.function.Predicate;

public interface Query<Children, T, R> {
    //设置要查询的字段
    Children select(R... columns);

    default Children select(Predicate<TableFieldInfo> predicate){
        return select(null,predicate);
    }
    Children select(Class<T> entityClass, Predicate<TableFieldInfo> predicate);
    String getSqlSelect();


}
