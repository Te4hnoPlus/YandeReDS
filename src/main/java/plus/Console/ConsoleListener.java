package plus.Console;

import plus.Utl.Reloadable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class ConsoleListener {

    protected boolean Enabled = false;
    protected HashMap<String, ConsoleCommandExucator> executors = new HashMap<>();

    private static ConsoleListener l;
    public static ConsoleListener getGlobal(){
        return l;
    }


    public static void setGlobal(ConsoleListener listener){
        if(l != null) l.Stop();
        l = listener;
        if(listener != null) l.Enable();
    }


    public ConsoleListener(){
        addCommand(new ParentableConsoleCommand("/reload_all") {
                       @Override
                       public void onSelfCommand(String... args) {
                           Reloadable.reloadAll();
                       }
                   }, new ParentableConsoleCommand("/stop") {
                       @Override
                       public void onSelfCommand(String... args) {}
                        @Override
                        public void onCommand(String... args){
                            System.out.println("Start shutdown...");
                            for(ConsoleCommandExucator c:cmds){
                                c.onCommand(args);
                            }
                            System.exit(0);
                        }
                   }, new ParentableConsoleCommand("/help") {
                       @Override
                       public void onSelfCommand(String... args) {
                           System.out.println("Registered console commands:");
                           for(String s:executors.keySet()){
                               System.out.printf("| - %s%n", s);
                           }
                       }
                   }
        );

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Thread t = new Thread(() -> {
            while (Enabled){
                try {
                    onRawCommand(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Thread.currentThread().stop();
        });
        t.start();
    }


    private void Stop(){
        Enabled = false;
    }


    private void Enable(){
        Enabled = true;
    }


    public void addCommand(ConsoleCommandExucator... executors){
        for(ConsoleCommandExucator e: executors) {
            if(!this.executors.containsKey(e.getName()))
                this.executors.put(e.getName(), e);
            else {
                ConsoleCommandExucator ex = this.executors.get(e.getName());
                if(ex instanceof AbstractParentableConsoleCommand exx){
                    exx.addChildCommand(e);
                }
            }
        }
    }


    protected void onRawCommand(String raw){
        if(!Enabled)return;
        int index = raw.indexOf(' ');
        ConsoleCommandExucator ex;
        if(index>0)
            ex = executors.get(raw.substring(0, raw.indexOf(' ')));
        else ex = executors.get(raw);
        if(ex==null){
            System.out.println("Unknown commands!");
        } else {
            ex.onCommand(index>0? raw.substring(index).strip().split(" "):null);
        }
    }
}