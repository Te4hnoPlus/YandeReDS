package plus.Command.Permissions;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import java.util.List;


public class BlockMulti implements Permission{

    private final String[] IDS;

    public BlockMulti(String[] args){
        IDS = args;
    }


    public boolean checkFor(Member m) {
        List<Role> rl = m.getRoles();
        for(Role r:rl){
            for (String id: IDS) {
                if(r.getId().equals(id))return false;
            }
        }
        return true;
    }


    @Override
    public String getPermission() {
        String result = "Block "+String.join(", ", new String[]{IDS[0], IDS[1]});
        if(IDS.length>2){
            result = result +String.format("and %s other.", IDS.length-2);
        }
        return result;
    }
}
