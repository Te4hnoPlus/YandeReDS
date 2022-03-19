package plus.Menus;

import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.internal.interactions.component.SelectMenuImpl;
import plus.Utl.AutoReloadable;
import plus.Utl.LangProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public abstract class HSelectMenu extends AutoReloadable implements AbstractSelectMenu{

    protected final HashMap<String, ChooseLabel> Labels = new HashMap<>();
    protected final String NAME;
    protected final String VIEW;
    protected String vName;
    protected String vView;

    public HSelectMenu(String name, String view){
        NAME = name;
        VIEW = view;
        reload();
    }


    public void reload(){
        Labels.clear();
        vName = LangProvider.GetGlobal().checkPlaceHolders("menu_name_"+NAME+"#"+NAME);
        vView = LangProvider.GetGlobal().checkPlaceHolders("menu_view_"+VIEW+"#"+VIEW);
        for(ChooseLabel l:Labels()){
            Labels.put(l.getValue(), l);
        }
    }


    public SelectMenu ToMenu(){
        List<SelectOption> opts = new ArrayList<>(Labels.values());
        return new SelectMenuImpl(vName, vView, 1, 1, false, opts);
    }


    public void onInteract(String label, SelectMenuInteractionEvent e){
        Labels.get(label).onInteract(e);
    }


    public String genName(){
        return NAME;
    }
}