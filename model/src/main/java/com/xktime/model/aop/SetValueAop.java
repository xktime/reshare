package com.xktime.model.aop;

import com.xktime.utils.util.SnowflakeIdUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

@Aspect
@Component
public class SetValueAop {

    @Autowired
    SnowflakeIdUtil snowflakeIdUtil;

    public void setId(Object o) {
        Set<Field> fields = getFields(o.getClass());
        for (Field field : fields) {
            Id id = field.getAnnotation(Id.class);
            if (id == null) {
                continue;
            }
            if (field.getType() != long.class) {
                continue;
            }
            try {
                field.setAccessible(true);
                long snowflakeId = snowflakeIdUtil.nextId();
                field.set(o, snowflakeId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Before(value = "execution(public * com.xktime.model.services..*.save*(..))")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {//todo 未处理批量保存
            if (arg instanceof Collection) {
                Collection collection = (Collection) arg;
                for (Object o : collection) {
                    setId(o);
                }
                continue;
            }
            setId(arg);
        }
    }

    private Set<Field> getFields(Class<?> clazz) {
        Set<Field> fields = new HashSet<>();
        if (clazz == Object.class) {
            return fields;
        }
        Collections.addAll(fields, clazz.getDeclaredFields());
        fields.addAll(getFields(clazz.getSuperclass()));
        return fields;
    }

}
