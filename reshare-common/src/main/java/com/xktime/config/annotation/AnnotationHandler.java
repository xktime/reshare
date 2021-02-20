package com.xktime.config.annotation;

import com.xktime.utils.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Set;

@Component
public class AnnotationHandler {
    @Autowired
    private ApplicationContext bean;

    /**
     * 在@Autowired执行之后会走这个方法
     */
    @PostConstruct
    public void postConstruct() {
        enumTypeServiceInjector();
    }

    private void enumTypeServiceInjector() {
        Set<Class<?>> clazzs = ClassUtil.getClassListByAnnotation(EnumTypeServiceInjector.class);
        if (clazzs == null) {
            return;
        }
        for (Class clazz : clazzs) {
            if (!clazz.isEnum()) {
                continue;
            }
            try {
                Method setService = clazz.getDeclaredMethod("setService", ApplicationContext.class);
                setService.setAccessible(true);
                Enum[] values = (Enum[]) clazz.getEnumConstants();
                for (Enum e : values) {
                    setService.invoke(e, bean);
                }
            } catch (Exception e) {
            }
        }
    }
}
