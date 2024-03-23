package modles;

import java.sql.Time;
import java.util.Date;

public class Payment{
    private Long id;
    private String ReferenceNumber;
    private int amount;
    private Date time;
    private PaymentStatus paymentStatus;
    private PaymentMode paymentMode;

}
