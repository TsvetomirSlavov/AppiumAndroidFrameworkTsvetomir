package tests;

import api.android.Android;
import api.apps.speedtest.Speedtest;
import core.managers.TestManager;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test Suite To Test Functionality of Speedtest app
 */
public class Functionality extends TestManager {

    private static Speedtest speedtest = Android.app.speedtest;

    @Test
    public void test4TestAgainFunctionality(){
        testInfo.id("test4TestAgainFunctionality").suite("Functionality").name("Verify that you can test again");
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












}
