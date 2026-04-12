// Build (Complex Object Creation)
class User{
    private String name; 
    private int age; // Encapsulation 
    public static class Builder{
        private String name; 
        private int age; 

        public Builder setName(String name){
            this.name=name; 
            return this; 
        }
        public Builder setAge(int age){
            this.age=age; 
            return this; 
        }
        public User build(){
            User u=new User(); 
            u.name=this.name; 
            u.age=this.age; 
            return u; 
        }
    }
}
// 5 USE CASES 
// 1. HTTP Request Builder  
// 2. Complext DTO 
// 3. Query Builders 
// 4. Configuration Objects 
// 5. API Request Objects 
// Too many constructors params -> then we should think of utiliziing builder 
