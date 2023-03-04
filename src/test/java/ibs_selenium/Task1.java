package ibs_selenium;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class Task1 {

    @BeforeEach
    public void before(){
    }

    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://training.appline.ru/user/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        //Шаг.1 Авторизация
        wait.until(visibilityOf(driver.findElement(
                By.xpath("//form[@id='login-form']")))); //Авторизация на сайте
        driver.findElement(By.id("prependedInput")).sendKeys("Sekretar Kompanii");
        driver.findElement(By.id("prependedInput2")).sendKeys("testing");
        driver.findElement(By.id("_submit")).click();
        wait.until(visibilityOf(driver.findElement(
                By.xpath("//h1[@class='oro-subtitle']"))));//Проверка заголовка "Панель быстрого запуска"
        //Шаг.2 Страница "Командировки"
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[2]/a/span[text()='Расходы']")).click();
        wait.until(visibilityOf(driver.findElement(By.xpath(
                "//*[@id='main-menu']/ul/li[2]/a/span/ancestor::li//ul[@class='dropdown-menu menu_level_1']"))));
        driver.findElement(By.xpath("//span[text()='Командировки']")).click();
        wait.until(invisibilityOf(driver.findElement(
                By.xpath("//div[@class='loader-mask shown']")))); //Проверка загрузки
        driver.findElement(By.xpath("//a[@title='Создать командировку']")).click();
        wait.until(invisibilityOf(driver.findElement(
                By.xpath("//div[@class='loader-mask shown']")))); //Проверка загрузки
        wait.until(visibilityOf(driver.findElement(
                By.xpath("//h1[@class='user-name']"))));//Проверка заголовка "Создать командировку"
        driver.findElement(By.xpath("//option[@value='1']")).click();
        //В поле "Подразделение" нет "Отдел внутренней разработки", было выбрано "Research & Development"
        driver.findElement(By.xpath("//a[@id='company-selector-show']")).click();
        driver.findElement(By.xpath("//span[@class='select2-chosen']")).click();
        driver.findElement(By.xpath("//div[text()='(Хром) Призрачная Организация Охотников']")).click();
        driver.findElement(By.xpath("//input[@data-name='field__1']")).click();
        driver.findElement(By.xpath("//input[@name='crm_business_trip[departureCity]']")).sendKeys(
                "Санкт-Петербург");
        driver.findElement(By.xpath("//input[@name='crm_business_trip[arrivalCity]']")).sendKeys(
                "Москва");
        driver.findElement(
                By.xpath("//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]")).sendKeys(
                "04.03.2023");
        driver.findElement(
                By.xpath("//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")).sendKeys(
                "04.04.2023");


        String organization_field = driver.findElement(By.xpath("//div/a[@href='/company/view/163']")).getText();
        String organization_expected = "Открыть \"(Хром) Призрачная Организация Охотников\"";
        assertEquals(organization_expected , organization_field, "Поле организация заполнена не верно" );

        //String division_field = driver.findElement(By.xpath("//div[@class='control-label wrap']")).getText();
        //System.out.println(division_field);
        String field = driver.findElement(By.xpath("//div[@id='uniform-crm_business_trip_businessUnit-uid-640310a7e0ef9']/span")).getText();
        System.out.println(field);


        //   //div[@id="uniform-crm_business_trip_businessUnit-uid-640310a7e0ef9"]/span






    }

    @AfterEach
    public void after(){
    }

}
