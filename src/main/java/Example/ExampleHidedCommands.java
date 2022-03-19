package Example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import plus.BigLabel.BigLabel;
import plus.BigLabel.PlaceHolder;
import plus.ChatUtl.AbstrctChatListener;
import plus.Command.CommandManager;
import plus.Command.HCommandExecutor;
import plus.Command.HideCommand;
import plus.Command.HideCommandManager;


public class ExampleHidedCommands extends HideCommandManager {

    /*
    Responsible for all commands that can be run from the chat.
    This class does all the routine work for you,
    allowing you to focus on implementing business logic.

    If you have a chat listener, pass it as an argument to the constructor
    in order to avoid activating the logic of your chat when using hidden commands.
     */
    public ExampleHidedCommands(JDA jda, AbstrctChatListener listener, CommandManager... manager) {
        super(jda, listener, manager);
    }


    @Override
    public HCommandExecutor[] Commands() {
        return new HCommandExecutor[]{
                new HideCommand("/example_command") {

                    //triggered when User send message which starts with "/example_command"
                    @Override
                    public void onCommand(MessageReceivedEvent e, String... args) {
                        //logic
                        e.getMessage().reply("example_answer").queue();
                    }
                },
                new HideCommand("/example_embed") {

                    /*
                    Automatically creates a formatting and settings file
                    You can use placeholders in the format to add variable values to the result.
                    */
                    private final BigLabel label = new BigLabel("example_embed", "name", "time");


                    @Override
                    public void onInit() {
                        //logic on initialize command
                    }


                    @Override
                    public void onCommand(MessageReceivedEvent e, String... args) {
                        e.getChannel().sendMessageEmbeds(
                                label.toEmbed(
                                        //replaces the registered placeholder with the specified value
                                        new PlaceHolder("name", e.getAuthor().getName()),
                                        new PlaceHolder("time", System.currentTimeMillis())
                                )
                        ).queue();
                    }
                }
        };
    }
}
