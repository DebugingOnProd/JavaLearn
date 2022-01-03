package org.lhq.jdbc.mapping;

import cn.hutool.core.text.CharSequenceUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class ResultSetToEntity {
    /**
     * @param <T>   ：泛型
     * @param clazz ：实体类的Class
     * @param rs    ：查询的结果集
     * @return 返回类型 ：List<T>
     * @方法名 ：resultSetMapToEntityList<br>
     * @方法描述 ：根据结果集（多条数据）映射 到 实体类集合<br>
     * @创建者 ：Andy.wang<br>
     * @创建时间 ：2013-9-4上午10:11:37 <br>
     */
    public static <E> List<E> rsMapToEntityList(Class<?> clazz, ResultSet rs,List<E> list) throws ClassNotFoundException {
        ResultSetMetaData rsmd;
        Method invokeMethod;
        Class<E> resultClass = (Class<E>) Class.forName(clazz.getName());
        try {
            rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            ArrayList<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }
            Map<String, String> fieldNameMap = columnNames.stream().collect(Collectors.toMap(columnName -> columnName, CharSequenceUtil::toCamelCase));
            Field[] declaredFields = resultClass.getDeclaredFields();
            Map<String, ? extends Class<?>> fieldType = Arrays.stream(declaredFields).collect(Collectors.toMap(Field::getName, Field::getType));
            while (rs.next()) {
                E entity = resultClass.getDeclaredConstructor().newInstance();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String columnName = rsmd.getColumnName(i);
                    String methodName = CharSequenceUtil.genSetter(CharSequenceUtil.toCamelCase(columnName));
                    Class<?> type = fieldType.get(fieldNameMap.get(columnName));
                    invokeMethod = clazz.getDeclaredMethod(methodName, type);
                    Object properties = rs.getObject(columnName,type);
                    invokeMethod.invoke(entity, properties);
                }
                list.add(entity);
            }
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | InvocationTargetException | SecurityException | NoSuchMethodException | InstantiationException e) {
            log.error("类型转换发生错误",e);
        }
        return list;
    }

}
