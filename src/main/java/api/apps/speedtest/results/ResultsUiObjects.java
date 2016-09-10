package api.apps.speedtest.results;

import api.android.Android;
import core.MyLogger;
import core.UiObject;
import core.UiSelector;

/**
 * Created by ceko on 09/02/2016.
 */
public class ResultsUiObjects {

    private static UiObject
                logo,
                sortByType,
                sortByTime,
                sortByDownload,
                sortByUpload,
                sortByPing,
                toolsButton,
                resultsLable;

    public UiObject logo(){
        if(logo == null) logo = new UiSelector().resourceId(Android.app.speedtest.packageID()+":id/logo").makeUiObject();
        return logo;
    }

    public UiObject toolsButton(){
        if(toolsButton == null) toolsButton = new UiSelector().resourceId(Android.app.speedtest.packageID()+":id/toolsButton").makeUiObject();
        return toolsButton;
    }

    public UiObject sortByType(){
       if(sortByType == null) sortByType = new UiSelector().resourceId(Android.app.speedtest.packageID()+":id/typeIcon").makeUiObject();
        return sortByType;
    }

    public UiObject sortByTime(){
        if(sortByTime == null) sortByTime = new UiSelector().resourceId(Android.app.speedtest.packageID()+":id/timeIcon").makeUiObject();
        return sortByTime;
    }

    public UiObject sortByDownload(){
        if(sortByDownload == null) sortByDownload = new UiSelector().resourceId(Android.app.speedtest.packageID()+":id/downloadIcon").makeUiObject();
        return sortByDownload;
    }

    public UiObject sortByUpload(){
        if(sortByUpload == null) sortByUpload = new UiSelector().resourceId(Android.app.speedtest.packageID()+":id/uploadIcon").makeUiObject();
        return sortByUpload;
    }

    public UiObject sortByPing(){
        if(sortByPing == null) sortByPing = new UiSelector().resourceId(Android.app.speedtest.packageID()+":id/pingIcon").makeUiObject();
        return sortByPing;
    }

    public UiObject resultsLabel(){
        if(resultsLable == null) resultsLable = new UiSelector().resourceId(Android.app.speedtest.packageID()+":id/resultsCoutText").makeUiObject();
        return resultsLable;
    }

    //Todo implement a method for the number of results, so I can do assertion that there is a new record added after the test

    //public UiObject shareButton(){
    //    if(shareButton == null) shareButton = new UiSelector().resourceId(Android.app.speedtest.packageID()+":id/shareButton").makeUiObject();
    //    return shareButton;
   //}










}
