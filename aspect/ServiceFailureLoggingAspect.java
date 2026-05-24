package org.example.ex2.aspect;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.ex2.Repository.ErrorLogRepository;
import org.example.ex2.entity.ErrorLog;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ServiceFailureLoggingAspect {

    private final ErrorLogRepository errorLogRepository;

    @AfterThrowing(
            pointcut =
                    "execution(* org.example.ex2.service..*(..))",
            throwing = "e"
    )
    public void logServiceFailure(
            JoinPoint joinPoint,
            Exception e
    ) {

        ErrorLog errorLog = new ErrorLog();

        errorLog.setTimestamp(LocalDateTime.now());

        errorLog.setMethodName(
                joinPoint.getSignature().getName()
        );

        errorLog.setExceptionMessage(
                e.getMessage()
        );

        errorLogRepository.save(errorLog);

        log.error(
                "SERVICE_ERROR method={} message={}",
                joinPoint.getSignature().getName(),
                e.getMessage(),
                e
        );

    }

}