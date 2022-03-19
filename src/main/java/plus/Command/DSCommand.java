package plus.Command;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;
import plus.Command.Permissions.Permission;
import plus.Utl.LangProvider;
import plus.Utl.Reloadable;


public abstract class DSCommand extends CommandDataImpl implements CommandExucator, Reloadable {

    protected Permission perm;

    public DSCommand(String name, String description) {
        super(name, LangProvider.GetGlobal().checkPlaceHolders(description));
        Reloadable.add(this);
        reload();
    }


    public void onInit(){

    }


    public void reload(){
        for(OptionData o:Options()){
            this.options.add(o);
        }
        perm = Permission.get(LangProvider.GetGlobal().checkPlaceHolders("command_"+name+"#none"));
        onInit();
    }


    @Override
    public boolean checkFor(Member m) {
        return perm.checkFor(m);
    }


    public String getPermission(){
        return perm.getPermission();
    }
}