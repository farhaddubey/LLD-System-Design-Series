import java.util.*; 
// Inheritance & Polymorphism 
abstract class Item{
    String title; 
    String author; 
    int year; 

    public Item(String t, String a, int y){
        this.title=t; 
        this.author=a; 
        this.year=y; 
    }

    abstract void display(); 
}

// Book 
class Book extends Item{
    String genre; 
    String isbn; 

    public Book(String t, String a, int y, String g, String i){
        super(t, a, y); 
        genre=g; 
        isbn=i; 
    }

    void display(){
        System.out.println(title+ " by "+author+" ("+year+")"); 
    }
}

// DVD 
class DVD extends Item{
    int duration; 

    public DVD(String t, String a, int y, int d){
        super(t, a, y); 
        this.duration=d; 
    }

    void display(){
        System.out.println(title+ " by "+author+" ("+year+") "); 
        System.out.println("Duration: "+duration); 
    }
}

class Library{
    List<Item> items=new ArrayList<>(); 

    public void addItem(Item item){
        items.add(item); 
    }

    public void showAll(){
        for(Item item: items){
            item.display();  
        }
    }
}