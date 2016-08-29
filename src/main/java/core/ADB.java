package core;

import core.managers.ServerManager;
import org.junit.Test;

/**
 * Created by ceko on 08/28/2016.
 */
public class ADB {

    public static String command(String command){
        if(command.startsWith("adb")) command = command.replace("adb ", ServerManager.getAndroidHome()+"/platform-tools/adb ");
        else throw new RuntimeException("This method is designed to run adb commands only");
        String output = ServerManager.runCommand(command);
        if(output == null) return "";
        else return output;
    }

    @Test
    public void test(){
        System.out.println(command("adb devices"));
    }


}
