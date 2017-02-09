package api.android;

import api.apps.Apps;
import core.ADB;
import core.Curl;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.Dimension;


/**
 * Created by ceko on 08/29/2016.
 */
public class Android {

    public static AndroidDriver driver;
    public static ADB adb;
    public static Curl curl;      //added static Curl variable from Android in Android
    public static Apps app = new Apps();
















    // All added from AndroidAppiumAPI
    // Comment out the variables and Constructor and add static to all methods
    /**
     * Aggregation.
     * Once a new instance of Android is created, ADB,cURL and AndroidDriver methods become available for that specific Android device
     */
    //public ADB adb;
    //public Curl curl;
    //public static AndroidDriver driver;

    /**
     * @param driver Must initiate Android instance with the AndroidDriver
     */
    //public Android(AndroidDriver driver){
    //    this.driver = driver;
    //    String deviceID = (String) driver.getCapabilities().getCapability("deviceName");
    //    this.adb = new ADB(deviceID);
    //    this.curl = new Curl();
    //}

    /**
     * This is a simple method that divides the screen's width in half and does a swipe up motion
     */
    public static void scrollDown(){
        Dimension size = driver.manage().window().getSize();
        int width =  size.getWidth();
        int height = size.getHeight();
        new TouchAction(driver)
                .press(width/2, height-200)
                .waitAction(1500)
                .moveTo(width/2, +700).release().perform();
    }

    /**
     * This is a simple method that divides the screen's width in half and does a swipe down motion
     */
    public static void scrollUp(){
        Dimension size = driver.manage().window().getSize();
        int width =  size.getWidth();
        int height = size.getHeight();
        new TouchAction(driver)
                .press(width/2, +400)
                .waitAction(1500)
                .moveTo(width/2, +height-400).release().perform();
    }

    /**
     * This is a simple method that divides the screen's height in 20 slices and does a swipe motion to scroll down
     *
     * @param region 1 to 20 specifies the more precise location of where to do the swipe action
     */
    public static void scrollDown(int region){
        if(region>20) throw new RuntimeException("Screen height is divided in 20 slices. Use int value between 1 and 20 "+
                " inclusive, to specify more precise location to perform the swipe");
        Dimension size = driver.manage().window().getSize();
        int width =  size.getWidth();
        int height = size.getHeight();
        int increment = height/20;
        int start = increment*region;
        new TouchAction(driver)
                .press(width/2, start)
                .waitAction(1500)
                .moveTo(width/2, height-start).release().perform();
    }

    /**
     * This is a simple method that divides the screen's height in 20 slices and does a swipe motion to scroll down
     *
     * @param region 1 to 20 specifies the more precise location of where to do the swipe action
     */
    public static void scrollUp(int region){
        if(region>20) throw new RuntimeException("Screen height is divided in 20 slices. Use int value between 1 and 20 "+
                " inclusive, to specify more precise location to perform the swipe");
        Dimension size = driver.manage().window().getSize();
        int width =  size.getWidth();
        int height = size.getHeight();
        int increment = height/20;
        int start = increment*region;
        new TouchAction(driver)
                .press(width/2, +start)
                .waitAction(1500)
                .moveTo(width/2, +height-10).release().perform();
    }

    /**
     * Simple swipe action to the left.
     *
     * @param region 1 to 20 specifies the more precise location of where to do the swipe action
     */
    public static void swipeLeft(int region){
        if(region>20) throw new RuntimeException("Screen height is divided in 20 slices. Use int value between 1 and 20 "+
                " inclusive, to specify more precise location to perform the swipe");
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.8);
        int endx = (int) (size.width * 0.20);
        int height = size.getHeight();
        int increment = height/20;
        int starty = increment * region;
        driver.swipe(startx, starty, endx, starty, 500);
    }

    /**
     * Simple swipe action to the right.
     *
     * @param region 1 to 20 specifies the more precise location of where to do the swipe action
     */
    public static void swipeRight(int region){
        if(region>20) throw new RuntimeException("Screen height is divided in 20 slices. Use int value between 1 and 20"+
                " inclusive, to specify more precise location to perform the swipe");
        Dimension size = driver.manage().window().getSize();
        int endx = (int) (size.width * 0.8);
        int startx = (int) (size.width * 0.20);
        int height = size.getHeight();
        int increment = height/20;
        int starty = increment * region;
        driver.swipe(startx, starty, endx, starty, 500);
    }

    public static void pressBack(){
        driver.pressKeyCode(AndroidKeyCode.BACK);
    }

    public static void pressHome(){
        driver.pressKeyCode(AndroidKeyCode.HOME);
    }












}
