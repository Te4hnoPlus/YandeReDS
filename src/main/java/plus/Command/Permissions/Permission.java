package plus.Command.Permissions;

import net.dv8tion.jda.api.entities.Member;


public interface Permission {

    boolean checkFor(Member m);


    String getPermission();


    static Permission get(String... args){
        if(args==null || args.length==0)return new AllowAll();
        if(args.length==1){
            if(args[0].equals("none"))return new AllowAll();
            if(args[0].contains(";")) args = args[0].split(";");
        }
        boolean isInvert = false;
        for(String s:args){
            if(s.equals("-i")){
                isInvert = true;
                break;
            }
        }
        if(isInvert){
            if(args.length==1){
                System.out.println("Can not block this permission for all!");
                return new AllowAll();
            } else {
                if(args.length==2){
                    return args[0].equals("-i")?new BlockOnly(args[1]):new BlockOnly(args[0]);
                }else {
                    int i = 0;
                    String[] args2 = new String[args.length - 1];
                    for (String a2 : args) {
                        if (!a2.equals("-i")) args2[i] = a2;
                        ++i;
                    }
                    return new BlockMulti(args2);
                }
            }
        } else {
            if(args.length==1)return new AllowOnly(args[0]);
            else return new AllowMulti(args);
        }
    }
}
