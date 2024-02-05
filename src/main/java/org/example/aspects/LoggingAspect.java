package org.example.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.model.Comment;
import org.springframework.core.annotation.Order;

import java.util.logging.Logger;

@Aspect
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("execution(* org.example.services.*.*(..))")
    @Order(1)
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        System.out.println("Method" + methodName +
                " with parameters" +
                "will execute");

        System.out.println("Сообщение до вызова декарируемого метода");


        Comment comment = new Comment();
        comment.setAuthor("Mr. Smith");
        comment.setText("Two comment");
        Object[] newArguments = {comment};


        Object returnedByMethod = joinPoint.proceed(newArguments);
        System.out.println("Сообщение после декарируемого метода");


        return returnedByMethod;
    }

    @Around("execution(* org.example.services.*.*(..))")
    @Order(2)
    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Метод: " + joinPoint.getSignature().getName() + " - " + elapsedTime + " милсек");
        return result;
    }

    @AfterReturning(value = "@annotation(ToLog)", returning = "returnedValue")
    public void log(Object returnedValue) {
        logger.info("Method executed and returned " + returnedValue);


    }
}
