package plus.Command.Permissions;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.List;


public final class AllowOnly implements Permission{

    private final String ID;
    public AllowOnly(String uid){
        this.ID = uid;
    }


    public boolean checkFor(Member m) {
        List<Role> rl = m.getRoles();
        for(Role r:rl){
            if(r.getId().equals(ID))return true;
        }
        return false;
    }


    @Override
    public String getPermission() {
        return "Allow "+ ID;
    }
}