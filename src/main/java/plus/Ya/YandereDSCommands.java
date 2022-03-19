package plus.Ya;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import plus.Command.CommandExucator;
import plus.Command.CommandManager;
import plus.Command.DSCommand;


public class YandereDSCommands extends CommandManager {

    public YandereDSCommands() {
        super(Main.jda);
    }

    @Override
    public CommandExucator[] Commands() {
        return new CommandExucator[]{
                new DSCommand("role", "choose your role") {
                    private static String SHOOTER = "941963599046389790";
                    private static String SOLIDER = "941957429262975000";

                    @Override
                    public OptionData[] Options() {
                        return new OptionData[]{
                                new OptionData(OptionType.STRING, "role", "1-2"),
                                //new OptionsSet(OptionType.NUMBER, "count", "count=users")
                        };
                    }

                    @Override
                    public void onCommand(SlashCommandInteractionEvent e) {
                        if(e.getGuild()==null)return;

                        Guild g = e.getGuild();
                        int choose;

                        if (e.getMember().getRoles().contains(g.getRoleById(SOLIDER))) {
                            choose = 1;
                        } else choose = 2;

                        Role add;
                        Role remove;
                        if(choose != 1){
                            add = g.getRoleById(SOLIDER);
                            remove = g.getRoleById(SHOOTER);
                        } else {
                            add = g.getRoleById(SHOOTER);
                            remove = g.getRoleById(SOLIDER);
                        }


                        g.addRoleToMember(e.getMember(), add).queue();
                        g.removeRoleFromMember(e.getMember(), remove).queue();

                        e.reply(String.format("Choose = [%s]", choose)).queue();
                        //e.getChannel().sendMessage(String.format("Choose = [%s]", choose)).queue();

                        System.out.println("Completed!");

                    }
                }//,

//                new DSCommand("valkiria_info", "cmd_valkiria_info#get valkiria info") {
//
//                    @Override
//                    public OptionData[] Options() {
//                        return new OptionData[]{
//                                new LOptionData(OptionType.STRING, "nick","cmd_valkiria_info_nick#nick", true)
//                        };
//                    }
//
//                    @Override
//                    public void onCommand(SlashCommandInteractionEvent e) {
//                        e.reply(e.getOption("nick").getAsString()).queue();
//                    }
//                }
        };
    }
}
