package plus.Ya.ClassChoose;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import plus.BigLabel.BigLabel;
import plus.Menus.AbstractSelectMenu;
import plus.Menus.InstallationCommand;


public class ChInstallCommand extends InstallationCommand {

    protected final BigLabel l;

    public ChInstallCommand(AbstractSelectMenu menu) {
        super(menu, "created!");
        l = new BigLabel("Class_Choose");
    }


    @Override
    public void onCommand(MessageReceivedEvent e , String... args) {
        System.out.println("Installed!");
        e.getChannel().sendMessageEmbeds(l.toEmbed()).setActionRow(MENU.ToMenu()).queue();

        try {
            Thread.sleep(1000);
        }catch (Exception ignored){}
        e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
    }
}