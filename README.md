Tsvetomir Slavov Android Appium Automation Framework 

DESCRIPTION
develop further depending on your requirements
JUnit Rules, Retry, and Parametarized test runs
wrappers for Appium, and ADB (Android debug bridge)
DriverMager that automatically troubleshoots any issues with our Appium Servers and starts Appium servers automatically
ADB integration for quicker run times

Issues with Gradle it is not working and not dowloading the dependencies.
testCompile was added in 1.1.0 and this Gradle is older
FIX - Build > Maven > Repositories > Update; comment out the build in Gradle

HOW TO USE THE FRAMEWORK

1 Add AUT, add Page Objects and models/actions
2 Instantiate the application in apps.Apps
3 In tests.TestPrimer specify which tests be run
4 Create tests calling Andoid.driver shows Appium librarie
5 ADB - manually create methods in your AUT class that you need later in your tests 
from the ADB class custom methods, like  
public void forceStop() {
        Android.adb.forceStopApp(packageID());
    }
6 Add more custom methods in Android to simplify the Appium library if needed or just use the Appium Library

TO ADD

Add more custom methods in Android to simplify the Appium library if needed or just use the Appium Library
Takes logcat output when tests fail
Takes screenshots from the device when tests fail
Takes stacktrace from the Java code when tests fail
Takes TCP Dump from the access point when tests fail
Freed up EggPlant licences for other teams/projects
Build with open source tools so zero fees and no subscriptions
Supports running tests on more than one physical device in parallel
Supports cellular network tests
Easy test creation from existing classes and methods
Has stress testing suites
Has unittests to check compatibility of a device with the framework
and more
Create automation test cases & test suites for UI/UX, server, ads, purchasing, and compatibility testing Famy
package tests;




package tests;

import api.android.Android;
import api.apps.speedtest.Speedtest;
import core.managers.TestManager;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;

/**
 * Test Suite To Test SpeedtestNavigation through Speedtest app
 */
public class SpeedtestNavigation extends TestManager {

    //rationalizing
    private static Speedtest speedtest = Android.app.speedtest;

    @BeforeClass
    public static void beforeClass(){
        speedtest.open();
    }

    @Before
    public void before(){
        testInfo.suite("SpeedtestNavigation");
    }

    @Test
    public void test1Home(){
        //set expected results
        testInfo.id("test1Home").name("Verify that Home Activity has all the elements");
        speedtest.menu.tapSpeedtest();
        Assert.assertTrue(speedtest.home.uiObject.ping().exists());
        Android.driver.swipe(800,1500,900,600,1);
        Assert.assertTrue(speedtest.home.uiObject.pingSpeed().exists());
        Assert.assertTrue(speedtest.home.uiObject.download().exists());
        Assert.assertTrue(speedtest.home.uiObject.downloadSpeed().exists());
        Assert.assertTrue(speedtest.home.uiObject.upload().exists());
        Assert.assertTrue(speedtest.home.uiObject.uploadSpeed().exists());
        Assert.assertTrue(speedtest.home.uiObject.shareButton().exists());
        Assert.assertTrue(speedtest.home.uiObject.removeAdsButton().exists());
        Assert.assertTrue(speedtest.home.uiObject.testAgainButton().exists());
        Assert.assertTrue(speedtest.home.uiObject.logo().exists());
    }

    @Test
    public void test2Results() {
        testInfo.id("test2Results").name("Verify that Results has sorting buttons");
        speedtest.menu.tapResults();
        Assert.assertTrue(speedtest.results.uiObject.toolsButton().exists());
        Assert.assertTrue(speedtest.results.uiObject.resultsLabel().exists());
        Assert.assertTrue(speedtest.results.uiObject.sortByDownload().exists());
        Assert.assertTrue(speedtest.results.uiObject.sortByPing().exists());
        Assert.assertTrue(speedtest.results.uiObject.sortByTime().exists());
        Assert.assertTrue(speedtest.results.uiObject.sortByType().exists());
        Assert.assertTrue(speedtest.results.uiObject.sortByUpload().exists());
        Assert.assertTrue(speedtest.results.uiObject.logo().exists());
    }

    @Test
    public void test3About(){
        testInfo.id("test3About").name("Verify that About activity has Privacy Policy and Terms of Use links");
        speedtest.menu.tapAbout();
        Assert.assertTrue(speedtest.about.uiObject.privacyPolicy().exists());
        Assert.assertTrue(speedtest.about.uiObject.termsOfUse().exists());
    }












package tests;

import api.android.Android;
import api.apps.speedtest.Speedtest;
import core.managers.TestManager;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test Suite To Test SpeedtestFunctionality of Speedtest app
 */
public class SpeedtestFunctionality extends TestManager {

    private static Speedtest speedtest = Android.app.speedtest;

    @Test
    public void test4TestAgainFunctionality(){
        testInfo.id("test4TestAgainFunctionality").suite("SpeedtestFunctionality").name("Verify that you can test again");
        speedtest.menu.tapSpeedtest();
        speedtest.home.tapTestAgain();
        Assert.assertTrue(speedtest.home.uiObject.ping().exists());
        Assert.assertTrue(speedtest.home.uiObject.pingSpeed().exists());
        Assert.assertTrue(speedtest.home.uiObject.download().exists());
        Assert.assertTrue(speedtest.home.uiObject.downloadSpeed().exists());
        Assert.assertTrue(speedtest.home.uiObject.upload().exists());
        Assert.assertTrue(speedtest.home.uiObject.uploadSpeed().exists());
        Assert.assertTrue(speedtest.home.uiObject.shareButton().exists());
        Assert.assertTrue(speedtest.home.uiObject.removeAdsButton().exists());
        Assert.assertTrue(speedtest.home.uiObject.testAgainButton().exists());
        Assert.assertTrue(speedtest.home.uiObject.logo().exists());
    }








package api.apps.speedtest;

import api.android.Android;
import api.apps.speedtest.about.About;
import api.apps.speedtest.begintest.BeginTest;
import api.apps.speedtest.home.Home;
import api.apps.speedtest.menu.Menu;
import api.apps.speedtest.results.Results;
import api.interfaces.Application;

/**
 * Created by ceko on 09/01/2016.
 */
public class Speedtest implements Application {

    public Menu menu = new Menu();
    public Home home = new Home();
    public About about = new About();
    public BeginTest beginTest = new BeginTest();
    public Results results = new Results();


    public void forceStop() {
        Android.adb.forceStopApp(packageID());
    }

    public void clearData() {
        Android.adb.clearAppsData(packageID());
    }

    public Object open() {
        Android.adb.openAppsActivity(packageID(), activityID());
        return null;
    }

    public String packageID() {
        return "org.zwanoo.android.speedtest";
    }

    public String activityID() {
        return "com.ookla.speedtest.softfacade.MainActivity";
    }


}
