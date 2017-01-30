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
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static api.android.Android.driver;

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
    public void swipeThroughGroups() throws InterruptedException {
        //WebDriverWait wait = new WebDriverWait(driver, 30);
        //wait.until(ExpectedConditions.elementToBeClickable(By
        //        .xpath("//android.widget.Button[contains(@text, 'Log In')]")));
        //Android.driver.wait(6);
        //Thread.sleep(15000);
        testInfo.id("swipe through groups").suite("FamyFunctionality").name("Verify that you can swipe through groups");
        System.out.println(driver.findElementByName("ceko").getText());
        Assert.assertTrue(driver.findElementByName("ceko").isDisplayed());
        driver.swipe(800,1500,900,600,1);
        Assert.assertFalse(driver.findElementByName("ceko").isDisplayed());
    }
}
