package org.example.ex2.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketRequest {

    private String flightNumber;

    private String passengerName;

}