package plus.Command.Permissions;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import java.util.List;


public final class AllowMulti implements Permission{

    private final String[] IDS;
    public AllowMulti(String[] args){
        IDS = args;
    }


    @Override
    public boolean checkFor(Member m) {
        List<Role> rl = m.getRoles();
        for(Role r:rl){
            for (String id: IDS) {
                if(r.getId().equals(id))return true;
            }
        }
        return false;
    }


    @Override
    public String getPermission() {
        String result = "Allow "+String.join(", ", new String[]{IDS[0], IDS[1]});
        if(IDS.length>2){
            result = result +String.format("and %s other.", IDS.length-2);
        }
        return result;
    }
}
