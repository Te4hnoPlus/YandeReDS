package Example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import plus.Command.HideCommandManager;
import plus.Menus.AbstractSelectMenu;
import plus.Menus.ChooseLabel;
import plus.Menus.HSelectMenu;
import plus.Menus.MenuController;
import plus.Utl.Emojis;


public class ExampleMenus extends MenuController {

    public ExampleMenus(JDA jda, HideCommandManager m) {
        super(jda, m);
    }


    @Override
    public AbstractSelectMenu[] Menus() {
        return new AbstractSelectMenu[]{

                /*
                You can send this menu to the channel using the hidden command "&install_example_name"
                For each value for which this will be possible, a translation will be added in the configuration file
                */
                new HSelectMenu("example_name", "example_view") {
                    @Override
                    public ChooseLabel[] Labels() {
                        return new ChooseLabel[]{
                                new ChooseLabel("choose_1") {
                                    @Override
                                    public void onInteract(SelectMenuInteractionEvent e) {
                                        //logic on click in this label
                                        e.getChannel().sendMessage("clicked to choose_1 !");
                                    }
                                },
                                new ChooseLabel("choose_2", "description", false /*is default?*/, Emojis.BOOK_1) {
                                    @Override
                                    public void onInteract(SelectMenuInteractionEvent e) {
                                        //logic on click in this label
                                        e.getChannel().sendMessage("clicked to choose_2 !");
                                    }
                                }
                        };
                    }
                }
        };
    }
}
