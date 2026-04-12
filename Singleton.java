// SINGLETON -> Control -> Only One Object 
// There must be only ONE instance globally  -> DB CONNECTION -> LOGGER -> CONFIG 
public class Singleton {
    private static Singleton instance; 
    private Singleton(){} // private constructor 
    public static Singleton getInstance(){
        if(instance==null){
            instance=new Singleton(); 
        }
        return instance; 
    }
}
// 1. Logger System 
// 2. DB Connection Pool 
// 3. Configuration Manager 
// 4. Cache Manager 
// 5. Thread Pool Manager 
// ONLY ONE OBJECT NEEDED ACROSS SYSTEM  
