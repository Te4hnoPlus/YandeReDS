package plus.Command.Permissions;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import java.util.List;


public final class BlockOnly implements Permission{

    private final String UID;

    public BlockOnly(String uid) {
        this.UID = uid;
    }


    public boolean checkFor(Member m) {
        List<Role> rl = m.getRoles();
        for(Role r:rl){
            if(r.getId().equals(UID))return false;
        }
        return true;
    }


    @Override
    public String getPermission() {
        return "Block "+UID;
    }
}
