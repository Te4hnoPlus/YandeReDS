package plus.Menus;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import plus.Utl.Emojis;
import plus.Utl.LangProvider;


public abstract class ChooseLabel extends SelectOption implements AbstractSelectExecutor {

    private static int countUsages = 0;

    public ChooseLabel(String label) {
        super(LangProvider.GetGlobal().checkPlaceHolders(label), replace(label));
    }


    public ChooseLabel(String label, String description, boolean isDefault, String e) {
        super(
                LangProvider.GetGlobal().checkPlaceHolders(label),
                replace(label),
                LangProvider.GetGlobal().checkPlaceHolders(description),
                isDefault,
                Emoji.fromUnicode(e)
        );
    }


    public ChooseLabel(String label, String description, boolean isDefault, Emojis e) {
        super(
                LangProvider.GetGlobal().checkPlaceHolders("choosel_label_"+label+"#"+label),
                replace(label),
                description==null?null:LangProvider.GetGlobal().checkPlaceHolders("choosel_discreption_"+description+"#"+label),
                isDefault,
                Emoji.fromUnicode(e.E)
        );
    }


    private static String replace(String s){
        ++countUsages;
        return s.replaceAll("[^A-Za-z0-9]", "")+"_"+ countUsages;
    }
}