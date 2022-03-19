package plus.Utl;


public class LangProvider extends SConfig implements AbstractLangProvider {

    private static AbstractLangProvider l = value -> value;


    public static AbstractLangProvider GetGlobal(){
        return l;
    }


    public static void setGlobalProvider(AbstractLangProvider provider){
        l = provider;
    }


    public LangProvider() {
        super("translations.lang");
    }


    public LangProvider(String filePatch) {
        super(filePatch.contains(".")?filePatch:filePatch+".lang");
    }


    @Override
    public String checkPlaceHolders(String value){
        if(value.contains("#")){
            int index = value.indexOf('#');
            String var = value.substring(0, index);
            String val = value.substring(index+1).strip();
            if(val.isEmpty())val = "none";
            return getOrWrite(var, val);
        } else return value;
    }
}