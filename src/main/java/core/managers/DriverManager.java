package core.managers;

import api.android.Android;
import core.ADB;
import core.MyLogger;
import core.Timer;
import core.constants.Arg;
import core.constants.Resources;
import io.appium.java_client.android.AndroidDriver;
//ADD TO FIX THE ISSUE WITH THE SAME DEVICES RUNNING THE TESTS
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by ceko on 08/28/2016.
 */
public class DriverManager {

    //edit the environment variables first to include APPIUM_HOME path
    private static String nodeJS = System.getenv("APPIUM_HOME")+"/node.exe";
    private static String appiumJS = System.getenv("APPIUM_HOME")+"/node_modules/appium/bin/appium.js";
    private static DriverService service;
    private static String deviceID;

    private static HashMap<String, URL> hosts;
    private static String unlockPackage = "io.appium.unlock";

    private static DesiredCapabilities getCaps(String deviceID){
        MyLogger.log.info("Creating driver capabilities for device: "+deviceID);
        DesiredCapabilities caps = new DesiredCapabilities();
        //FIX change "deviceName" to  MobileCapabilityType.UDID insted of deviceID so it does not try to run the same device always
        caps.setCapability("deviceName", MobileCapabilityType.UDID);
        caps.setCapability("platformName", "Android");
        MyLogger.log.info("Installing UNLOCK_APK-DEBUG.APK for device "+deviceID);
        //Local path to the appium unlock app
        caps.setCapability("app", "C:\\Program Files (x86)\\Appium\\node_modules\\appium\\build\\unlock_apk\\unlock_apk-debug.apk");
        return caps;
    }

    //either return
    private static URL host(String deviceID) throws MalformedURLException {
        if(hosts == null){
            //Diamond notation is not supported at this language level 1.5 in build.gradle!!!! I had to put data tipes in the declaration, not leave it empty to fix this error
            //In order for my framework to be compatible with different java versions on different machines we use version 1.5 here, but we can use higher
            hosts = new HashMap<String, URL>();
            //make sure that devices that you decide to support are added to the HashMap with a unique host address.
            //Add more test devices here, for sequential running, use the same server IP and Port
            //WiFi connection: the computer and the phones must be connected to the same rooter over WiFi to work
            //USB way to connect  S6   0715f7c98061163a      WiFi way to connect 192.168.0.7:5555
            //                    S4   d9e1470c
            //                    ZTE  99000322039588
            //cmd appium 0.0.0.0:4723     Appium.exe 127.0.0.1:4723   USE Appium.exe to be able to connect multiple devices over WiFi
            hosts.put("d9e1470c", new URL("http://127.0.0.1:4723/wd/hub"));
            //hosts.put("0715f7c98061163a", new URL("http://127.0.0.2:4723/wd/hub"));
            //hosts.put("192.168.0.7:5555", new URL("http://127.0.0.1:4724/wd/hub"));
            //hosts.put("192.168.0.6:5555", new URL("http://127.0.0.1:4724/wd/hub"));
            //hosts.put("99000322039588", new URL("http://127.0.0.1:4724/wd/hub"));
            //For parallel running change the IP and Port number of the server for each device +1 127.0.0.n+1:4723
            //hosts.put("otherDevice", new URL("http://0.0.0.1:4724/wd/hub"));
        }
        return hosts.get(deviceID);
    }

    private static ArrayList<String> getAvailableDevices(){
        MyLogger.log.info("Checking for available devices");
        ArrayList<String> availableDevices = new ArrayList<String>();
        ArrayList connectedDevices = ADB.getConnectedDevices();
        for(Object connectedDevice : connectedDevices){
            //First make sure that the appium unlock app is not installed on the device
            //If it is installed that means that appium is running tests on that device and we need to move on to the next available device
            //that currently is not running any tests, if we use multiple devices of course, use the free device to create driver
            String device = connectedDevice.toString();
            ArrayList apps = new ADB(device).getInstalledPackages();
            if(!apps.contains(unlockPackage)) {
                if(useDevice(deviceID)) availableDevices.add(device);
                else MyLogger.log.info("Device: "+deviceID+" is being used by another JVM");
            }
            else {
                MyLogger.log.info("Device: "+device+" has "+unlockPackage+" assuming it is under testing");
            }
        }
        if(availableDevices.size() == 0) {
            throw new RuntimeException("Not a single device is available for testing at this time");
        }
        return availableDevices;
    }

