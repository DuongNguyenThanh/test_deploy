package vn.edu.clevai.quiztest.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component("ActivityLogLogging")
public class Logging {

    @Pointcut("execution(public * *(..))")
    private void anyPublicOperation() {
    }

    @Pointcut("within(vn.edu.clevai.quiztest.controller.*)")
    public void withinControllers() {
    }

    @Pointcut("within(vn.edu.clevai.quiztest.service.*)")
    public void withinServiceImpls() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping) "
            + "|| @annotation(org.springframework.web.bind.annotation.GetMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.PatchMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.PostMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.PutMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void hasRequestMapping() {
    }

    @Before("anyPublicOperation() && withinControllers() && hasRequestMapping()")
    public void beforeControllersMappingMethod(JoinPoint joinPoint) {
        log.info("Before method :" + joinPoint.getSignature());
    }

    @AfterReturning(value = "anyPublicOperation() && withinControllers() && hasRequestMapping()", returning = "returnValue")
    public void afterControllersMappingMethod(JoinPoint joinPoint, Object returnValue) {
        log.info("AfterReturning method :" + jointPointName(joinPoint) + " " + returnValue.toString());
    }

    @AfterThrowing(value = "anyPublicOperation() && withinControllers() && hasRequestMapping()", throwing = "throwable")
    public void afterControllersMappingMethodThrow(JoinPoint joinPoint, Throwable throwable) {
        log.info("AfterThrowing method :" + jointPointName(joinPoint) + " " + throwable.toString());
    }

    private String jointPointName(JoinPoint joinPoint) {
        return joinPoint.getSignature().toShortString();
    }
}
