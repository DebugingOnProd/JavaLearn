package org.lhq.ioc;

import cn.hutool.core.text.CharSequenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.lhq.anno.AutoInject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
public class BeanFactory {
    private static Map<String, Object> beanCache = new ConcurrentHashMap<>();

    public <T> T getBean(Class<T> clazz) {
        return (T) beanCache.get(clazz.getName());
    }

    public Object getBean(String beanName) {
        return beanCache.get(beanName);
    }


    public void setBean(Class<?> clazz, String beanName, String... dependBeans) {
        Object[] dependBeansInsts = new Object[dependBeans.length];

        for (int i = 0; i < dependBeans.length; i++) {
            dependBeansInsts[i] = beanCache.get(dependBeans[i]);
        }
        Object createBean = null;
        for (Constructor<?> constructor : clazz.getConstructors()) {
            try {
                createBean = constructor.newInstance(dependBeansInsts);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                log.error("创建出现错误", e);
            }
        }
        if (createBean == null) {
            throw new RuntimeException("没有找到合适实例化bean");
        }


        beanCache.put(beanName, createBean);


    }

    public void dependencyInjectionReflection(Object beanInstance, Map<String, Object> beanCache) {
        log.info("反射注入开始");
        List<Field> fields = Arrays.stream(beanInstance.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(AutoInject.class) != null)
                .collect(Collectors.toList());

        fields.forEach(field -> {
            String fieldName = field.getName();
            field.setAccessible(true);
            try {
                field.set(beanInstance, beanCache.get(fieldName));
            } catch (IllegalAccessException e) {
                log.error("反射注入失败自动注入失败", e);
            }
        });
    }

    public void dependencyInjectionSetter(Object beanInstance, Map<String, Object> beanCache) {
        List<Method> methods = Arrays.stream(beanInstance.getClass().getMethods())
                .filter(method -> method.getAnnotation(AutoInject.class) != null)
                .collect(Collectors.toList());
        methods.forEach(method -> {
            String methodName = method.getName();
            String fieldName = CharSequenceUtil.getGeneralField(methodName);
            Object bean = beanCache.get(fieldName);
            try {
                method.invoke(beanInstance, bean);
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error("setter注入失败", e);
            }
        });
    }

    public void dependencyInjectionConstructor(Object beanInstance, Map<String, Object> beanCache) {
        List<Constructor<?>> constructorList = Arrays.stream(beanInstance.getClass().getDeclaredConstructors())
                .filter(constructor -> constructor.getAnnotation(AutoInject.class) != null)
                .collect(Collectors.toList());
        constructorList.forEach(constructor -> {
            Class<?>[] parameterTypes = constructor.getParameterTypes();

        });
    }
}
