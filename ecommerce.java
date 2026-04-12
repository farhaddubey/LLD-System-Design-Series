import java.util.*; 

//-------------PRODUCT --------------
class Product{
    private int id; 
    private String name; 
    private double price; 
    public Product(int id, String name, double price){
        this.id=id; 
        this.name=name; 
        this.price=price; 
    }
    public int getId(){return id; }
    public double getPrice(){return price; } 
    public String getName(){ return name; }
}

//------------INVENTORY---------------
class Inventory{
    private Map<Integer, Integer> stock = new HashMap<>(); 
    public void addStock(Product p, int qty){
        stock.put(p.getId(), stock.getOrDefault(p.getId(), 0)+qty); 
    }
    public boolean isAvailable(Product p, int qty){
        return stock.getOrDefault(p.getId(), 0)>=qty; 
    }
    public void reduceStock(Product p, int qty){
        if(isAvailable(p, qty)){
            stock.put(p.getId(), stock.get(p.getId()) - qty); 
        }
    }
}

//-------------CART ITEM ---------------------
class CartItem{
    Product product; 
    int quantity; 
    public CartItem(Product p, int q){
        this.product=p; 
        this.quantity=q; 
    }
}

//--------------CART--------------------------
class Cart{
    private List<CartItem> items = new ArrayList<>(); 

    public void addItem(Product p, int qty){
        for(CartItem item: items){
            if(item.product.getId()==p.getId()){
                item.quantity+=qty; 
                return; 
            }
        }
        items.add(new CartItem(p, qty)); 
    }

    public List<CartItem> getItems(){
        return items; 
    }

    public double getTotal(){
        double total=0; 
        for(CartItem item: items){
            total+=item.product.getPrice()* item.quantity; 
        }
        return total; 
    }
}

//--------------PAYMENT STRATEGY-------------------
interface PaymentStrategy{
    void pay(double amount); 
}

class CreditCartPayment implements PaymentStrategy{
    public void pay(double amount){
        System.out.println("Paid "+ amount + " via Credit Card");
    }
}

class UpiPayment implements PaymentStrategy{
    public void pay(double amount){
        System.out.println("Paid "+amount+" via UPI"); 
    }
}

//-------------------USER---------------------
class User{
    private int id; 
    private String name; 
    private double balance; 
    private Cart cart = new Cart(); 

    public User(int id, String name, double balance){
        this.id=id; 
        this.name=name; 
        this.balance=balance; 
    }

    public Cart getCart(){ return cart; }
    public double getBalance(){ return balance; } 

    public void deductBalance(double amount){
        balance -= amount; 
    }
}

// ------------------ORDER-------------------------
class Order{
    private User user; 
    private Inventory inventory; 
    private PaymentStrategy paymentStrategy; 

    public Order(User user, Inventory inventory, PaymentStrategy ps){
        this.user=user; 
        this.inventory=inventory; 
        this.paymentStrategy=ps; 
    }

    public void placeOrder(){
        double total=user.getCart().getTotal(); 

        // Check Stock 
        for(CartItem item: user.getCart().getItems()){
            if(!inventory.isAvailable(item.product, item.quantity)){
                throw new RuntimeException("Stock not available"); 
            }
        }

        // Checkign balance 
        if(user.getBalance()<total){
            throw new RuntimeException("Insufficiecnt fund");
        }

        // Reducing Stock 
        for(CartItem item: user.getCart().getItems()){
            inventory.reduceStock(item.product, item.quantity);
        }

        // Deduct Balance 
        user.deductBalance(total);

        // Payment 
        paymentStrategy.pay(total); 
        System.out.println("Order placed successfully"); 
    }
}