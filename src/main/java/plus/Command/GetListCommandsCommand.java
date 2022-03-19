package plus.Command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;


public class GetListCommandsCommand extends DSCommand{

    private final HideCommandManager m;

    protected GetListCommandsCommand(HideCommandManager m) {
        super("commands", "view hide commands");
        this.m = m;
    }


    @Override
    public OptionData[] Options() {
        return new OptionData[0];
    }


    @Override
    public void onCommand(SlashCommandInteractionEvent e) {
        StringBuilder result = new StringBuilder("---- List all commands -----");
        for(String key:m.executors.keySet()){
            HCommandExecutor ex = m.executors.get(key);
            result.append(String.format("\n| cmd: [%s], Permission: %s", key, ex.getPermission()));
        }
        e.reply(result.toString()).queue();
    }
}
