package plus.Ya;

import plus.Command.HideCommandManager;
import plus.Menus.AbstractSelectMenu;
import plus.Menus.MenuController;
import plus.Ya.ClassChoose.ChInstallCommand;
import plus.Ya.ClassChoose.ChooseClass;


public class YandereDSMenus extends MenuController {
    public YandereDSMenus(HideCommandManager m) {
        super(Main.jda, m);
        ChooseClass menu = new ChooseClass(this);
        addSelectMenu(menu, new ChInstallCommand(menu));
        //addSelectMenu();
    }

    @Override
    public AbstractSelectMenu[] Menus() {
        return new AbstractSelectMenu[]{
                //new ChooseClass(this)
        };
    }
}