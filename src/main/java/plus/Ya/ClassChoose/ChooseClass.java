package plus.Ya.ClassChoose;


import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;

import plus.Menus.ChooseLabel;
import plus.Menus.HSelectMenu;
import plus.Menus.MenuController;
import plus.Utl.Emojis;
import plus.Utl.LangProvider;


public class ChooseClass extends HSelectMenu {

    protected String idRoleShooter;
    protected String idRoleWarrior;
    protected String msgFormat;

    public ChooseClass(MenuController m) {
        super("56789067892", "class list");
        reload();
    }


    @Override
    public void reload(){
        super.reload();
        idRoleShooter = LangProvider.GetGlobal().checkPlaceHolders("ch_class_shooter#none");
        idRoleWarrior = LangProvider.GetGlobal().checkPlaceHolders("ch_class_warrior#none");
        msgFormat = LangProvider.GetGlobal().checkPlaceHolders("ch_class_menu_repl_message#Changed!");
    }


    @Override
    public ChooseLabel[] Labels() {
        return new ChooseLabel[]{
                new ClassChooseLabel(this, "warrior" , null, false, Emojis.SWORDS) {
                    @Override
                    public void onInteract(SelectMenuInteractionEvent e) {
                        if(e.getGuild()==null || e.getMember() == null)return;
                        Role r = e.getGuild().getRoleById(menu.idRoleShooter);
                        if(e.getMember().getRoles().contains(r)){
                            e.getGuild().removeRoleFromMember(e.getMember(), r).queue();
                        }
                        e.getGuild().addRoleToMember(
                                e.getMember(),
                                e.getGuild().getRoleById(menu.idRoleWarrior)
                        ).queue();
                        e.reply(String.format(menu.msgFormat, menu.idRoleWarrior)).setEphemeral(true).queue();
                    }
                },
                new ClassChooseLabel(this, "shooter" , null, false, Emojis.BOW) {
                    @Override
                    public void onInteract(SelectMenuInteractionEvent e) {
                        if(e.getGuild()==null || e.getMember() == null)return;
                        Role r = e.getGuild().getRoleById(menu.idRoleWarrior);
                        if(e.getMember().getRoles().contains(r)){
                            e.getGuild().removeRoleFromMember(e.getMember(), r).queue();
                        }
                        e.getGuild().addRoleToMember(
                                e.getMember(),
                                e.getGuild().getRoleById(menu.idRoleShooter)
                        ).queue();
                        e.reply(String.format(menu.msgFormat, menu.idRoleShooter)).setEphemeral(true).queue();
                    }
                }
        };
    }
}