package plus.Ya.MySettings;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import plus.BigLabel.BigLabel;
import plus.BigLabel.PlaceHolder;
import plus.Command.HideCommand;
import plus.Utl.Reloadable;
import plus.Utl.SConfig;
import plus.Utl.Sql.SqlClient;
import java.util.List;

public class GetInfoCommand extends HideCommand implements Reloadable {

    private final NextLvlTable lvlTable= new NextLvlTable();
    private final BigLabel l = new BigLabel("userValkiria",
            "nick", "class", "lvl", "exp", "max_exp", "sh_stat", "atk_stat", "eng_stat", "crit_stat"
    );
    private final SConfig c;
    private SqlClient client;


    public GetInfoCommand() {
        super("valkiria_info");
        Reloadable.add(this);
        c = new SConfig("v_info_socket");
        reload();
    }


    @Override
    public void onInit() {
    }


    @Override
    public String[] Aliases(){
        return new String[]{"!v_info", "!v_stats"};
    }


    @Override
    public void onCommand(MessageReceivedEvent e, String... args) {
        String user;
        if(args==null)user = e.getMessage().getMember().getNickname();
        else user = args[0];
        List<String> answer = client.getData(user);
        if(answer==null){
            e.getChannel().sendMessageEmbeds(
                    l.toEmbed(
                            new PlaceHolder("nick", user+" (player not found)"),
                            new PlaceHolder("class", "???"),
                            new PlaceHolder("lvl", "???"),

                            new PlaceHolder("exp", "???"),
                            new PlaceHolder("max_exp", "???"),

                            new PlaceHolder("sh_stat", "???"),
                            new PlaceHolder("atk_stat", "???"),
                            new PlaceHolder("eng_stat", "???"),
                            new PlaceHolder("crit_stat", "???")
                    )
            ).queue();
        } else {
            SimpleValkiria v = new SimpleValkiria(answer.get(0));
            int lvl = lvlTable.getLvl(v.EXP);
            e.getChannel().sendMessageEmbeds(
                    l.toEmbed(
                            new PlaceHolder("nick", user),
                            new PlaceHolder("class", v.CLASS),
                            new PlaceHolder("lvl", lvl),
                            new PlaceHolder("exp", v.EXP),
                            new PlaceHolder("max_exp", lvlTable.getNextLvlExp(lvl)),

                            new PlaceHolder("sh_stat", v.POINTS_SHIELD),
                            new PlaceHolder("atk_stat", v.POINTS_ATTACK),
                            new PlaceHolder("eng_stat", v.POINTS_ENERGY),
                            new PlaceHolder("crit_stat", v.POINTS_CRIICAL)
                    )
            ).queue();
        }
    }


    @Override
    public void reload() {
        String host = c.getOrWrite("host", "localhost");
        int port;
        int cacheTime;
        try {
            port = Integer.parseInt(c.getOrWrite("port", "4971"));
            cacheTime = Integer.parseInt(c.getOrWrite("time_cache", "4971"));
        } catch (Exception e){
            port = 10;
            cacheTime = 180;
            c.Write("port", "10 # only number value!");
            c.Write("time_cache", "180");
        }
        if(cacheTime>0) {
            client = new CashedSql(host, port, cacheTime);
        } else {
            client = new SqlClient(host, port);
        }
    }
}