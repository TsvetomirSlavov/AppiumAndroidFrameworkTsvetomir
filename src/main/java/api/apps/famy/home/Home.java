package api.apps.famy.home;

import api.android.Android;
import api.apps.speedtest.home.*;
import api.interfaces.Activity;
import core.MyLogger;
import org.openqa.selenium.NoSuchElementException;

/**
 * Created by ceko on 09/11/2016.
 */
public class Home implements Activity {

    public api.apps.speedtest.home.HomeUiObjects uiObject = new api.apps.speedtest.home.HomeUiObjects();

    public String getPingSpeed(){
        try{
            MyLogger.log.info("Getting Ping Speed");
            return uiObject.pingSpeed().getText();
        }catch (NoSuchElementException e) {
            throw new AssertionError("Can't get Ping Speed, element absent or blocked by a dialog");
        }
    }

    public String getDownloadSpeed(){
        try{
            MyLogger.log.info("Getting Download Speed");
            return uiObject.downloadSpeed().getText();
        }catch (NoSuchElementException e) {
            throw new AssertionError("Can't get Download Speed, element absent or blocked by a dialog");
        }
    }

    public String getUploadSpeed(){
        try{
            MyLogger.log.info("Getting Upload Speed");
            return uiObject.uploadSpeed().getText();
        }catch (NoSuchElementException e) {
            throw new AssertionError("Can't get Upload Speed, element absent or blocked by a dialog");
        }
    }

    public api.apps.speedtest.home.Home tapTestAgain(){
        try{
            MyLogger.log.info("Tapping on Test Again Button");
            //Increase to 15 seconds because sometimes it takes longer and it throws Exception just because we did set it for shorter time period!
            uiObject.testAgainButton().tap().waitToDisappear(15).waitToAppear(120);
            return Android.app.speedtest.home;
        }catch (NoSuchElementException e){
            throw new AssertionError("Cant tap Test Again Button, element absent or blocked by a dialog");
        }
    }


    public api.apps.speedtest.home.Home waitToLoad() {
        try{
            MyLogger.log.info("Waiting for Home activity");
            uiObject.testAgainButton().waitToAppear(10);
            return Android.app.speedtest.home;
        }catch (AssertionError e){
            throw new AssertionError("Home activity failed to load/open");
        }
    }


}
