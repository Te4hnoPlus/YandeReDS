package plus.Command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import plus.Command.Permissions.Permission;


public interface HCommandExecutor extends Permission {

    void onCommand(MessageReceivedEvent e, String... args);


    String getName();


    default String[] Aliases(){return null;}
}
