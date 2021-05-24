package com.xktime.config.annotation;

import org.springframework.context.ApplicationContext;

@EnumTypeServiceInjector
public interface EnumTypeService {
    void setService(ApplicationContext bean);
}