    private static DriverService createService() throws MalformedURLException {
        service = new AppiumServiceBuilder()
                .usingDriverExecutable(new File(nodeJS))
                .withAppiumJS(new File(appiumJS))
                .withIPAddress(host(deviceID).toString().split(":")[1].replace("//",""))
                .usingPort(Integer.parseInt(host(deviceID).toString().split(":")[2].replace("/wd/hub","")))
                .withArgument(Arg.TIMEOUT, "120")// shut down appium service if no calls are send after 2 minutes, by default is 1 min
                .withArgument(Arg.LOG_LEVEL, "warn")//comment out for info log level of appium service
                .build();
        return service;
    }

    public static void createDriver() throws MalformedURLException {
        ArrayList<String> devices = getAvailableDevices();
        for(String device : devices){
            try {
                deviceID = device;
                if(useDevice(deviceID)) {
                    queueUp();
                    gracePeriod();
                    MyLogger.log.info("Starting Appium service for device "+deviceID+" on port "+host(device).toString());
                    createService().start();
                    MyLogger.log.info("Trying to create a new Driver for device " + device);
                    Android.driver = new AndroidDriver(host(device), getCaps(device));
                    MyLogger.log.info("Trying to create a new ADB for device " + device);
                    Android.adb = new ADB(device);
                    MyLogger.log.info("Leaving queue "+deviceID);
                    leaveQueue();
                    //this break stops the loop from executing again break vs continue, but there is no point of continue because there are no more statements
                    //without it it works correctly one after the other, but there is an issue that the appium server is already in use
                    break;
                    //I am not sure about the break, it is logical if this method gets called multiple times for 3 devices i do not know
                }
            }
            catch (Exception e){
                e.printStackTrace();
                //Ignore and try next device
            }

        }
    }

    public static void killDriver() throws InterruptedException {
        if(Android.driver != null){
            MyLogger.log.info("Killing Android Driver");
            MyLogger.log.info("Uninstalling UNLOCK_APK-DEBUG.APK");
            //UNINSTALL EVERY TIME BECAUSE IT RUNS CHECKS IN THE BEGINNING AND THEY WILL FAIL OTHERWISE OR ADJUST THESE CHECKS
            Android.adb.uninstallApp(unlockPackage);
            MyLogger.log.info("Quitting Android driver");
            Android.driver.quit();
            service.stop();
            MyLogger.log.info("Stopping Appium service");
        }
        else{
            MyLogger.log.info("Android Driver is not initialized, nothing to kill!");
        }
    }


