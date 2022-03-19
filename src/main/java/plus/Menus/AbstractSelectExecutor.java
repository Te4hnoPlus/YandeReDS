package plus.Menus;

import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;


public interface AbstractSelectExecutor {
    void onInteract(SelectMenuInteractionEvent e);
}
