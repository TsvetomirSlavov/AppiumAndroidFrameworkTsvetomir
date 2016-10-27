package tests;


import api.android.Android;
import api.apps.famy.Famy;
import api.apps.speedtest.Speedtest;
import core.managers.TestManager;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test Suite To Test FamyFunctionality of Famy app
 */
public class FamyFunctionality extends TestManager{

    private static Famy famy = Android.app.famy;



    @BeforeClass
    public static void beforeClass(){
        famy.open();
    }

    @Before
    public void before(){
        testInfo.suite("FamyFunctionality");
    }

    @Test
    public void swipeThroughGroups(){
        testInfo.id("swipe through groups").suite("FamyFunctionality").name("Verify that you can swipe through groups");
        System.out.println(Android.driver.findElementByName("ceko").getText());
        Assert.assertTrue(Android.driver.findElementByName("ceko").isDisplayed());
        Android.driver.swipe(800,1500,900,600,1);
        Assert.assertFalse(Android.driver.findElementByName("ceko").isDisplayed());
    }
}
