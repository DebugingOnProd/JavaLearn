package org.lhq.utils;



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
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author Hades
 */
@Slf4j
public class ConvertUtil {


	private ConvertUtil(){}

    public static <T,R> List<R> resultToList(List<T> originList, Function<T,R> mapper){
        if (originList==null||originList.isEmpty()){
            return new ArrayList<>();
        }
        List<R> newList = new ArrayList<>(originList.size());
        for (T originElement : originList) {
            R newElement = mapper.apply(originElement);
            if (newElement==null){
                continue;
            }
            newList.add(newElement);
        }
        return newList;
    }

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
