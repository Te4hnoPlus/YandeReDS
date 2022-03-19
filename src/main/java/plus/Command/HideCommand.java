package plus.Command;

import net.dv8tion.jda.api.entities.Member;
import plus.Command.Permissions.Permission;
import plus.Utl.LangProvider;


public abstract class HideCommand implements HCommandExecutor{

    public final String NAME;
    protected final Permission perm;

    public HideCommand(String name){
        this.NAME = name;
        perm = Permission.get(LangProvider.GetGlobal().checkPlaceHolders("hide_command_"+name+"#none"));
        onInit();
    }
    public void onInit(){ }

    public HideCommand(String name, Permission permission){
        this.NAME = name;
        this.perm = permission;
    }

    public final String getName(){
        return NAME;
    }

    @Override
    public boolean checkFor(Member m) {
        return perm.checkFor(m);
    }
    public String getPermission(){
        return perm.getPermission();
    }
}