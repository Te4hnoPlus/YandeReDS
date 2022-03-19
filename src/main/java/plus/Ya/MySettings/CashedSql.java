package plus.Ya.MySettings;

import plus.Utl.Sql.SqlClient;
import plus.Utl.Tmp.TemporalMap;
import plus.Utl.Tmp.TemporalObject;

import java.util.List;


public class CashedSql extends SqlClient {

    protected TemporalMap<String, List<String>> cache;

    public CashedSql(String host, int port, int cashedTime /*in seconds*/) {
        super(host, port);
        cache = new TemporalMap<>(cashedTime, 1000);
    }

    @Override
    public List<String> getData(String quest) {
        TemporalObject<List<String>> a = cache.getRaw(quest);
        List<String> result;
        if(a==null){
            result = super.getData(quest);
            cache.put(quest, result);
        } else {
            result = a.value;
        }
        return result;
    }


    @Override
    public void putData(String user, List<String> labels){
        System.out.println("method putData(String, List<String>) not supported.");
    }
}