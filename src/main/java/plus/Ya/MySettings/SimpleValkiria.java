package plus.Ya.MySettings;


public class SimpleValkiria {

    public final String CLASS;
    public final String POINTS_SHIELD;
    public final String POINTS_ATTACK;
    public final String POINTS_CRIICAL;
    public final String POINTS_ENERGY;
    public final int EXP;

    public SimpleValkiria(String rawData){
        String[] args = rawData.split(";");
        CLASS = args[0];
        POINTS_SHIELD = args[2];
        POINTS_ATTACK = args[3];
        POINTS_CRIICAL = args[4];
        POINTS_ENERGY = args[5];
        EXP = Integer.parseInt(args[8]);
    }
}
