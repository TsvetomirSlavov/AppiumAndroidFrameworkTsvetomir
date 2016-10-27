package tests;


import api.android.Android;
import api.apps.famy.Famy;
import api.apps.speedtest.Speedtest;
import core.managers.TestManager;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test Suite To Test FamyFunctionality of Famy app
 */
public class FamyFunctionality extends TestManager{

    private static Famy famy = Android.app.famy;

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
}
