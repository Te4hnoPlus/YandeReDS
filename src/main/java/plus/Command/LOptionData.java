package plus.Command;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import plus.Utl.LangProvider;


public class LOptionData extends OptionData {

    public LOptionData(OptionType type, String name, String description) {
        super(type, name, LangProvider.GetGlobal().checkPlaceHolders(description));
    }


    public LOptionData(OptionType type, String name, String description, boolean isRequired) {
        super(type, name, LangProvider.GetGlobal().checkPlaceHolders(description), isRequired);
    }


    public LOptionData(OptionType type, String name, String description, boolean isRequired, boolean isAutoComplete) {
        super(type, name, LangProvider.GetGlobal().checkPlaceHolders(description), isRequired, isAutoComplete);
    }
}
