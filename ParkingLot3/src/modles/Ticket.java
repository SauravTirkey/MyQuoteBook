package modles;

import java.util.Date;

public class Ticket extends BaseModel{
    private Date entryTime;
    private String number;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;

    private Gate gate;
    private Operator generatedBy;

}
