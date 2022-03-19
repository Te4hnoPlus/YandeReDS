package plus.Command;

public interface AbstractCommandManager {
    CommandExucator[] Commands();
    void addCommand(CommandExucator e);
}