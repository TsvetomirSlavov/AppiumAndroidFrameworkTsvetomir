package core.managers;

import api.android.Android;
import core.ADB;
import core.MyLogger;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ceko on 08/28/2016.
 */
public class DriverManager {

    private static HashMap<String, URL> hosts;
    private static String unlockPackage = "io.appium.unlock";

    private static DesiredCapabilities getCaps(String deviceID){
        MyLogger.log.info("Creating driver capabilities for device: "+deviceID);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", deviceID);
        caps.setCapability("platformName", "Android");
        MyLogger.log.info("Installing UNLOCK_APK-DEBUG.APK");
        //Local path to the appium unlock app
        caps.setCapability("app", "C:\\Program Files (x86)\\Appium\\node_modules\\appium\\build\\unlock_apk\\unlock_apk-debug.apk");
        return caps;
    }

    private static URL host(String deviceID) throws MalformedURLException {
        if(hosts == null){
            //Diamond notation i not supported at this language level!!!! I had to put data tipes in the declaration, not leave it empty to fix this error
            hosts = new HashMap<String, URL>();
            //Add more test devices here, for sequential running, use the same server IP and Port
            //WiFi connection: the computer and the phones must be connected to the same rooter over WiFi to work
            //USB way to connect  S6   0715f7c98061163a      WiFi way to connect 192.168.0.7:5555
            //                    S4   d9e1470c
            //                    ZTE  99000322039588
            //cmd appium 0.0.0.0:4723     Appium.exe 127.0.0.1:4723   USE Appium.exe to be able to connect multiple devices over WiFi
            hosts.put("192.168.0.7:5555", new URL("http://127.0.0.1:4723/wd/hub"));
            //hosts.put("192.168.0.6:5555", new URL("http://127.0.0.1:4724/wd/hub"));
            //hosts.put("99000322039588", new URL("http://127.0.0.1:4724/wd/hub"));
            //hosts.put("d9e1470c", new URL("http://0.0.0.0:4723/wd/hub"));
            //For parallel running change the IP and Port number of the server for each device +1
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
                availableDevices.add(device);
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


    public static void createDriver(){
        ArrayList<String> devices = getAvailableDevices();
        for(String device : devices){
            try {
                MyLogger.log.info("Trying to create a new Driver for device " + device);
                Android.driver = new AndroidDriver(host(device), getCaps(device));
                Android.adb = new ADB(device);
                break;
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
            //UNINSTALL EVERY TIME BECAUSE IT RUNS CHECKS IN THE BEGINNING AND THEY WILL FAIL OTHERWISE
            Android.adb.uninstallApp(unlockPackage);
            MyLogger.log.info("quitting");
            Android.driver.quit();
        }
        else{
            MyLogger.log.info("Android Driver is not initialized, nothing to kill!");
        }
    }

















}
