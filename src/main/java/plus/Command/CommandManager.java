package plus.Command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import java.util.HashMap;


public abstract class CommandManager extends ListenerAdapter implements AbstractCommandManager{

    protected final HashMap<String, CommandExucator> executors = new HashMap<>();
    protected final JDA jda;


    public CommandManager(JDA jda){
        this.jda = jda;
        CommandListUpdateAction commands = jda.updateCommands();
        CommandExucator[] executors = Commands();
        jda.addEventListener(this);
        for(CommandExucator c:executors){
            addCommand(c);
        }
        commands.addCommands(executors).queue();
    }


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
        if (e.getGuild() == null) return;
        CommandExucator executor = executors.get(e.getName());
        if(executor != null && executor.checkFor(e.getMember()))executor.onCommand(e);
    }


    @Override
    public void addCommand(CommandExucator executor){
        executors.put(executor.getName(), executor);
        CommandListUpdateAction commands = jda.updateCommands();
        commands.addCommands(executor).queue();
        System.out.printf("Command [normal] [%s] loaded!%n", executor.getName());
    }
}
