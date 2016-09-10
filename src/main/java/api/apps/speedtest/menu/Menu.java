package api.apps.speedtest.menu;

import api.android.Android;
import api.apps.speedtest.about.About;
import api.apps.speedtest.home.Home;
import api.apps.speedtest.results.Results;
import core.MyLogger;
import org.openqa.selenium.NoSuchElementException;

/**
 * Create more methods when possible or more functionality
 */
public class Menu {

    public MenuUiObjects uiObject = new MenuUiObjects();

    public Home tapSpeedtest(){
        try{
            MyLogger.log.info("Tapping on the SPEEDTEST Menu Buton");
            uiObject.speedtest().tap();
            return Android.app.speedtest.home.waitToLoad();
        }catch (NoSuchElementException e){
            throw new AssertionError("Cant tap SPEEDTEST Button, element absent or blocked by a dialog");
        }
    }

    public Results tapResults(){
        try{
            MyLogger.log.info("Tapping on the RESULTS Menu Buton");
            uiObject.results().tap();
            return Android.app.speedtest.results.waitToLoad();
        }catch (NoSuchElementException e){
            throw new AssertionError("Cant tap RESULTS Button, element absent or blocked by a dialog");
        }
    }

    //Todo return settings, implement settings API
    public void tapSettings(){
        try{
            MyLogger.log.info("Tapping on the SETTINGS Menu Buton");
            uiObject.settings().tap();
        }catch (NoSuchElementException e){
            throw new AssertionError("Cant tap SETTINGS Button, element absent or blocked by a dialog");
        }
    }

    public About tapAbout(){
        try{
            MyLogger.log.info("Tapping on the ABOUT Menu Buton");
            uiObject.about().tap();
            return Android.app.speedtest.about.waitToLoad();
        }catch (NoSuchElementException e){
            throw new AssertionError("Cant tap ABOUT Button, element absent or blocked by a dialog");
        }
    }










}
