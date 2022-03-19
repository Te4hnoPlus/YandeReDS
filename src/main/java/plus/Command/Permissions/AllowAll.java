package plus.Command.Permissions;

import net.dv8tion.jda.api.entities.Member;


public final class AllowAll implements Permission{

    @Override
    public boolean checkFor(Member m) {
        return true;
    }


    @Override
    public String getPermission() {
        return "Allow All";
    }
}
