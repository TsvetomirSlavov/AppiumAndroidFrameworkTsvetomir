package api.apps.speedtest.menu;

import core.MyLogger;
import org.openqa.selenium.NoSuchElementException;

/**
 * Create more methods when possible or more functionality
 */
public class Menu {

    public MenuUiObjects uiObject = new MenuUiObjects();

    public void tapSpeedtest(){
        try{
            MyLogger.log.info("Tapping on the SPEEDTEST Menu Buton");
            uiObject.speedtest().tap();
        }catch (NoSuchElementException e){
            throw new AssertionError("Cant tap SPEEDTEST Button, element absent or blocked by a dialog");
        }
    }

    public void tapResults(){
        try{
            MyLogger.log.info("Tapping on the RESULTS Menu Buton");
            uiObject.results().tap();
        }catch (NoSuchElementException e){
            throw new AssertionError("Cant tap RESULTS Button, element absent or blocked by a dialog");
        }
    }

    public void tapSettings(){
        try{
            MyLogger.log.info("Tapping on the SETTINGS Menu Buton");
            uiObject.settings().tap();
        }catch (NoSuchElementException e){
            throw new AssertionError("Cant tap SETTINGS Button, element absent or blocked by a dialog");
        }
    }

    public void tapAbout(){
        try{
            MyLogger.log.info("Tapping on the ABOUT Menu Buton");
            uiObject.about().tap();
        }catch (NoSuchElementException e){
            throw new AssertionError("Cant tap ABOUT Button, element absent or blocked by a dialog");
        }
    }










}
