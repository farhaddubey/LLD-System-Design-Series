// What interviewer tests 
// Matching Engine (Driver -> Rider) 
// State Management 
// Strategy Pattern (pricing, matching) 
// Extensibility 
// Step 1. Entities 
// Rider 
// Driver  
// Ride 
// Location 
// MatchingStrategy 
// PricingStrategy 
// ----------------------- RELATIONSHIPS ----------------------------------
// Rider -> Request Ride 
// Driver -> Accepts Ride 
import java.util.*; 
// ----------------------- LOCATION --------------------------------------- 
class Location {
    double x, y; 

    public Location(double x, double y) {
        this.x=x; 
        thix.y=y; 
    }

    public double distance(Location other) {
        return Math.sqrt(Math.pow(x-other.x,2) + Math.pow(y-other.y, 2)); 
    }
}

// ----------------------- DRIVER -----------------------------------------
class Driver {
    String id; 
    Location location; 
    boolean available; 

    public Driver(String id, Location location){
        this.id=id; 
        this.location=location; 
        this.available=available; 
    }
}

// --------------------- RIDER --------------------------------------------
class Rider {
    String id; 
    Location location; 

    public Rider(String id, Location location){
        this.id = id; 
        this.location = location; 
    }
}

// --------------------- RIDE ----------------------------------------------
class Ride {
    Rider rider; 
    Driver driver; 
    double fare; 

    public Ride(Rider r, Driver d, double fare) {
        this.rider = r; 
        this.driver = d; 
        this.fare = fare; 
    }
}

// --------------------- MATCHING STRATEGY ----------------------------------
interface MatchingStrategy {
    Driver matchDriver(Rider rider, List<Driver> drivers); 
}

class NearestDriverStrategy implements MatchingStrategy {
    public Driver matchDriver(Rider rider, List<Driver> drivers) {
        Driver best = null; 
        double minDist = Double.MAX_VALUE; 

        for (Driver d: drivers) {
            if (!d.available) continue; 

            double dist = rider.location.distance(d.distance); 
            if (dist < minDist) {
                minDist = dist; 
                best = d; 
            }
        }
        return best; 
    }
}

// ------------------------- PRICING STRATEGY ------------------------
interface PricingStrategy {
    double calculateFare(double distance); 
}

class SimplePricing implements PricingStrategy {
    public double calculateFare(double distance) {
        return distance * 10; // Bast logic 
    }
}

// -------------------- SERVICE ---------------------------
class RideService {
    List<Driver> drivers; 
    MatchingStrategy matchingStrategy; 
    PricingStrategy pricingStrategy; 

    public RideService(List<Driver> drivers, MatchingStrategy ms, PricingStrategy ps) {
        this.drivers = drivers;  
        this.matchingStrategy = ms; 
        this.pricingStrategy = ps; 
    }

    public Ride requestRide(Rider rider, Location destination) {
        Driver driver = matchingStrategy.matchDriver(rider, drivers); 

        if (driver==null) {
            throw new RuntimeException("No Driver avaialable");
        }

        double distance = rider.location.distance(destination); 
        double fare = pricingStrategy.calculateFare(distance); 

        driver.available = false; 

        System.out.println("Ride booked with Driver " + driver.id); 
        return new Ride(rider, driver, fare); 
    }
}