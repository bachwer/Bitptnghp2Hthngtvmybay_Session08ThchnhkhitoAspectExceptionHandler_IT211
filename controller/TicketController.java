package org.example.ex2.controller;
import lombok.RequiredArgsConstructor;
import org.example.ex2.dto.BookTicketRequest;
import org.example.ex2.entity.Ticket;
import org.example.ex2.service.TicketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/book")
    public Ticket bookTicket(
            @RequestBody BookTicketRequest request
    ) {

        if (request.getPassengerName() == null
                || request.getPassengerName().trim().isEmpty()) {

            throw new RuntimeException(
                    "Tên hành khách không được để trống"
            );
        }

        return ticketService.bookTicket(request);
    }

    @PostMapping("/cancel/{ticketId}")
    public void cancelTicket(
            @PathVariable Long ticketId
    ) {

        ticketService.cancelTicket(ticketId);

    }

}