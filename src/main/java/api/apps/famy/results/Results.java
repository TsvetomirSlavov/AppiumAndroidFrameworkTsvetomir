package api.apps.famy.results;

import api.android.Android;
import api.interfaces.Activity;
import core.MyLogger;
import org.openqa.selenium.NoSuchElementException;

/**
 * Different implementation than Menu, Home classes, the methods here need to take parameter instead of nothing! Maybe the Menu, Home classes implementation is better.
 * We loose consistency with different implementation of the different screens of the same app!
 */
public class Results implements Activity {

    public ResultsUiObjects uiObject = new ResultsUiObjects();

    public Results sortBy(String sortType){
        try{
            sortType = sortType.toLowerCase();
            MyLogger.log.info("Sorting by "+sortType);
            if(sortType.equals("type")) uiObject.sortByType().tap();
            else if(sortType.equals("time")) uiObject.sortByTime().tap();
            else if(sortType.equals("download")) uiObject.sortByDownload().tap();
            else if(sortType.equals("upload")) uiObject.sortByUpload().tap();
            else if(sortType.equals("ping")) uiObject.sortByPing().tap();
            else throw new RuntimeException("Sorting by type: "+sortType+" is not supported");
            return Android.app.speedtest.results;
        }catch (NoSuchElementException e){
            throw new AssertionError("Cant sort by "+sortType+", element absent or blocked by a dialog");
        }
    }


    public Results waitToLoad() {
        try{
            MyLogger.log.info("Waiting for Results Activity");
            uiObject.resultsLabel().waitToAppear(10);
            return Android.app.speedtest.results;
        }catch (AssertionError e){
            throw new AssertionError("Results activity failed to load/open");
        }
    }
}
