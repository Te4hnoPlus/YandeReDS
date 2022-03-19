package plus.Menus;

import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;


public interface AbstractSelectMenu {

    ChooseLabel[] Labels();


    void onInteract(String label, SelectMenuInteractionEvent e);


    String genName();


    SelectMenu ToMenu();
}
