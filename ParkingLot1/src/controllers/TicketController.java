package controllers;

import dtos.IssueTicketRequestDto;
import dtos.IssueTicketResponseDto;
import dtos.ResponseStatus;
import modles.Ticket;
import repositories.TicketRepository;
import services.TicketService;

public class TicketController {
private TicketService ticketService;

public TicketController(TicketService ticketService){
    this.ticketService=ticketService;
}
    public IssueTicketResponseDto issueTicket(IssueTicketRequestDto request){
     IssueTicketResponseDto response=new IssueTicketResponseDto();
        Ticket ticket=null;
        try{
            ticket=ticketService.issueTicket(request.getVehicleType()
                    ,request.getVehicleNumber(),
                    request.getVehicleOwnerName(),
                    request.getGateId());
        }catch(Exception ex){
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setFailureReason(ex.getMessage());
        }
        response.setResponseStatus(ResponseStatus.SUCCESS);
        response.setTicket(ticket);






        return  null;

    }
}
