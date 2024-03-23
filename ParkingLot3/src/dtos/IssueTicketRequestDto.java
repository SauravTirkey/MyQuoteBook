package dtos;

import modles.Gate;
import modles.Vehicle;
import modles.VehicleType;

public class IssueTicketRequestDto {
    private VehicleType vehicleType;
    private String vehicleNumber;
    private String ownerName;
    private Long gateId;
}
