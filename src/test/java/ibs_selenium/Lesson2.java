package ibs_selenium;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class Lesson2 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void before(){
        // Открываем нужную страницу и разворачиваем ее во весь экран
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://training.appline.ru/user/login");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void test() {
        //Шаг.1 Авторизация
        wait.until(visibilityOf(driver.findElement(
                By.xpath("//form[@id='login-form']")))); //Авторизация на сайте
        driver.findElement(By.id("prependedInput")).sendKeys("Sekretar Kompanii");
        driver.findElement(By.id("prependedInput2")).sendKeys("testing");
        driver.findElement(By.id("_submit")).click();
    }
}



