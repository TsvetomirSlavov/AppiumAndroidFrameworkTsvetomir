package core.constants;

import core.managers.ServerManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ceko on 09/10/2016.
 */
public class Resources {

    //dynamically changing path in case of starting from IDE, cmd jar, Jenkins
    //achieve it with a method getWorkingDir in ServerManager and use it also in other cases for references so i can change dynamically
    public static final String QUEUE = ServerManager.getWorkingDir()+"/src/main/resources/queue.json";

    public static JSONObject getQueue() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(QUEUE));
        //casting
        return (JSONObject) obj;

    }


}
