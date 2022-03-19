package plus.YandeRe;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import plus.Utl.SConfig;
import javax.security.auth.login.LoginException;
import java.util.Locale;


public class YaDsBot implements DsBot{

    protected JDA jda;


    public JDA getJDA(){
        return jda;
    }


    public YaDsBot(String name){
        this(name, null);
    }


    public YaDsBot(String name, String token){

        if(name==null || name.isEmpty())throw new NullPointerException("Name can not be empty or null!");
        SConfig c = new SConfig(String.format("BOT_INFO_%s.inf", name.toUpperCase(Locale.ROOT)));
        if(token==null){
            token = c.getString("TOKEN");
            if(token==null){
                c.Write("TOKEN", "your ds token");
                System.out.println("Token not defined! YandeReDS Closed.");
                return;
            }
        } else {
            c.Write("TOKEN", "<defined in constructor>");
        }

        JDABuilder builder = JDABuilder.createDefault(token);
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.NONE);

        try {
            int i = Integer.parseInt(c.getOrWrite("LARGE_THRESHOLD", "50"));
            builder.setLargeThreshold(i);
        } catch (NumberFormatException err){
            c.addComment("<LARGE_THRESHOLD> maybe only integer!");
            builder.setLargeThreshold(50);
        }

        String type = c.getOrWrite("ACTIVITY_TYPE", "NONE", "supported only [PLAYING, STREAMING, WATCHING, COMPETING, NONE]");
        String actv = c.getOrWrite("ACTIVITY_VALUE", "localhost:80", "any string");
        String surl = c.getOrWrite("STREAMING_URL", "none", "used when ACTIVITY_TYPE == STREAMING");

        if(!type.equalsIgnoreCase("NONE")) {
            switch (type) {
                case "PLAYING" -> builder.setActivity(Activity.playing(actv));
                case "STREAMING" -> builder.setActivity(Activity.streaming(actv, surl));
                case "WATCHING" -> builder.setActivity(Activity.watching(actv));
                case "COMPETING" -> builder.setActivity(Activity.competing(actv));
                default -> {
                    System.out.println("Wrong activity type!");
                    c.addComment("<ACTIVITY_TYPE> supported only [PLAYING, STREAMING, WATCHING, COMPETING, NONE]");
                }
            }
        }

        try {
            jda = builder.build();
            onInit();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
