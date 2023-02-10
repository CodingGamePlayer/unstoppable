package com.example.batisproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DebuggingAspect {

    //대상 메소드 선택

    @Pointcut("execution(* com.example.batisproject.controller..*.*(..))")
    private void cut() {}

    @Pointcut("execution(* com.example.batisproject.service..*.*(..))")
    private void cut1() {}

    // 실행시점 이전 : cut()의 대상이 수행되기 이전에 수행
    /*@Before("cut()")
    public void loggingArgsForController(JoinPoint joinPoint) { // cut()의 대상 메소드
        // 입력값 가져오기
        Object[] args = joinPoint.getArgs();

        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        // 입력값 로깅하기
        for (Object obj : args){
            log.info("{}#{}의 입력값 => {}", className, methodName, obj);
        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint, Object returnObj){
        
        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        // 메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        //반환값 로딩
        log.info("{}#{}의 반환값 => {}", className, methodName, returnObj);


    }

    @Before("cut1()")
    public void loggingArgsForService(JoinPoint joinPoint){

        Object[] args = joinPoint.getArgs();

        String simpleName = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        String name = joinPoint.getSignature()
                .getName();

        log.info("{}#{}의 입력값 => {}", simpleName, name, args);
    }


    @AfterReturning(value = "cut1()", returning = "returnObj")
    public void loggingReturnValueForService(JoinPoint joinPoint, Object returnObj){

        String simpleName = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        String name = joinPoint.getSignature()
                .getName();

        log.info("{}#{}의 반환값 => {}", simpleName, name, returnObj);
    }*/
}
