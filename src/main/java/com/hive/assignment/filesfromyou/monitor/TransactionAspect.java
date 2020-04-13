package com.hive.assignment.filesfromyou.monitor;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {

  @Around("@annotation(CaptureCustomTransaction)")
  public Object captureCustomTransaction(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
    Transaction transaction = ElasticApm.startTransaction();
    try {
      transaction.setName(proceedingJoinPoint.getSignature().getDeclaringTypeName() + "#" + proceedingJoinPoint.getSignature().getName());
      transaction.setStartTimestamp(System.currentTimeMillis());
      transaction.setUser("1234", "1234@filefromyou.com", "user1234");
    } catch (Exception e) {
      transaction.captureException(e);
    } finally {
      transaction.end();
    }
    return proceedingJoinPoint.proceed();
  }
}