    private static void queueUp() {
        try {
            MyLogger.log.info("Queueing Up: "+deviceID);
            JSONObject json = new JSONObject();
            json.put("queued_at", Timer.getTimeStamp());
            JSONObject jsonQueue = Resources.getQueue();
            jsonQueue.put(deviceID, json);
            MyLogger.log.info("JSON Queue: "+jsonQueue);
            ServerManager.write(new File(Resources.QUEUE), jsonQueue.toString());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean useDevice(String deviceID) {
        try {
            JSONObject  json = Resources.getQueue();
            if(json.containsKey(deviceID)){
                JSONObject deviceJson = (JSONObject) json.get(deviceID);
                long time = (long) deviceJson.get("queued_at");
                int diff = Timer.getDifference(time, Timer.getTimeStamp());
                if(diff >= 30) return true;
                else return false;
            } else return true;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void gracePeriod(){
        int waitTime = 0;
        try {
            JSONObject  json = Resources.getQueue();
            Set keys = json.keySet();

            JSONObject ourDeviceJson = (JSONObject) json.get(deviceID);
            json.remove(deviceID);
            long weQueuedAt = (long) ourDeviceJson.get("queued_at");

            for(Object key : keys){
                JSONObject deviceJson = (JSONObject) json.get(key);
                long theyQueuedAt = (long) deviceJson.get("queued_at");
                //If we did not queue first we need to wait for the other device to initialize driver so there is no collision
                if(weQueuedAt > theyQueuedAt) {
                    //But only if device queued first and recently, otherwise we can assume device was already initialized or no longer being used
                    int diff = Timer.getDifference(theyQueuedAt, Timer.getTimeStamp());
                    if(diff < 50){
                        MyLogger.log.info("Device: "+key+" queued first, I will need to give it extra time to initialize");
                        waitTime += 30;
                    }
                }
            }
            try {Thread.sleep(waitTime);} catch (InterruptedException e) {e.printStackTrace();}
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void leaveQueue(){
        try {
            JSONObject jsonQueue = Resources.getQueue();
            jsonQueue.remove(deviceID);
            ServerManager.write(new File(Resources.QUEUE), jsonQueue.toString());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }


























    /*
    private static void queueUp(){
        try{
            MyLogger.log.info("Queueing Up: "+deviceID);
            JSONObject json = new JSONObject();
                json.put("queued_at", Timer.getTimeStamp());
            JSONObject jsonQueue = Resources.getQueue();
                jsonQueue.put(deviceID, json);
            MyLogger.log.info("JSON Queue: "+jsonQueue);
            ServerManager.write(new File(Resources.QUEUE), jsonQueue.toString());
        }catch (IOException | ParseException e){
            throw new RuntimeException(e);
        }
    }

    private static boolean useDevice(String deviceID){
        try{
            JSONObject json = Resources.getQueue();
            if(json.containsKey(deviceID)){
                JSONObject deviceJson = (JSONObject) json.get(deviceID);
                long time = (long) deviceJson.get("queued_at");
                int diff = Timer.getDifference(time, Timer.getTimeStamp());
                if(diff >= 30) return true;
                else return false;
            } else return true;
        } catch (IOException | ParseException e){
            throw new RuntimeException(e);
        }
    }

    private static void gracePeriod(){
        int waitTime = 0;
        try {
            JSONObject json = Resources.getQueue();
            Set keys = json.keySet();

            JSONObject ourDeviceJson = (JSONObject) json.get(deviceID);
            json.remove(deviceID);
            long weQueueAt = (long) ourDeviceJson.get("queued_at");


            for(Object key : keys){
                JSONObject deviceJson = (JSONObject) json.get(key);
                long theyQueuedAt = (long) deviceJson.get("queued_at");
                //If we did not queue first we need to wait for the other device to initialize driver so there is no collision
                if(weQueueAt > theyQueuedAt) {
                    //But only if device queued first and recently, otherwise we can assume device was already initialized or no longer being used
                    int diff = Timer.getDifference(theyQueuedAt, Timer.getTimeStamp());
                    if(diff < 50) {
                        MyLogger.log.info("Device: "+key+" queued first, I will need to give it extra time to initialize");
                        waitTime += 15;
                    }
                }
            }
            try {
                Thread.sleep(waitTime);
            }  catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException | ParseException e){
            throw new RuntimeException(e);
        }
    }

    public static void leaveQueue(){
        try{
            JSONObject jsonQueue = Resources.getQueue();
            jsonQueue.remove(deviceID);
            ServerManager.write(new File(Resources.QUEUE), jsonQueue.toString());
        } catch (IOException | ParseException e){
            throw new RuntimeException(e);
        }
    }

*/






}
