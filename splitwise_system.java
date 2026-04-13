// Entities : 
// USER 
// EXPENSE 
// SPLIT 
// BALANCESHEET 
import java.util.*; 

//-----------------USER--------------
class User{
    String id; 
    String name; 
    public User(String id, String name){
        this.id=id; 
        this.name=name; 
    }
}

//----------------SPLIT---------------
abstract class Split{
    User user; 
    double amount; 

    public Split(User user){
        this.user=user; 
    }

    abstract void calculateAmount(double total); 
}

// Equal Splits 
class EqualSplit extends Split{
    public EqualSplit(User u){
        super(u); 
    }
    void calculateAmount(double total){
        // Handles outside 
    }
}

// Exact Split 
class ExactSplit extends Split{
    public ExactSplit(User user, double amount){
        super(user); 
        this.amount=amount; 
    }
    void calculateAmount(double total){
        // already defined 

    }
}

// Expense 
class Expense{
    User paidBy; 
    double amount; 
    List<Split> splits; 

    public Expense(User paidBy, double amount, List<Split> splits){
        this.paidBy=paidBy; 
        this.amount=amount; 
        this.splits=splits; 
    }
}

// BALANCE SHEET 
class BalanceSheet{
    Map<User, Map<User, Double>> balances=new HashMap<>(); 

    public void updateBalance(User paidBy, List<Split> splits, double total){

        int n=splits.size(); 
        double equalAmount= total/n; 

        for(Split split: splits){
            double amt = (split instanceof ExactSplit) ? split.amount : equalAmount; 

            balances.putIfAbsent(split.user, new HashMap<>()); 
            balances.putIfAbsent(paidBy, new HashMap<>()); 

            balances.get(split.user).put(paidBy, balances.get(split.user).getOrDefault(paidBy, 0.0)+amt);
            balances.get(paidBy).put(split.user, balances.get(paidBy).getOrDefault(split.user, 0.0)-amt); 
        }
    }
}