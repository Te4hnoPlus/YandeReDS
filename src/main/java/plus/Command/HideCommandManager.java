package plus.Command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import plus.ChatUtl.AbstrctChatListener;
import plus.Utl.Reloadable;
import java.util.HashMap;


public abstract class HideCommandManager extends ListenerAdapter implements HideAbstractCommandManager{

    protected final HashMap<String, HCommandExecutor> executors = new HashMap<>();
    protected final AbstrctChatListener defaultListener;

    public HideCommandManager(JDA jda, AbstrctChatListener listener, CommandManager... manager){
        HCommandExecutor[] commands = Commands();
        for(HCommandExecutor c:commands){
            executors.put(c.getName(), c);
            String[] aliases = c.Aliases();
            if(aliases != null){
                for (String a:aliases){
                    executors.put(a, c);
                }
            }
            System.out.printf(
                    "Command [hide] [%s] aliases=[%s] loaded!%n",
                    c.getName(),
                    aliases==null?"null":
                            aliases.length>3?aliases[0]+", "+aliases[1]+", "+aliases[2]+", and "+(aliases.length-3)+" others.":String.join(", ", aliases)
                    );
        }
        executors.put("&reload_all", new HideCommand("reload") {
            @Override
            public void onCommand(MessageReceivedEvent e, String... args) {
                Reloadable.reloadAll();
                try {
                    Thread.sleep(10000);
                }catch (Exception ignored){}
                e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
            }
        });
        if(manager != null && manager.length==1){
            manager[0].addCommand(new GetListCommandsCommand(this));
        }
        addCommand(new HHCommandList("&commands",this));

        if(listener==null)listener = e -> {System.out.println(e.getMessage().getContentRaw());};
        defaultListener = listener;
        jda.removeEventListener(defaultListener);
        jda.addEventListener(this);
    }


    @Override
    public final void onMessageReceived(MessageReceivedEvent e) {
        try {
            String msg = e.getMessage().getContentRaw();
            int index = msg.indexOf(' ');
            HCommandExecutor ex;
            if(index>0)
            ex = executors.get(msg.substring(0, msg.indexOf(' ')));
            else ex = executors.get(msg);
            if(ex==null){
                defaultListener.onMessage(e);
            } else {
                if(ex.checkFor(e.getMember())) ex.onCommand(e,
                        index>0? msg.substring(index).strip().split(" "):null
                );
            }
        }catch (Exception err){
            err.printStackTrace();
        }
    }


    @Override
    public void addCommand(HCommandExecutor c){
        executors.put(c.getName(), c);
    }
}
