package plus.BigLabel;


public final class PlaceHolder {

    public final String VAR;
    public final String VALUE;

    public PlaceHolder(String var, String value){
        VAR = var;
        VALUE = value;
    }


    public PlaceHolder(String var, Object value){
        VAR = var;
        VALUE = value.toString();
    }


    public PlaceHolder(String var, int value){
        VAR = var;
        VALUE = String.valueOf(value);
    }


    public PlaceHolder(String var, long value){
        VAR = var;
        VALUE = String.valueOf(value);
    }
}
