package plus.Utl.Sql;

import java.util.List;

public interface DataFolder {


    List<String> getData(String quest);


    void putData(String user, List<String> labels);
}
