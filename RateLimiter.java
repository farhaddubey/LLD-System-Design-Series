import java.util.*; 

class RateLimiter{
    private int limit; 
    private int window; 
    private Map<String, Deque<Long>> requests; 

    public RateLimiter(int limit, int window){
        this.limit=limit; 
        this.window=window; 
        this.requests=new HashMap<>(); 
    }

    public boolean allowRequest(String userId){
        long now=System.currentTimeMillis()/1000; 

        requests.putIfAbsent(userId, new ArrayDeque<>()); 
        Deque<Long> queue=requests.get(userId); 

        while(!queue.isEmpty() && queue.peekFirst()<=now - window){
            queue.pollFirst(); 
        }

        if(queue.size()<limit){
            queue.addLast(now);
            return true; 
        }

        return false; 
    }
}