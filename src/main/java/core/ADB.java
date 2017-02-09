package core;

import core.managers.ServerManager;
//import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static core.managers.ServerManager.getAndroidHome;

/**
 * FIX ADB.EXE CONFLICT - MOBIZEN DELETE ITS ADB.EXE THERE ARE 2 IN ITS FOLDERS!!!!!!!! THIS FIXED IT
 * FIX ADB CONFLICT OF VERSION BY DELETING ALL DUPLICATES IN TOOLS IN SDK MANAGER!!!!!!!!!!!!!!!
 * utility methods
 * ADD AS MANY AS I NEED LOOK UP ADB COMMANDS
 * ADD LOGS TO EVERY METHOD SO I KNOW WAHT THE PROGRAM IS DOING
 * Create more methods when needed
 */
public class ADB {

    private String ID;
    private static String ANDROID_HOME; //added from AnroidAppiumAPI

    //constructor
    public ADB(String deviceID) {
        ID = deviceID;
    }

    public static String command(String command) {
        //USE LOGGER CLASS FOR ALL METHODS, BUT HOW, SIMPLE, THEY ALL CALL THIS METHOD command()
        MyLogger.log.debug("Formatting ADB Command: "+command);
        if (command.startsWith("adb"))
            command = command.replace("adb ", getAndroidHome() + "/platform-tools/adb ");
        else throw new RuntimeException("This method is designed to run adb commands only");
        MyLogger.log.debug("Formatted ADB Command: "+command);
        String output = ServerManager.runCommand(command);
        MyLogger.log.debug("Output of the  ADB Command: "+output);
        if (output == null) return "";
        else return output;
    }

    public static void killServer() {
        //Example add to all methods
        MyLogger.log.info("Killing server");
        command("adb kill-server");
    }

    public static void startServer() {
        MyLogger.log.info("Starting server");
        command("adb start-server");
    }

    public static ArrayList getConnectedDevices() {
        MyLogger.log.info("Getting connected devices");
        ArrayList devices = new ArrayList();
        String output = command("adb devices");
        //DECLARING AN ARRAY IN THE LOOP DEFINITION: output.split("\n")
        for (String line : output.split("\n")) {
            line = line.trim();
            if (line.endsWith("device")) devices.add(line.replace("device", "").trim());
        }
        //Add a log to see the connected devices in the terminal
        MyLogger.log.info("Conncted deviecs list:\n"+devices);
        return devices;

    }

    public String getForegroundActivity() {
        MyLogger.log.info("Getting the foreground activity");
        //executes the comman in android like linux on the device not like windows that is why we say grep linux commands
        return command("adb -s " + ID + " shell dumpsys windows windows | grep mCurrentFocus");
    }

    public String getAndroidVersionAsString() {
        String output = command("adb -s " + ID + " shell getprop ro.build.version.release");
        //just to be consistent in case it returns 6.0 three characters not 6.0.1 five characters, so we can be consistent in usig this otput variable later
        if (output.length() == 3) output += ".0";
        return output;
    }

    public int getAndroidVersion() {
        return Integer.parseInt(getAndroidVersionAsString().replaceAll("\\.", ""));

    }

    public ArrayList getInstalledPackages() {
        ArrayList packages = new ArrayList();
        //declare String[] array to hold the packages
        String[] output = command("adb -s " + ID + " shell pm list packages").split("\n");
        for (String packageID : output) packages.add(packageID.replace("package:", "").trim());
        return packages;
    }

    //Open specific application
    public void openAppsActivity(String packageID, String activityID) {
        command("adb -s " + ID + " shell am start -c api.android.intent.category.LAUNCHER -a api.android.intent.action.MAIN -n " + packageID + "/" + activityID);
    }

    //Clear Application Data     pm - means package manager command
    public void clearAppsData(String packageID) {
        command("adb -s " + ID + " shell pm clear " + packageID);
    }

    //Force stop specific application
    public void forceStopApp(String packageID) {
        command("adb -s " + ID + " shell am force-stop " + packageID);
    }

    public void installApp(String apkPath) {
        command("adb -s " + ID + " install " + apkPath);
    }

    public void uninstallApp(String packageID) {
        command("adb -s " + ID + " uninstall " + packageID);
    }

    public void clearLogBuffer(String packageID) {
        command("adb -s " + ID + " shell -c");
    }

    //target: the location to storr the file
    public void pushFile(String source, String target) {
        command("adb -s " + ID + " push " + source + " " + target);
    }

    public void pullFile(String source, String target) {
        command("adb -s " + ID + " pull " + source + " " + target);
    }

    public void deleteFile(String target) {
        command("adb -s " + ID + " shell rm " + target);
    }

    public void moveFile(String source, String target) {
        command("adb -s " + ID + " shell mv " + source + " " + target);
    }

    public void takeScreenShot(String target) {
        command("adb -s " + ID + " shell screencap " + target);
    }

    public void rebootDevice() {
        command("adb -s " + ID + " reboot");
    }

    public String getDeviceModel() {
        return command("adb -s " + ID + " shell getprop ro.product.model");
    }

    public String getDeviceSerialNumber() {
        return command("adb -s " + ID + " dhell getprop ro.serailno");
    }

    public String getDeviceCarrier() {
        return command("adb -s " + ID + " dhell getprop gsm.operator.alpha");
    }

