package org.lhq.jdbc.sql;

public enum SqlTemplate {
    SELECT_ONE("selectOne","查询满足条件的一条数据"," SELECT %s FROM %s "),
    SELECT_LIST("selectList","查询满足条件的所有数据"," SELECT %s FROM %s ");

    private final String method;
    private final String desc;
    private final String sql;

    SqlTemplate(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }
    public String getMethod() {
        return method;
    }

    public String getDesc() {
        return desc;
    }

    public String getSql() {
        return sql;
    }
}
