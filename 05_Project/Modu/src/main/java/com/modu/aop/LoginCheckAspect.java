package com.modu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginCheckAspect {
    
    @Before("execute(com.modu.controller.Membership.*)")
    private String checkLogin(JoinPoint joinPoint) {
        Object[] object = joinPoint.getArgs();
        System.out.println("###apsect");
        for(int i=0; i<object.length; i++) {
            System.out.println("##"+object[i]);
        }
        
        return "redirect:login";
    }
}
