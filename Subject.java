import java.util.ArrayList;

interface Observer{
    void update(); 
}

class User implements Observer{
    public void udpate(){
        System.out.println("Notified"); 
    }
}

class Subject {
    List<Observer> list=new ArrayList<>(); 
    void add(Observer o){list.add(o);} 
    void notifyAllUsers(){
        for(Observer o: list){
            o.update();
        }
    }
}