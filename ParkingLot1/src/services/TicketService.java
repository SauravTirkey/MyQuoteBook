package services;

import exceptions.GateNotFoundException;
import modles.*;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;
import strategies.SpotAssignmentStrategy;
import strategies.SpotAssignmentStrategyFactory;

import java.util.Date;
import java.util.Optional;

public class TicketService {

    //business logi
    GateRepository gateRepository=new GateRepository();
    VehicleRepository vehicleRepository=new VehicleRepository();
    ParkingLotRepository parkingLotRepository=new ParkingLotRepository();
    TicketRepository ticketRepository=new TicketRepository();

    public TicketService(GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         ParkingLotRepository parkingLotRepository,
                         TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(VehicleType vehicleType
       ,String vehicleNumber,
       String vehicleOwnerName,
        Long gateId) throws GateNotFoundException {


           /*1.Create the ticket object
           *2.assign the spot
           * 3.save the ticket in db
           * 4.return the object
           * */

           Ticket ticket=new Ticket();
           ticket.setEntryTime(new Date());

            Optional<Gate>  gateOptional=gateRepository.findById(gateId);

            if(gateOptional.isEmpty()){
                throw new GateNotFoundException();
            }

            Gate gate=gateOptional.get();
            ticket.setGeneratedAt(gate);
            ticket.setGeneratedBy(gate.getOperator());



            /*
            * Check if the vehicle exists
            * 1.yes get from db
            * put in ticket object
            * 2.No
            * create new vehicle
            * store in db
            * put in vehicle
            * */
//              Optional<Vehicle> vehicleOptional=vehicleRepository
//                      .getVehicleByVehicleNumber(vehicleNumber);
//
//
//              Vehicle savedvehicle;
//              if(vehicleOptional.isEmpty()){
//                  Vehicle vehicle=new Vehicle();
//                  vehicle.setVehicleType(vehicleType);
//                  vehicle.setOwnerName(vehicleOwnerName);
//                  vehicle.setNumber(vehicleNumber);
//                  savedvehicle=vehicleRepository.save(vehicle);
//              }else{
//                  savedvehicle=vehicleOptional.get();
//              }
//               ticket.setVehicle(savedvehicle);

                 Optional<Vehicle> vehicleOptional=vehicleRepository.findVehicleByNumber(vehicleNumber);

                 Vehicle savedVehicle;
                  if(vehicleOptional.isEmpty()){
                      Vehicle vehicle=new Vehicle();
                      vehicle.setVehicleType(vehicleType);
                      vehicle.setOwnerName(vehicleOwnerName);
                      vehicle.setNumber(vehicleNumber);
                      savedVehicle=vehicleRepository.save(vehicle);
                  }else{
                      savedVehicle=vehicleOptional.get();
                  }
                  ticket.setVehicle(savedVehicle);

              /*
              * */
           SpotAssignmentStrategyType spotAssignmentStrategyType=parkingLotRepository.findParkingLotById(gate)
                   .getSpotAssignmentStrategyType();


           SpotAssignmentStrategy spotAssignmentStrategy=
                   SpotAssignmentStrategyFactory.getSpotForType(spotAssignmentStrategyType);


           ticket.setAssignedSpot(spotAssignmentStrategy.getSpot(
                   vehicleType
           ));

           ticket.setNumber("Ticket - "+vehicleNumber);

           return  ticketRepository.saveTicket(ticket);

    }
}
