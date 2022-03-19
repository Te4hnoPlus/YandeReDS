package plus.Utl.Sql;

import plus.Utl.FastRandom;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SqlClient implements DataFolder {

    protected final String host;
    protected final int port;
    protected BufferedReader in;
    protected BufferedWriter out;

    //connecting to a local YandeRe database
    public SqlClient(String host, int port) {
        if(host==null)host = "localhost";
        try {
            createConnection(host, port);
        } catch (Exception e){
            System.out.printf("Not connection to sql in [%s:%s].%n", host, port);
        }
        this.host = host;
        this.port = port;
    }


    protected void createConnection(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }


    @Override
    public List<String> getData(String quest) {
        try {
            out.write(listToString(Queries.GET, quest)+"\n");
            out.flush();
            String result = in.readLine();
            return stringToList(result);
        } catch (IOException | NullPointerException e){
            try {
                createConnection(host, port);
                out.write(listToString(Queries.GET, quest)+"\n");
                out.flush();
                String result = in.readLine();
                return stringToList(result);
            } catch (Exception ignored){return null;}
        }
    }


    @Override
    public void putData(String user, List<String> labels){
        labels.add(0, user);
        labels.add(0, Queries.PUT);
        String result = listToString(labels)+"\n";
        try {
            out.write(result);
            out.flush();
        } catch (IOException e){
            try {
                createConnection(host, port);
                out.write(result);
                out.flush();
            } catch (Exception err){
                err.printStackTrace();
            }
        }
    }


    protected List<String> stringToList(String rawQuery){
        String sep = String.valueOf(rawQuery.charAt(0));
        rawQuery = rawQuery.substring(1);
        if(rawQuery.equals("null"))return null;
        return new ArrayList<>(Arrays.asList(rawQuery.split(sep)));
    }


    protected String listToString(List<String> args){
        if(args==null)return ";null";
        String sep = String.valueOf(FastRandom.r.nextChar());
        for(String s:args){
            if(s.contains(sep))return listToString(args);
        }
        return sep+String.join(sep, args);
    }


    protected String listToString(String... args){
        if(args==null)return ";null";
        String sep = String.valueOf(FastRandom.r.nextChar());
        for(String s:args){
            if(s.contains(sep))return listToString(args);
        }
        return sep+String.join(sep, args);
    }


}