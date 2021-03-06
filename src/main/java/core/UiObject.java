package core;

//he has api.android.Android and a method scrollTo() but this library does not have it I have to implement it there are some examples online
//scrollTo() is removed
import api.android.Android;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

/**
 * USe older version of appium in gradle to have scrollTo
 * I HAVE TO IMPLEMENT SCROLLTO METHOD BECAUSE IT IS DEPRECATED
 * Create more methods when needed dependending on the requirements for the app functionality to be validated
 * //todo implement native appium methods through WebElement that can call all appium methods without this wrapper
 * WebElement element = Android.driver.   gives me the Appium API
 * Android.driver.swipe(800,1500,900,600,1);
 */
public class UiObject {

    private String locator;

    UiObject(String locator){
        this.locator = locator;
        MyLogger.log.debug("Created new UiObject: " +this.locator);

    }

    //missing scrollTo method my implementation
    //public MobileElement scrollTo(String text) {
    //    String uiScrollables = AndroidDriver.UiScrollable("new UiSelector().descriptionContains(\"" + text + "\")") +
    //            AndroidDriver.UiScrollable("new UiSelector().textContains(\"" + text + "\")");
    //    return (MobileElement) findElementByAndroidUIAutomator(uiScrollables);
    //}

    //distinguish between xpath and uiselector from UiSelector class objects
    private boolean isXpath(){
        return !locator.contains("UiSelector");
    }

    //does the element actually exist
    public boolean exists(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.isDisplayed();
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element checked
    public boolean isChecked(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("checked").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element checkable
    public boolean isCheckable(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("checkable").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element clickable
    public boolean isClickable(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("clickable").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element enabled
    public boolean isEnabled(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("enabled").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element focusable
    public boolean isFocusable(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("focusable").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element in focus
    public boolean isFocused(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("focused").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }


    //is the element scrollable
    public boolean isScrollable(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("scrollable").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element longClickable
    public boolean isLongclickable(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("longclickable").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element selected
    public boolean isSelected(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("selected").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }



    public Point getLocation(){

            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getLocation();


    }

    public String getText(){

            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("name");

    }

    public String getResourceId(){

            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("resourceId");

    }

    public String getClassName(){

            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("className");

    }

    public String getContentDesc(){

            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("contentDesc");

    }

    public UiObject clearText(){

        if(isXpath()) Android.driver.findElementByXPath(locator).clear();
        else  Android.driver.findElementByAndroidUIAutomator(locator).clear();
        return this;
    }

    public UiObject typeText(String value){

        if(isXpath()) Android.driver.findElementByXPath(locator).sendKeys(value);
        else  Android.driver.findElementByAndroidUIAutomator(locator).sendKeys(value);
        return this;
    }

    public UiObject tap(){

        if(isXpath()) Android.driver.findElementByXPath(locator).click();
        else  Android.driver.findElementByAndroidUIAutomator(locator).click();
        return this;
    }


    //todo implement native appium methods through WebElement that can call all appium methods without this wrapper


    //USE   By  TikhomirovSergey GitHub  framework Appium updates regularily  java-client/src/test/java/io/appium/java_client/android/AndroidElementTest.java   method  scrollingToSubElement()  oh this is only a test method to test whether his code works

    //FIX scrollTo()
    //Use older version of appium in gradle 3.4.0 has scrollTo()
    public UiObject scrollTo(){
        if(locator.contains("text")) throw new RuntimeException("Scroll to method can only be used with text attributes and current locator: "+locator+" does not contain any text attributes!");
        if(isXpath()) Android.driver.scrollTo(locator.substring(locator.indexOf("@text=\""), locator.indexOf("\"]")).replace("@text=\"", ""));
        else{
            String text;
            if(locator.contains("textContains")) text = locator.substring(locator.indexOf(".textContains(\""), locator.indexOf("\")")).replace(".textContains(\"", "");
            else text = locator.substring(locator.indexOf(".textContains(\""), locator.indexOf("\")")).replace(".textContains(\"", "");
            Android.driver.scrollTo(text);
        }
        return this;
    }

    //Solution from the project AppiumAndroidAPI
    //public UiObject scrollToText(){
    //    if(isXpath()) throw new RuntimeException("Cannot scroll to an xPath! Java Client does not support this");
    //    else if(!uiLocator.contains("")) throw new RuntimeException("UiSelector: "+uiLocator+" does not have a text attribute! Try to use scrollToElement() instead.");
    //    else {
    //        String text;
    //if(uiLocator.contains("textContains")) text = uiLocator.substring(uiLocator.indexOf(".textContains(\""), uiLocator.indexOf("\")")).replace(".textContains(\"","");
    //        else text = uiLocator.substring(uiLocator.indexOf(".text(\""), uiLocator.indexOf("\")")).replace(".text(\"","");
    //        Android.driver.scrollToExact(text);
    //    }
    //    return this;
    //}

    public UiObject waitToAppear(int seconds){
        Timer timer = new Timer();
        timer.start();
        while(!timer.expired(seconds)) if(exists()) break;
        if(timer.expired(seconds) && !exists()) throw new AssertionError("Element "+locator+" failed to appear within "+seconds+" seconds");
        return this;
    }

    public UiObject waitToDisappear(int seconds){
        Timer timer = new Timer();
        timer.start();
        while(!timer.expired(seconds)) if(!exists()) break;
        if(timer.expired(seconds) && exists()) throw new AssertionError("Element "+locator+" failed to disappear within "+seconds+" seconds");
        return this;
    }




    // Added from AndoidAppiumAPI
    // Change uiLocator variable to locator which I have here in the constructor


    public Point getBounds(){
        WebElement element;
        if(xPath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        return element.getLocation();//getAttribute("bounds");
    }

    private boolean xPath(){
        boolean value = !locator.contains("UiSelector");
        System.out.println("Element is xPath: "+value);
        return value;
    }



    public UiObject scrollToText(){
        if(xPath()) throw new RuntimeException("Cannot scroll to an xPath! Java Client does not support this");
        else if(!locator.contains("")) throw new RuntimeException("UiSelector: "+locator+" does not have a text attribute! Try to use scrollToElement() instead.");
        else {
            String text;
            if(locator.contains("textContains")) text = locator.substring(locator.indexOf(".textContains(\""), locator.indexOf("\")")).replace(".textContains(\"","");
            else text = locator.substring(locator.indexOf(".text(\""), locator.indexOf("\")")).replace(".text(\"","");
            Android.driver.scrollToExact(text);
        }
        return this;
    }

    public UiObject setText(String value){
        WebElement element;
        if(xPath()) element = Android.driver.findElementByXPath(locator);
        else element = Android.driver.findElementByAndroidUIAutomator(locator);
        String existingText = element.getText();
        if(!existingText.equals(value)) clearText();
        else if(existingText != null && !existingText.isEmpty()) clearText();
        element.sendKeys(value);
        return this;
    }






}
