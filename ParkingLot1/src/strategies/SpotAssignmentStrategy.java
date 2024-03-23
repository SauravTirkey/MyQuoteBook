package strategies;

import modles.ParkingSpot;
import modles.VehicleType;

public interface SpotAssignmentStrategy {
   public ParkingSpot getSpot(VehicleType vehicleType);
}
