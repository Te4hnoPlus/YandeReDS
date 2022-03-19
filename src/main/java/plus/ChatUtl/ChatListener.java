package plus.ChatUtl;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public abstract class ChatListener extends ListenerAdapter implements AbstrctChatListener{

    public ChatListener(JDA jda){
        jda.addEventListener(this);
    }


    @Override
    public final void onMessageReceived(MessageReceivedEvent e){
        onMessage(e);
    }
}