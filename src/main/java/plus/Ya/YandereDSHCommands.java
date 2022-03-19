package plus.Ya;


import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import plus.BigLabel.BigLabel;
import plus.ChatUtl.AbstrctChatListener;
import plus.Command.CommandManager;
import plus.Command.HCommandExecutor;
import plus.Command.HideCommand;
import plus.Command.HideCommandManager;
import plus.Ya.MySettings.GetInfoCommand;


public class YandereDSHCommands extends HideCommandManager {
    public YandereDSHCommands(AbstrctChatListener listener, CommandManager... manager) {
        super(Main.jda, listener, manager);

    }

    @Override
    public HCommandExecutor[] Commands() {
        return new HCommandExecutor[]{
                new HideCommand("&joh_bot_inf") {
                    private final BigLabel l = new BigLabel("djoh_info");
                    @Override
                    public void onCommand(MessageReceivedEvent e, String... args) {
                        System.out.println("creating ...");
                        e.getMessage().reply("created!").queue();
                        e.getChannel().sendMessageEmbeds(l.toEmbed()).queue();
                    }
                },
                new HideCommand("/run funka") {
                    private final String format = "```%s```";
                    @Override
                    public void onCommand(MessageReceivedEvent e, String... args) {
                        String[] idr = {"none"};
                        MessageChannel c = e.getChannel();

                        Runnable r = () -> {
                            String id = idr[0];
                            int t = 0;
                            String s = "oOoOoOoOoOoOoOoOoOoO";
                            while (t<30){
                                try {
                                    Thread.sleep(500);
                                    ++t;
                                    String s1 = String.valueOf(s.charAt(0));
                                    s = s.substring(1)+s1;
                                    c.editMessageById(id,String.format(format, s)).queue();
                                } catch (InterruptedException ignored) {}
                            }
                            c.deleteMessageById(id).queue();
                            c.deleteMessageById(e.getMessage().getId()).queue();
                        };
                        MessageAction m = c.sendMessage(String.format(format, "Starting ..."));//.queue();

                        m.queue(response -> {
                            idr[0] = response.getId();
                            Thread th = new Thread(r);
                            th.start();
                        });
                    }
                },
                new GetInfoCommand()
        };
    }
}
