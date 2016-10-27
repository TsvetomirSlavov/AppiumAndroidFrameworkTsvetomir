package api.apps.famy.begintest;

import core.UiObject;
import core.UiSelector;

/**
 * Created by ceko on 09/02/2016.
 */
public class BeginTestUiObjects {

    private static UiObject beginTestButton;

    public UiObject beginTestButton(){
        if(beginTestButton == null) beginTestButton = new UiSelector().text("Begin Test").makeUiObject();
        return beginTestButton;
    }
}
