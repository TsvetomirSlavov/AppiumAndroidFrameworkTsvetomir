package api.apps.famy;

import api.android.Android;
import api.apps.speedtest.about.About;
import api.apps.speedtest.begintest.BeginTest;
import api.apps.speedtest.home.Home;
import api.apps.speedtest.menu.Menu;
import api.apps.speedtest.results.Results;
import api.interfaces.Application;

/**
 * Created by ceko on 09/11/2016.
 */
public class Famy implements Application{




    public void forceStop() {
        Android.adb.forceStopApp(packageID());
    }

    public void clearData() {
        Android.adb.clearAppsData(packageID());
    }

    public Object open() {
        Android.adb.openAppsActivity(packageID(), activityID());
        return null;
    }

    public String packageID() {
        return "com.spacosa.android.famy.global";
    }

    public String activityID() {
        return "com.spacosa.android.famy.global.MainActivity";
    }

}
