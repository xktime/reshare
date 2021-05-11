package com.xktime.config.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RedisAopService {

//    @Autowired
//    RedisUtil redisUtil;
//
//    @Before("execution(public * com.xktime.model.templet..*.*(..))")
//    public void before(JoinPoint joinPoint){
//        System.out.println("-----------------------------before---------------------------");
//        Object[] args = joinPoint.getArgs();
//        for(Object arg : args) {
//            System.out.println(arg.toString());
//        }
//    }
//
//    @AfterReturning("execution(public * com.xktime.model.templet..*.*(..))")
//    public void after(){
//        System.out.println("-----------------------------after---------------------------");
//    }
}
