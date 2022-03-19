package plus.Command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public final class HHCommandList extends HideCommand{

    private final HideCommandManager m;

    public HHCommandList(String name, HideCommandManager m) {
        super(name);
        this.m = m;
    }


    @Override
    public void onCommand(MessageReceivedEvent e, String... args) {
        StringBuilder result = new StringBuilder("---- List all commands -----");
        for(String key:m.executors.keySet()){
            HCommandExecutor ex = m.executors.get(key);
            result.append(String.format("\n| cmd: [%s], Permission: %s", key, ex.getPermission()));
        }
        e.getChannel().sendMessage(result.toString()).queue();
    }


    @Override
    public String[] Aliases() {
        return super.Aliases();
    }
}
