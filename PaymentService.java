interface PaymentStrategy{
    void pay(); 
}

class UPI implements PaymentStrategy{
    public void pay(){System.out.println("UPI"); }
}

class Card implements PaymentStrategy{
    public void pay(){System.out.println("CARD");}
}

class PaymentService{
    private PaymentStrategy strategy; 
    public PaymentService(PaymentStrategy strategy){
        this.strategy=strategy; 
    }
    public void process(){
        strategy.pay(); 
    }
}

// 5 USE CASES 
// 1. PAYMENT METHODS  
// 2. SORTING STRATEGIES 
// 3. COMPRESSION ALGORITHM 
// 4. AUTHENTICATION METHODS 
// 5. PRICING STRATEGIES 