package plus.Command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import plus.Command.Permissions.Permission;


public interface CommandExucator extends SlashCommandData, Permission {
    OptionData[] Options();
    void onCommand(SlashCommandInteractionEvent e);
}
