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












}
