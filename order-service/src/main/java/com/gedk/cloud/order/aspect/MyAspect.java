package com.gedk.cloud.order.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/11 19:23
 */
@Aspect
@Component
@Slf4j
public class MyAspect {
    @Pointcut(" execution(* com.gedk.cloud.order.controller.OrderController.*(..)) ")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnValue = null;
        try {
            returnValue = joinPoint.proceed();
        }finally {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            log.info("方法规则式拦截 " + method.getName());
        }
        return returnValue;
    }
}
