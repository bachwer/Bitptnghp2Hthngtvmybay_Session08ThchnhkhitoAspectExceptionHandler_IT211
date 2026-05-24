package org.example.ex2.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.ex2.Repository.FlightRepository;
import org.example.ex2.Repository.TicketRepository;
import org.example.ex2.entity.Flight;
import org.example.ex2.entity.Ticket;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class TicketCancellationRuleAspect {

    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;

    @Before(
            "execution(* com.example.flightbooking.service.TicketService.cancelTicket(..)) && args(ticketId)"
    )
    public void validateCancellation(Long ticketId) {

        Ticket ticket = ticketRepository
                .findById(ticketId)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy vé"));

        Flight flight = flightRepository
                .findById(ticket.getFlightId())
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy chuyến bay"));

        long hours =
                Duration.between(
                        LocalDateTime.now(),
                        flight.getDepartureTime()
                ).toHours();

        if (hours < 24) {

            throw new RuntimeException(
                    "Không được hủy vé trước giờ bay dưới 24h"
            );

        }

    }

}