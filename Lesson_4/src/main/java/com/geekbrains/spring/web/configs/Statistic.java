package com.geekbrains.spring.web.configs;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Statistic {

    private long timerProductsService = 0L;
    private long timerOrderService = 0L;
    private long timerUserService = 0L;

    @Around("execution(public * com.geekbrains.spring.web.services.ProductsService.*(..))")
    public Object methodProductsService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        System.out.println((MethodSignature) proceedingJoinPoint.getSignature() + " duration: " + duration);
        timerProductsService = timerProductsService + duration;
        System.out.println("ProductsService: " + timerProductsService);
        return out;
    }

    public long getTimerProductService() {
        return timerProductsService;
    }

    public long getTimerOrderService() {
        return timerOrderService;
    }

    public long getTimerUserService() {
        return timerUserService;
    }


    @Around("execution(public * com.geekbrains.spring.web.services.OrderService.*(..))")
    public Object methodOrderService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        System.out.println((MethodSignature) proceedingJoinPoint.getSignature() + " duration: " + duration);
        timerOrderService = timerOrderService + duration;
        System.out.println("OrderService: " + timerOrderService);
        return out;
    }

    @Around("execution(public * com.geekbrains.spring.web.services.UserService.*(..))")
    public Object methodUserService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        System.out.println((MethodSignature) proceedingJoinPoint.getSignature() + " duration: " + duration);
        timerUserService = timerUserService + duration;
        System.out.println("UserService: " + timerUserService);
        return out;
    }


}
