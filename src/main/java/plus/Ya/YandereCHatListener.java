package plus.Ya;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import plus.ChatUtl.ChatListener;


public class YandereCHatListener extends ChatListener {

    public YandereCHatListener() {
        super(Main.jda);
    }

    @Override
    public void onMessage(MessageReceivedEvent e) {
        System.out.println(e.getMessage().getContentRaw());
    }
}
