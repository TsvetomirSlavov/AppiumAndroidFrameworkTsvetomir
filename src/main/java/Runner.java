import api.android.Android;
import core.ADB;
import core.MyLogger;
import core.UiObject;
import core.managers.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Level;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

import core.UiSelector;

/**
 * Created by ceko on 08/27/2016.
 */
public class Runner {

    //start appium manually from cmd appium
    public static void main(String[] args) throws MalformedURLException {
        //Change Debug Level for the logs
        MyLogger.log.setLevel(Level.INFO);
        AndroidDriver driver = null;
        try{
            DriverManager.createDriver();
            Android.adb.openAppsActivity("org.zwanoo.android.speedtest", "com.ookla.speedtest.softfacade.MainActivity");

            UiObject testAgainButton = new UiSelector().resourceId("org.zwanoo.android.speedtest:id/o2_button_text").makeUiObject();
            UiObject ping = new UiSelector().resourceId("org.zwanoo.android.speedtest:id/pingSpeed").makeUiObject();
            UiObject download = new UiSelector().resourceId("org.zwanoo.android.speedtest:id/downloadSpeed").makeUiObject();
            UiObject upload = new UiSelector().resourceId("org.zwanoo.android.speedtest:id/uploadSpeed").makeUiObject();

            //VERY IMPORTANT WAITS BECAUSE OTHERWISE IT CAN NOT FIND THE ELEMENTS
            testAgainButton.waitToAppear(15).tap().waitToDisappear(5).waitToAppear(120);

            MyLogger.log.info("Ping: " +ping.getText());
            MyLogger.log.info("Download: " +download.getText());
            MyLogger.log.info("Upload: " +upload.getText());


            //So if there is only one device to uninstall the unlock app so if there is an exception, to able to fix it and run again
            DriverManager.killDriver();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            if(driver != null) driver.quit();
        }





    }






















//MyLogger.log.setLevel(Level.DEBUG);
    //MyLogger.log.debug("Test Debug");//Use DEBUG for low level classes my classes
    //MyLogger.log.error("Test Error");
    //MyLogger.log.info("Test Info");
    //MyLogger.log.warn("Test Warning");
}
