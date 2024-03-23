package controllers;

import dtos.IssueTicketRequestDto;
import dtos.IssueTicketResponseDto;
import services.TicketService;

import java.security.Provider;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }
    public IssueTicketResponseDto issueTicket(IssueTicketRequestDto issueTicketRequestDto){
        IssueTicketResponseDto issueTicketResponseDto =new IssueTicketResponseDto();


        return null;

    }

}
