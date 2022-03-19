package plus.Menus;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import plus.Command.HCommandExecutor;
import plus.Command.Permissions.Permission;
import plus.Utl.AutoReloadable;
import plus.Utl.LangProvider;

public class InstallationCommand extends AutoReloadable implements HCommandExecutor {

    protected final AbstractSelectMenu MENU;
    protected final String INSTALL_MESSAGE;
    protected final String INSTALL_COMMAND;
    protected Permission perm;

    public InstallationCommand(AbstractSelectMenu menu, String installMessage){
        MENU = menu;
        INSTALL_MESSAGE = installMessage;
        INSTALL_COMMAND = "&install_"+menu.genName();
        reload();
    }


    @Override
    public void reload() {
        perm = Permission.get(LangProvider.GetGlobal().checkPlaceHolders("install_permission#none"));
    }


    @Override
    public void onCommand(MessageReceivedEvent e , String... args) {
        e.getChannel().sendMessage(INSTALL_MESSAGE).setActionRow(MENU.ToMenu()).queue();
    }


    @Override
    public String getName() {
        return INSTALL_COMMAND;
    }


    @Override
    public boolean checkFor(Member uid) {
        return perm.checkFor(uid);
    }


    @Override
    public String getPermission() {
        return perm.getPermission();
    }
}