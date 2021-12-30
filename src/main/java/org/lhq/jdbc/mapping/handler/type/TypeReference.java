package org.lhq.jdbc.mapping.handler.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeReference<T> {
    private final Type rawType;

    protected TypeReference() {
        this.rawType = getSuperclassType(getClass());
    }

    Type getSuperclassType(Class<?> clazz) {
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            if (TypeReference.class != genericSuperclass) {
                return getSuperclassType(clazz.getSuperclass());
            }

            throw new RuntimeException(getClass()+"子类 类型引用缺少类型参数,删除或添加其他类型参数");
        }


        Type rawType = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];

        if (rawType instanceof ParameterizedType) {
            rawType = ((ParameterizedType) rawType).getRawType();
        }

        return rawType;

    }
    public final Type getRawType() {
        return rawType;
    }

    @Override
    public String toString() {
        return rawType.toString();
    }
}
