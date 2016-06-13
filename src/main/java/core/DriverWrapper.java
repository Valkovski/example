package core;


import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import utils.Propertiator;

public class DriverWrapper {
    private static WebDriver driver = null;
    private static FluentWait<WebDriver> wait = null;

    private DriverWrapper() {
        EventFiringWebDriver eDriver = null;
        String type = Propertiator.getPropertie("driver");
        switch(type) {
        case "Chrome":
            eDriver  = new EventFiringWebDriver(new ChromeDriver());
            break;
        case "Firefox":
            eDriver  = new EventFiringWebDriver(new FirefoxDriver());
            break;
     }
      eDriver.register(new MyEventListener());
          driver = eDriver;
          driver.manage().window().maximize();
  }

    public static WebDriver getDriver() {
        if(driver != null) {
            return driver;
        } else {
            new DriverWrapper();
            return driver;
        }
    }
    
    public static FluentWait<WebDriver> getFindFluentWait() {
       getFluentWait().withTimeout(30, TimeUnit.SECONDS)
                    .pollingEvery(5, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);
            return wait;
        }
    
    public static FluentWait<WebDriver> getFluentWait() {
        if(wait != null) {
            return wait;
        } else {
            wait =  new FluentWait<WebDriver>(driver);
            return wait;
        }
    }
    
}
