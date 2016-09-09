import api.android.Android;
import api.apps.speedtest.home.Home;
import core.ADB;
import core.MyLogger;
import core.UiObject;
import core.managers.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Level;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

import core.UiSelector;
import tests.TestPrimer;

/**
 * Created by ceko on 08/27/2016.
 */
public class Runner {
    //start appium manually from cmd appium
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //Change Debug Level for the logs
        MyLogger.log.setLevel(Level.INFO);
        try{
            DriverManager.createDriver();
            //JUnitCore.runClasses(TestPrimer.class);
        }
        finally{
            //So if there is only one device to uninstall the unlock app so if there is an exception, to able to fix it and run again
            DriverManager.killDriver();
        }
    }










//MyLogger.log.setLevel(Level.DEBUG);
    //MyLogger.log.debug("Test Debug");//Use DEBUG for low level classes my classes
    //MyLogger.log.error("Test Error");
    //MyLogger.log.info("Test Info");
    //MyLogger.log.warn("Test Warning");
}
