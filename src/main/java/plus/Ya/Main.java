package plus.Ya;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import plus.Console.ConsoleListener;
import plus.Utl.LangProvider;
import plus.Utl.SConfig;


import javax.security.auth.login.LoginException;

public class Main {

    public static JDA jda;
    public static Guild MainGuild;

    public static void main(String[] args){
        try {
            if(args.length>0) {
                main(args[0]);
            } else {
                main((String) null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String token) throws LoginException {
        SConfig c = new SConfig("TOKEN.bin");
        if(token==null){
            token = c.getString("TOKEN");
            if(token==null){
                c.Write("TOKEN", "your ds token");
                System.out.println("Token not defined! YandeReDS Closed.");
                return;
            }
        }
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.NONE);
        builder.setLargeThreshold(25);
        builder.setActivity(Activity.listening("localhost:80"));

        JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .build();

        try {
            jda = builder.build();

            onPostInit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void onPostInit(){
        while (true) {
            MainGuild = jda.getGuildById("941954418901930034");
            if(MainGuild==null) {
                try {
                    Thread.sleep(500);
                }catch (Exception ignored){}
            }else {
                System.out.println("| Main guild successful loaded!");
                break;
            }
        }
        ConsoleListener.setGlobal(new ConsoleListener());
        LangProvider.setGlobalProvider(new LangProvider());

        YandereDSCommands cmd = new YandereDSCommands();
        YandereCHatListener cht = new YandereCHatListener();
        YandereDSHCommands hc = new YandereDSHCommands(cht, cmd);
        YandereDSMenus mns = new YandereDSMenus(hc);
    }
}
