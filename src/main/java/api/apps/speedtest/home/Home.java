package api.apps.speedtest.home;

import core.MyLogger;
import org.openqa.selenium.NoSuchElementException;

/**
 * Create more methods
 */
public class Home {

    public HomeUiObjects uiObject = new HomeUiObjects();

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

    public Home tapTestAgain(){
        try{
            MyLogger.log.info("Tapping on Test Again Button");
            uiObject.testAgainButton().tap().waitToDisappear(5).waitToAppear(120);
            return this;
        }catch (NoSuchElementException e){
            throw new AssertionError("Cant tap Test Again Button, element absent or blocked by a dialog");
        }
    }






















}
