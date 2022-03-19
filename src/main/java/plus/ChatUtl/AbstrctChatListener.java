package plus.ChatUtl;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public interface AbstrctChatListener {

    void onMessage(MessageReceivedEvent e);
}
