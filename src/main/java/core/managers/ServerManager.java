package core.managers;

import java.io.*;
import java.util.Scanner;

/**
 * Created by ceko on 08/29/2016.
 */
public class ServerManager {

    private static String OS;
    private static String ANDROID_HOME;

    public static String getAndroidHome(){
        if(ANDROID_HOME == null){
            ANDROID_HOME = System.getenv("ANDROID_HOME");
            if(ANDROID_HOME == null) throw new RuntimeException("Failed to find ANDROID_HOME make sure that the environment variable is set");
        }
        return ANDROID_HOME;
    }

    public static String getOS(){
        if(OS == null) OS = System.getenv("os.name");
        return OS;
    }

    public static  boolean isWindows(){
        return getOS().startsWith("Windows");
    }

    public static  boolean isMac(){
        return getOS().startsWith("Mac");
    }

    //Server commands

    public static String runCommand(String command){
        String output = null;
        try {
            Scanner scanner = new Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\\A");
            if(scanner.hasNext()) output = scanner.next();
        }
        catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
        return output;
    }

    //return the working dir and append to it as needed
    public static String getWorkingDir(){
        return System.getProperty("user.dir");
    }

    /*
    @param file The path to the file
     */
    public static String read(File file){
        StringBuilder output = new StringBuilder();
        try{
            String line;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) output.append(line+"\n");
            bufferedReader.close();
        }catch (IOException error){
            error.printStackTrace();
        }
        return output.toString();
    }

    /*
    @param file The path to the file
     */
    public static void write(File file, String content){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"))){
            writer.write(content);
            writer.close();
        }catch (IOException error){
            error.printStackTrace();
        }
    }

}
