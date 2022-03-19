package Example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import plus.ChatUtl.ChatListener;


public class ExampleChatListener extends ChatListener {

    /*
    Responsible for the logic of working with all messages.
    This class does all the routine work for you,
    allowing you to focus on implementing business logic.

    Pass hidden commands as an argument to the controller in order to avoid logic activation during command activation.
     */
    public ExampleChatListener(JDA jda) {
        super(jda);
    }

    @Override
    public void onMessage(MessageReceivedEvent e) {
        //logic
    }
}
