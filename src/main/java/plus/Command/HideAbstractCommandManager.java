package plus.Command;


public interface HideAbstractCommandManager {

    void addCommand(HCommandExecutor c);


    HCommandExecutor[] Commands();
}
