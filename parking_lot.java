// PARKING LOT 
// ENUM, ABSTRACTION
import java.util.*;

enum VehicleType {
    CAR, BIKE
}

class Vehicle{
    String number; 
    VehicleType type; 
    public Vehicle(String number, VehicleType type){
        this.number = number; 
        this.type = type; 
    }
}

class ParkingSpot{
    int id; 
    VehicleType type;  // USER DEFINED ENUM 
    Vehicle vehicle; 
    public ParkingSpot(int id, VehicleType type){
        this.id= id; 
        this.type = type; 
    }

    public boolean isFree(){
        return vehicle==null; 
    }

    public boolean park(Vehicle v){
        if(isFree() && v.type==type){
            vehicle=v; 
            return true; 
        }
        return false; 
    }

    public void unpark(){
        vehicle=null; 
    }
}

class ParkingLot{
    List<ParkingSpot> spots=new ArrayList<>(); 

    public void addSpot(ParkingSpot spot){
        spots.add(spot); 
    }

    public void parkVehicle(Vehicle v){
        for(ParkingSpot spot: spots){
            if(spot.park(v)){
                System.out.println("Parked at spot"+ spot.id); 
                return; 
            }
        }
        System.out.println("No spot available");
    }
}