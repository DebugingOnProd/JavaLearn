package org.lhq.jdbc.mapping;

import cn.hutool.db.meta.JdbcType;

public class ResultMapping {
    private String property;
    private String column;
    private Class<?> javaType;
    private JdbcType jdbcType;
}
