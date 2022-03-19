package plus.Utl;


public abstract class AutoReloadable implements Reloadable{

    public AutoReloadable(){
        Reloadable.add(this);
    }
}