import java.util.*;

import javax.sound.midi.SysexMessage;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction; 

// -------------------------------- ENUM ----------------------------
enum OrderStatus {
    CREATED, PREPARING, OUT_FOR_DELIVERY, DELIVERED
}

// -------------------------------- USER ----------------------------
class User {
    String name; 

    public User(String name) {
        this.name = name; 
    }
}

// -------------------------------- RESTAURANT ----------------------
class Restaurant {
    String name; 
    Map<String, Double> menu; 

    public Restaurant(String name) {
        this.name = name; 
        this.menu = new HashMap<>(); 
    }

    public void addItem(String item, double price) {
        menu.put(item, price); 
    }
}

// -------------------------------- DELIVERY AGENT -------------------
class DeliveryAgent {
    String name; 
    boolean available = true; 

    public DeliveryAgent(String name) {
        this.name = name; 
    }
}

// -------------------------------- PAYMENT STRATEGY -----------------
interface PaymentStrategy {
    void pay(double amount); 
}

class CODPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Cash on Delivery: " + amount);
    }
}

class OnlinePayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid Online: " + amount); 
    }
}

// ------------------------------- ORDER ----------------------------
class Order {
    User user; 
    Restaurant restaurant; 
    Map<String, Integer> map; 
    DeliveryAgent agent; 
    OrderStatus status; 
    double total; 

    public Order(User u, Restaurant r, Map<String, Integer> items) {
        this.user = u; 
        this.restaurant = r; 
        this.items = items; 
        this.status = OrderStatus.CREATED; 
        calculateTotal(); 
    }

    private void calculateTotal() {
        total = 0; 
        for (String item : items.keySet()) {
            total += restaurant.menu.get(item) * items.get(item); 
        }
    }

    public void assignAgent(DeliveryAgent agent) {
        this.agent = agent; 
        agent.available = false; 
    }

    public void updateStatus(OrderStatus status) {
        this.status = status; 
    }
}

// -------------------------------------- SERVICE -------------------------
class FoodDeliveryService {
    List<DeliveryAgent> agents; 

    public FoodDeliveryService(List<DeliveryAgent> agents) {
        this.agents = agents; 
    }

    public DeliveryAgent assignAgent() {
        for (DeliveryAgent agent : agents) {
            if (agent.available) return agent; 
        }
        throw new RuntimeException("No agents available");
    }

    public void placeOrder(Order order, PaymentStrategy paymeent) {
        DeliveryAgent agent = assignAgent(); 
        order.assignAgent(agent); 

        payment.pay(order.total);

        order.updateStatus(OrderStatus.PREPARING);
        System.out.println("Order is preparing..........."); 
    }
}
