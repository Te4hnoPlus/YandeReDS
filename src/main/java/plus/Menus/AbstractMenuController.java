package plus.Menus;


public interface AbstractMenuController {
    void addSelectMenu(AbstractSelectMenu menu);
    void addSelectMenu(AbstractSelectMenu menu, InstallationCommand cmd);
    AbstractSelectMenu[] Menus();
}
