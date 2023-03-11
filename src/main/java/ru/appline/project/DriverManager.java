package ru.appline.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Properties;

import static java.lang.System.setProperty;
import static ru.appline.project.properties.TestProperties.getInstance;

public class DriverManager {

    private static WebDriver driver;
    private static Properties properties = getInstance().getProperties();

    public static WebDriver getWebDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void initDriver() {

        setProperty(properties.getProperty("WEB_DRIVER"), properties.getProperty("WEB_DRIVER_PATH"));
        driver = new ChromeDriver();
        driver.get(properties.getProperty("HOSTNAME"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    public static void closeDriver() {
        driver.quit();

    }


}
