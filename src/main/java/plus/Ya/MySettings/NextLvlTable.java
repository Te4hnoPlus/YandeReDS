package plus.Ya.MySettings;

public class NextLvlTable{

    private final int[] lvls = new int[90];
    public NextLvlTable(){
        for (int i = 0; i < 90; i++) {
            lvls[i] = (int) (i*i*i*Math.sqrt(i)*0.08+i*8+1);
        }
    }

    public int getLvl(int Exp) {
        if (lvls[45] < Exp) {
            for (int i = 45; i < 90; i++) {
                if (lvls[i] >= Exp) {
                    return ++i;
                }
            }
            return 90;
        } else {
            for (int i = 0; i < 45; i++) {
                if (lvls[i] >= Exp) {
                    return ++i;
                }
            }
        }
        return 46;
    }

    public int getNextLvlExp(int lvl){
        if(lvl<90) {
            return lvls[lvl];
        } else {
            return lvls[89];
        }

    }
    public boolean isLvlUp(int lvl, int exp){
        return exp>=lvls[lvl];
    }
}
