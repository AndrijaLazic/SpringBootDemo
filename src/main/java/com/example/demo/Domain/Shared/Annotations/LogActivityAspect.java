package com.example.demo.Domain.Shared.Annotations;

import com.example.demo.Domain.DTO.LogActivityRepo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogActivityAspect {

    @Autowired
    LogActivityRepo logActivityRepo;

    @Around("@annotation(logActivity)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, LogActivity logActivity) throws Throwable {
        long start = System.currentTimeMillis();
        DBOperation operation = logActivity.operation();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
