package com.nepCafe.asmnttracker.interceptors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.nepCafe.asmnttracker.util.Util;

@Aspect
@Component
public class ServiceAspect {

	private static Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

	@Before("execution(* com.nepCafe.asmnttracker.controller.*.*(..))")
	public void beforeAdvice(JoinPoint proceedingJoinPoint) {
		MethodSignature methodSig = (MethodSignature) proceedingJoinPoint.getSignature();
		logger.info("Invoking: {} -> {}", proceedingJoinPoint.getTarget().getClass().getName(), methodSig.getName());
		Object[] args = proceedingJoinPoint.getArgs();
		String[] parametersName = methodSig.getParameterNames();

		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {

				logger.info("{} -> {}", parametersName[i], Util.convert2Json(args[i]));
			}
		}

	}

	@AfterReturning(pointcut = "execution(* com.nepCafe.asmnttracker.controller.*.*(..))", returning = "response")
	public void afterAdvice(JoinPoint proceedingJoinPoint, Object response) {
		MethodSignature methodSig = (MethodSignature) proceedingJoinPoint.getSignature();
		logger.info("Completed {} -> {}", proceedingJoinPoint.getTarget().getClass().getName(), methodSig.getName());
		if(response != null) {
			logger.info("Returned {}", Util.convert2Json(response));			
		}

	}

}
