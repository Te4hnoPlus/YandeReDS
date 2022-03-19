import plus.Utl.Tmp.TemporalMap;


public class Test{

    public static void main(String[] args){
        try {
            main();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main() throws InterruptedException {
        TemporalMap<String, String> t = new TemporalMap<>();

        t.put("test", "testValue");
        System.out.println(t.get("test"));
        Thread.sleep(100*1000);
        System.out.println(t.get("test"));
    }
}
