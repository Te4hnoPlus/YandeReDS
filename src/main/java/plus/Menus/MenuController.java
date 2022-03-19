package plus.Menus;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import plus.Command.HideCommandManager;
import java.util.HashMap;

public abstract class MenuController extends ListenerAdapter implements AbstractMenuController{

    protected final HashMap<String, AbstractSelectMenu> menus = new HashMap<>();
    public final HideCommandManager m;


    public MenuController(JDA jda, HideCommandManager m){
        this.m = m;
        for(AbstractSelectMenu menu:Menus()){
            menus.put(menu.genName(), menu);
            m.addCommand(
                    new InstallationCommand(menu, "** **")
            );
        }
        jda.addEventListener(this);
    }


    public void onSelectMenuInteraction(SelectMenuInteractionEvent e) {
        if(e.getGuild()==null)return;
        System.out.printf("User [%s] click to [%s]%n", e.getMember().getNickname(), e.getComponent().getId());

        menus.get(e.getComponent().getId()).onInteract(e.getSelectedOptions().get(0).getValue(), e);
    }


    public void addSelectMenu(AbstractSelectMenu menu){
        menus.put(menu.genName(), menu);
        m.addCommand(
                new InstallationCommand(menu, "** **")
        );
    }


    public void addSelectMenu(AbstractSelectMenu menu, InstallationCommand cmd){
        menus.put(menu.genName(), menu);
        m.addCommand(cmd);
    }
}