    public ArrayList getLogcatProcesses() {
        String[] output = command("adb -s " + ID + " shell top -n 1 | grep -i 'logcat'").split("\n");
        ArrayList processes = new ArrayList();
        for (String line : output) {
            processes.add(line.split(" ")[0]);
            processes.removeAll(Arrays.asList("", null));
        }
        return processes;
    }

    //ability to filter the log grep
    public Object startLogcat(final String logID, final String grep) {
        ArrayList pidBefore = getLogcatProcesses();

        Thread logcat = new Thread(new Runnable() {
            //@Override
            public void run() {
                if(grep == null) {
                    command("adb -s " + ID + " shell logcat -v threadtime > /sdcard/" + logID + ".txt");
                }
                else command("adb -s "+ID+" shell logcat -v threadtime | grep -i '"+grep+"'> /sdcard/" +logID+ ".txt");
            }
        });
        logcat.setName(logID);
        logcat.start();
        logcat.interrupt();

        ArrayList pidAfter = getLogcatProcesses();
        Timer timer = new Timer();
        timer.start();
        while (!timer.expired(15)){
            if (pidBefore.size() > 0) pidAfter.removeAll(pidBefore);
            if (pidAfter.size() > 0) break;
            pidAfter = getLogcatProcesses();
        }

        if (pidAfter.size() == 1) return pidAfter.get(0);
        else if (pidAfter.size() > 1)
            throw new RuntimeException("Multiple logcat processes were started when only one was expected!");
        else throw new RuntimeException("Failed to start logcat process!");
    }

    public void stopLogcat(Object PID){
        command("adb -s "+ID+" shell kill "+PID);
    }


    /*
    @Test
    public void test(){
        ID = "0715f7c98061163a";
        System.out.println("Processes Prior to starting new logcat: "+getLogcatProcesses());
        Object PID = startLogcat("1", null);
        System.out.println("Started logcat on PID: "+PID);
        System.out.println("Processes sfter starting new logcat: "+ getLogcatProcesses());
        stopLogcat(PID);
        System.out.println("Processes after stopping new logcat: "+getLogcatProcesses());
    }
    */




    ///////////////ADD MISSING METHODS FROM AppiumAndroidAPI
    ///////////////////////////////////
    //////////////////////////////
    ///////////////////////
    //////////////
    ///////





    // needed to add Curl - getAndoidHome was already here in Managers
    // change deviceID variables here to ID
    // runCommand I have it here as command replace it in the adb methods
    // Most Methods need to be copied as pairs to work because they call each other

    private static String getAndroidHome(){
        if(ANDROID_HOME == null) ANDROID_HOME = System.getenv("ANDROID_HOME");
        if(ANDROID_HOME == null) throw new RuntimeException("Failed to find ANDROID_HOME. Make sure you have ANDROID_HOME set as an environment variable!");
        return ANDROID_HOME;
    }

    public static String getAnrFileLocation(String deviceID) {
        return command("adb -s "+deviceID+" shell getprop dalvik.vm.stack-trace-file");
    }

    public String getAnrFileLocation() {
        return getAnrFileLocation(ID);
    }

    public static LinkedList getDirectoryContent(String target, String deviceID){
        LinkedList items = new LinkedList();
        String[] raw = command("-s "+deviceID+" shell ls -a "+target).split("\n");
        Collections.addAll(items, raw);
        return items;
    }

    public LinkedList getDirectoryContent(String target){
        return getDirectoryContent(target, ID);
    }

    public int getDevicesWiFiMode() {
        return getDevicesWiFiMode(ID);
    }

    public static int getDevicesWiFiMode(String deviceID) {
        return Integer.parseInt(command("adb -s "+deviceID+" shell settings get global wifi_on"));
    }

    public static String getDevicesNetworkInterfaceStatus(String deviceID, String interfaceId){
        return "UP";
    }

    public String getDevicesNetworkInterfaceStatus(String interfaceId){
        return getDevicesNetworkInterfaceStatus(ID, interfaceId);
    }

    public static String getDevicesManufacturer(String deviceID){
        return command("adb -s "+deviceID+" shell getprop ro.product.manufacturer");
    }

    public String getDevicesManufacturer(){
        return getDevicesManufacturer(ID);
    }

    public static String getDevicesLanguageSettings(String deviceID){
        return "language settings";
    }

    public String getDevicesLanguageSettings() {
        return getDevicesLanguageSettings(ID);
    }

    public static int getDevicesAirPlaneMode(String deviceID){
        return 1;
    }

    public int getDevicesAirPlaneMode(){
        return getDevicesAirPlaneMode(ID);
    }

    public static String getAppVersionAsString(String appPackage, String deviceID) {
        String output = command("adb -s "+deviceID+" shell dumpsys package "+appPackage+" | grep -i versioncode");
        if(output.contains("versionCode=")) output = output.replace("versionCode=", "");
        return output;
    }

    public String getAppVersionAsString(String appPackage) {
        return getAppVersionAsString(appPackage, ID);
    }

    public static int getAppVersionAsInt(String appPackage, String deviceID){
        return Integer.parseInt(getAppVersionAsString(appPackage, deviceID).replaceAll("\\.","").trim());
    }

    public int getAppVersionAsInt(String appPackage){
        return getAppVersionAsInt(appPackage, ID);
    }

    public static String getAppName(String appPackage, String deviceID) {
        String output = command("adb -s "+deviceID+" shell dumpsys package "+appPackage+" | grep -i versionname");
        if(output.contains("versionName=")) output = output.replace("versionName=", "");
        return output;
    }

    public String getAppName(String appPackage) {
        return getAppName(appPackage, ID);
    }
























}
