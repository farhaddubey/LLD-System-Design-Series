import java.util.*; 

// --------------------- STRATEGY --------------
interface NotificationChannel {
    void send(String message); 
}

class EmailNotification implements NotificationChannel {
    public void send(String message) {
        System.out.println("Email Send: " + message); 
    }
}

class SMSNotifation implements NotificationChannel {
    public void send(String message) {
        System.out.println("SMS Sent: " + message); 
    }
}

class PushNotification implements NotificationChannel {
    public void send(String message) {
        System.out.println("Push sent: " + message);  
    }
}

// --------------- USER ----------------------------
class NotificationService { 

    public void sendNotification(User user, String message) {
        user.preferredChannedl.send(msg); 
    }
}