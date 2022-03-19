package plus.Ya.ClassChoose;

import plus.Menus.ChooseLabel;
import plus.Utl.Emojis;

public abstract class ClassChooseLabel extends ChooseLabel {

    protected final ChooseClass menu;

    public ClassChooseLabel(ChooseClass menu, String label) {
        super(label);
        this.menu = menu;
    }

    public ClassChooseLabel(ChooseClass menu, String label, String description, boolean isDefault, String e) {
        super(label, description, isDefault, e);
        this.menu = menu;
    }

    public ClassChooseLabel(ChooseClass menu, String label, String description, boolean isDefault, Emojis e) {
        super(label, description, isDefault, e);
        this.menu = menu;
    }
}
