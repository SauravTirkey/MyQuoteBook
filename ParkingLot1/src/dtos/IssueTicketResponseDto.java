package dtos;

import modles.Ticket;

public class IssueTicketResponseDto {

    private Ticket ticket;
    private ResponseStatus responseStatus;
    private String FailureReason;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getFailureReason() {
        return FailureReason;
    }

    public void setFailureReason(String failureReason) {
        FailureReason = failureReason;
    }
}
