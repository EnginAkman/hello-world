package com.library.utilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    /*
Singleton Design Pattern:
    - What is it and how to achieve it?
    - constrains/limits initialization of class to single instance
    - a pattern we use  to make sure create only one object of the class
    - Singleton Design Pattern is a software design pattern that restricts the instiation of class to a "single" object.
    - We use it for our Driver utility, so that we can always get the same driver instance everytime we call it.
What JAVA OOP concept is used here?
    - Encapsulation
    1- We create private constructor to limit class' object creation.
    2- We create a getter method so that we return the driver in the way that we want to return.
Why do you want to get same driver instance all the time?
    - When creating tests in different classes with different packages, with different design patterns, with different logics implemented;
     we need to make we passing around the exact same driver instance so that our webdriver knows where to go.
 */

    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    //1-Make constructor private
    private Driver(){

    }

    private static WebDriver driver;

    public static WebDriver getDriver(){

        if (driver == null){
            String browser = ConfigurationReader.getProperty("browser");

            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;

                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;

                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "chrome-remote":
                    try {
                        URL url = new URL("http://100.24.15.65:4444/wd/hub");  // this is cybertek's Selenium Grid
                        ChromeOptions chromeOptions = new ChromeOptions();
                        driverPool.set(new RemoteWebDriver(url, chromeOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    throw new RuntimeException("Wrong browser name: " + browser);

                case "firefox-remote":
                    try {
                        //to request grid to run tests on firefox
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        URL url = new URL("http://18.206.13.27:4444/wd/hub");
                        driver = new RemoteWebDriver(url, firefoxOptions);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

            }
        }

        return driver;

    }

    public static void closeDriver(){

        if (driver != null){

            driver.quit();

            driver = null;
        }
    }
}
