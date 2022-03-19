package plus.BigLabel;

import plus.Utl.SConfig;
import java.io.FileWriter;
import java.io.IOException;


public class BConfig extends SConfig{

    public BConfig(String name) {
        super("label_"+name+".em");
    }


    public String getOrWriteMainFormat(){
        if(super.content.contains("<format>")) {
            return super.content.split("<format>")[1];
        } else {
            try(FileWriter writer = new FileWriter(file, false)) {
                content = content+"\n<format>\nmulti string label value\n```version 1.0 ```\n<format>\n";
                writer.write(content);
                writer.flush();
                return "multi string label value\n```version 1.0 ```\n";
            } catch (IOException e) {
                e.printStackTrace();
                return "multi string label value\n```version 1.0 ```\n";
            }
        }
    }
}