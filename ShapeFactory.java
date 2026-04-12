// FACTORY (Creating -> Hide Object Creation)
// Mental Model: Don't ask how object is created -> Just to ask for it 
interface Shape{
    void draw(); 
}
class Circle implements Shape{
    public void draw(){System.out.println("Circle"); }
}
class Square implements Shape{
    public void draw(){System.out.println("Square");}
}
class Triangle implements Shape{
    public void draw(){System.out.println("Triangle"); }
}
class StraighLine implements Shape{
    public void draw(){System.out.println("Straight Line");}
}
class ShapeFactory{
    public static Shape getShape(String type){
        if(type.equals("circle")) return new Circle(); 
        if(type.equals("square")) return new Square(); 
        if(type.equals("triangle")) return new Triangle(); 
        if(type.equals("straightline")) return new StraighLine(); 
        return null; 
    }
}
// 5 USE CASES 
// Payment Methods (UPI, Card) 
// Notification (Email, SMS)