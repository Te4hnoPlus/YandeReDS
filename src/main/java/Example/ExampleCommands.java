package Example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import plus.Command.CommandExucator;
import plus.Command.CommandManager;
import plus.Command.DSCommand;
import plus.Command.LOptionData;


public class ExampleCommands extends CommandManager {

    /*
    Responsible for all slash commands.
    This class does all the routine work for you,
    allowing you to focus on implementing business logic.
     */
    public ExampleCommands(JDA jda) {
        super(jda);
    }


    //used only for creating commands
    @Override
    public CommandExucator[] Commands() {

        return new CommandExucator[]{

                new DSCommand("example_command", "example_description") {

                    @Override
                    public OptionData[] Options() {     //used only for creating command, maybe empty.
                        return new OptionData[]{
                                new OptionData(OptionType.STRING, "example_query", "description", true),
                                new LOptionData(        //look like OptionData, but used translations on description
                                        OptionType.STRING,
                                        "example_query",
                                        "description",
                                        false)
                        };
                    }

                    @Override
                    public void onCommand(SlashCommandInteractionEvent e) {
                        //logic on usage this command
                        e.reply("example answer").queue();
                    }

                },

                new DSCommand("example_command_2", "example_description_2") {

                    @Override
                    public OptionData[] Options() {
                        return new OptionData[0];
                    }

                    @Override
                    public void onCommand(SlashCommandInteractionEvent e) {
                        //other logic
                    }
                }
        };
    }
}
