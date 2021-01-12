package com.xktime.utils;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public class ClassUtil {

    private final static String ROOT_PACKAGE_NAME = "com.xktime";

    public static Set<Class<?>> getClassListByAnnotation(Class<? extends Annotation> annotationClass) {
        try {
            return getClassListByAnnotation(ROOT_PACKAGE_NAME, annotationClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static Set<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        try {
            Reflections reflections = new Reflections(packageName);
            return reflections.getTypesAnnotatedWith(annotationClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
