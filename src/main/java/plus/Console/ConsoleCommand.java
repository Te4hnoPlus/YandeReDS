package plus.Console;

import plus.Utl.AutoReloadable;

public abstract class ConsoleCommand extends AutoReloadable implements ConsoleCommandExucator{

    protected final String name;
    public ConsoleCommand(String name){
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }


    @Override
    public void reload() {
    }
}
