package plus.Utl.Tmp;

import java.util.HashMap;


public class TemporalMap<Key, Value> {

    protected final HashMap<Key , TemporalObject<Value>> cache;
    protected final int livingTime;
    private final Thread t;


    public TemporalMap(){
        this(TemporalObject.getGlobalLivingTime(), 1000);
    }


    public TemporalMap(int livingTime, final int coolDown){
        this.livingTime = livingTime;
        cache = new HashMap<>(livingTime);
        t = new Thread(() -> {
            while(!cache.isEmpty()){
                try {
                    Thread.sleep(coolDown);
                } catch (InterruptedException ignored) {}
                for(Key key: cache.keySet()){
                    TemporalObject<Value> o = cache.get(key);
                    o.time-=1;
                    if(o.time<1){
                        cache.remove(key);
                    }
                }
            }
        });
    }


    public void put(Key key, Value value){
        cache.put(key, new TemporalObject<>(value));
        if(!t.isAlive()){
            t.start();
        }
    }


    public Value get(Key key){
        return getRaw(key).value;
    }


    public TemporalObject<Value> getRaw(Key key){
        TemporalObject<Value> r = cache.get(key);
        if(r != null)r.regenTime();
        return r;
    }
}