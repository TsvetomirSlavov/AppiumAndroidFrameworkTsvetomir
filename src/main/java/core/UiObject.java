package core;

import api.android.some.Android;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

/**
 * Created by ceko on 08/28/2016.
 */
public class UiObject {

    private String locator;

    UiObject(String locator){
        this.locator = locator;
        System.out.println(this.locator);

    }

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










}
