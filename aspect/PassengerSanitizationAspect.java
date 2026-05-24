package org.example.ex2.aspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.ex2.dto.BookTicketRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PassengerSanitizationAspect {

    @Around(
            "execution(* com.example.flightbooking.service.TicketService.bookTicket(..))"
    )
    public Object sanitizePassengerName(
            ProceedingJoinPoint joinPoint
    ) throws Throwable {

        Object[] args = joinPoint.getArgs();

        BookTicketRequest request =
                (BookTicketRequest) args[0];

        String sanitizedName =
                request.getPassengerName()
                        .trim()
                        .toUpperCase();

        request.setPassengerName(sanitizedName);

        args[0] = request;

        log.info(
                "Đã chuẩn hóa passengerName thành: {}",
                sanitizedName
        );

        return joinPoint.proceed(args);
    }

}