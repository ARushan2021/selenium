package ibs_selenium;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Lesson1 {


    @BeforeEach
    public void before(){
    }

    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://training.appline.ru/user/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        //Шаг.1 Авторизация
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//form[@id='login-form']"))));
        driver.findElement(By.id("prependedInput")).sendKeys("Sekretar Kompanii");
        driver.findElement(By.id("prependedInput2")).sendKeys("testing");
        driver.findElement(By.id("_submit")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                By.xpath("//h1[@class='oro-subtitle']"))));
        //Шаг.2 Командировки
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[2]/a/span[text()='Расходы']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
                "//*[@id='main-menu']/ul/li[2]/a/span/ancestor::li//ul[@class='dropdown-menu menu_level_1']"))));
        driver.findElement(By.xpath("//span[text()='Командировки']")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(
                By.xpath("//div[@class='loader-mask shown']"))));
        //Шаг. 3
        driver.findElement(By.xpath("//div[1]/div/div/span/div[3]/div")).click();
        driver.findElement(By.xpath("//div[1]/div/div/div[2]/div/div/input")).sendKeys("Согласование с ОСР");
        driver.findElement(By.xpath("//label[@title='Согласование с ОСР']")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(
                By.xpath("//div[@class='loader-mask shown']"))));
        String id_number = driver.findElement(
                By.xpath("//td[text()='Питер']//parent::tr//td[contains(@class, 'cell-name')]")).getText();
        driver.findElement(
                By.xpath("//div[1]/div/div/span/div[1]/div[1]")).click(); //  "//div[@class='filter-item oro-drop open-filter']/div[contains(text(), 'Номер')]"
        wait.until(ExpectedConditions.visibilityOf(
            driver.findElement(By.xpath("//input[@name='value']")))).sendKeys(id_number, Keys.ENTER);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(
                By.xpath("//div[@class='loader-mask shown']"))));
        //Шаг.4 Переход в командтровку
        driver.findElement(By.xpath("//tr/td[contains(@class, 'grid-cell')][text()='К21-10098']")).click();





        //driver.quit();
    }
    @AfterEach
    public void after(){
    }
}

