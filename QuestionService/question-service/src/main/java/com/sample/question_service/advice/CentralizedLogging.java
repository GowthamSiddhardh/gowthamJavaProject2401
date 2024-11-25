package com.sample.question_service.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
@Aspect
@Component
public class CentralizedLogging {
    
	private static final Logger log = LoggerFactory.getLogger(CentralizedLogging.class);
	
	@Pointcut(value = "execution(* com.sample.question_service.*.*.*(..) )")
	public void myPointCut() {
		
	}

	@Around("myPointCut()")
	public static Object Logging(ProceedingJoinPoint joinPoint) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		String className = joinPoint.getTarget().getClass().toString();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		log.info("method invoked : " + className + " : " + methodName + "() " + "arguments "
				+ mapper.writeValueAsString(arguments));
		Object object = joinPoint.proceed();
		log.info(className + " : " + methodName + "() " + "response " + mapper.writeValueAsString(object));
		return object;
	}
}
