// ELEVATOR SYSTEM 
// State Management 
// Direction Logic 
// Scheduling 

// Entities 
// Elevators 
// Requests 
// Controller 
// Direction 

import java.util.*; 

// --------------------- ENUM ---------------------
enum Direction {
    UP, DOWN, IDLE
}

// --------------------- REQUEST ------------------
class Request {
    int floor; 
    Direction direction; 

    public Request(int floor, Direction direction){
        this.floor=floor; 
        this.direction=direction; 
    }
}

// -------------------- ELEVATOR ------------------
class Elevator {
    int id; 
    int currentFloor; 
    Direction direction; 
    Queue<Integer> requests; 

    public Elevator(int id) {
        this.id=id; 
        this.currentFloor=0; 
        this.direction=Direction.IDLE; 
        this.requests=new LinkedList<>(); 
    }

    public void addRequest(int floor) {
        requests.offer(floor); 
    }

    public void move() {
        if(requests.isEmpty()) {
            direction = Direction.IDLE; 
            return; 
        }

        int target = requests.peek(); 

        if (currentFloor < target) {
            currentFloor++; 
            direction = Direction.UP; 
        } else if (currentFloor > target) {
            currentFloor--; 
            direction = Direction.DOWN; 
        } else {
            requests.poll(); // reached 
        }

        System.out.println("Elevator " + id + " at floor " + currentFloor);
    }
}

// ---------------- CONTROLLER ---------------------
class ElevatorControlller {
    List<Elevator> elevators; 

    public ElevatorController(int n) {
        elevators = new ArrayList<>(); 
        for (int i=0; i<n; i++){
            elevators.add(new Elevator(i)); 
        }
    }

    public Elevator assignElevator(Request request) {
        // Simple strategy: nearest elevator 
        Elevator best = elevators.get(0); 
        int minDist = Integer.MAX_VALUE; 

        for (Elevator e: elevators) {
            int dist = Math.abs(e.currentFloor - request.floor); 
            if (dist < minDist){
                minDist = dist; 
                best = e; 
            }
        }

        best.addRequest(request.floor);
        return best; 
    }
}
