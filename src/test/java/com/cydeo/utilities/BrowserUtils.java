package com.cydeo.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BrowserUtils {

    public static void sleep(int second){
        second *=1000;

        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {

        }
        //this method will accepet and execute Thread.sleep method for given duration
    }

    public static void switchWindowAndVerify( String expectInUrl, String expectedTitle){

        Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();

        for (String each : allWindowHandles) {

            Driver.getDriver().switchTo().window(each);
            System.out.println("Current URL: "+Driver.getDriver().getCurrentUrl());

            if (Driver.getDriver().getCurrentUrl().contains(expectInUrl)){
                break;
            }
        }
        String actualTitle = Driver.getDriver().getTitle();
        expectedTitle=expectedTitle;

        Assert.assertTrue(actualTitle.contains(expectedTitle));

    }


    public static void verifyTitle( String expectedTitle){

        String currentTitle = Driver.getDriver().getTitle();

        Assert.assertEquals(currentTitle,expectedTitle);

    }

    public static void verifyTitleContains( String expectedTitle){

        String currentTitle = Driver.getDriver().getTitle();

        Assert.assertTrue(currentTitle.contains(expectedTitle));

    }

    public static void waitForInvisibilityOf(WebElement webElement){

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static void waitForInvisibilityOf(String title){

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.titleContains(title));
    }


    public static List<String> dropdownOptionsAsString(WebElement dropDown){
//this method accepts a dropdown and return list String
        Select select = new Select(dropDown);

        List<WebElement> dropdownAsWebElement = select.getOptions();

        List<String> dropdownAsString = new ArrayList<>();

        for (WebElement each : dropdownAsWebElement) {
            dropdownAsString.add(each.getText());
        }
        return dropdownAsString;
    }



}
