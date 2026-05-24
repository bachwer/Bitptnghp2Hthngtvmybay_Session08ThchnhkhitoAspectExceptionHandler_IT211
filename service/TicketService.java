package org.example.ex2.service;

import lombok.RequiredArgsConstructor;
import org.example.ex2.Repository.FlightRepository;
import org.example.ex2.Repository.TicketRepository;
import org.example.ex2.dto.BookTicketRequest;
import org.example.ex2.entity.Flight;
import org.example.ex2.entity.Ticket;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class


TicketService {

    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;

    public Ticket bookTicket(BookTicketRequest request) {

        Flight flight = flightRepository
                .findByFlightNumber(request.getFlightNumber())
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy chuyến bay"));

        if (flight.getAvailableSeats() <= 0) {
            throw new RuntimeException("Hết vé!");
        }

        flight.setAvailableSeats(
                flight.getAvailableSeats() - 1
        );

        Ticket ticket = new Ticket();

        ticket.setPassengerName(
                request.getPassengerName()
        );

        ticket.setFlightId(flight.getId());

        ticket.setStatus("BOOKED");

        flightRepository.save(flight);

        return ticketRepository.save(ticket);
    }

    public void cancelTicket(Long ticketId) {

        Ticket ticket = ticketRepository
                .findById(ticketId)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy vé"));

        ticket.setStatus("CANCELED");

        ticketRepository.save(ticket);
    }

}