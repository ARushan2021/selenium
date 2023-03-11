package ru.ibs_selenium;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class TestBusinessTrip {
    private WebDriver driver;
    private WebDriverWait wait;
    @BeforeEach
    public void before() {
        // Открываем нужную страницу и разворачиваем ее во весь экран
        System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver.exe");
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
        wait.until(visibilityOf(driver.findElement(
                By.xpath("//h1[@class='oro-subtitle']"))));//Проверка заголовка "Панель быстрого запуска"
        //Шаг.2 Страница "Командировки"
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[2]/a/span[text()='Расходы']")).click();
        wait.until(visibilityOf(driver.findElement(By.xpath(
                "//*[@id='main-menu']/ul/li[2]/a/span/ancestor::li//ul[@class='dropdown-menu menu_level_1']"))));
        driver.findElement(By.xpath("//span[text()='Командировки']")).click();
        loading();
        // Создание новой командировки
        driver.findElement(By.xpath("//a[@title='Создать командировку']")).click();
        loading();
        //Проверка заголовка "Создать командировку"
        wait.until(visibilityOf(driver.findElement(
                By.xpath("//h1[@class='user-name']"))));
        driver.findElement(By.xpath("//option[@value='1']")).click();
        //!!!!!В поле "Подразделение" нет "Отдел внутренней разработки", было выбрано "Research & Development"!!!!!
        driver.findElement(By.xpath("//a[@id='company-selector-show']")).click();
        driver.findElement(By.xpath("//span[@class='select2-chosen']")).click();
        // Заполнение поля "Принимающая организация"
        driver.findElement(By.xpath("//div[text()='(Хром) Призрачная Организация Охотников']")).click();
        // Проставление чек-бокса "Заказ билетов"
        driver.findElement(By.xpath("//input[@data-name='field__1']")).click();
        // Заполнение поля "Город прибытия"
        driver.findElement(By.xpath("//input[@name='crm_business_trip[arrivalCity]']")).sendKeys(
                "Санкт-Петербург");
        // Заполнение поля "Планируемая дата выезда*"
        driver.findElement(
                By.xpath("//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]")).sendKeys(
                "04.03.2023");
        // Заполнение поля "Планируемая дата возвращения"
        driver.findElement(
                By.xpath("//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")).sendKeys(
                "04.04.2023");
        // При заполнении поля "Планируемая дата возвращения" открывается календарик, закрываем его
        driver.findElement(By.xpath("//input[contains(" +
                "@id, 'date_selector_crm_business_trip_returnDatePlan')]")).sendKeys(Keys.ESCAPE);

        // Проверка что, все полня заполнены правильно.

        //Нажимаем кнопку сохранить, что-бы введенное значения сохранились в тэгах, атрибутах value, checked
        driver.findElement(By.xpath("//button[@class='btn btn-success main-group action-button']")).click();
        loading();
        //Значение поля "Подразделение"
        String division_field = driver.findElement(
                By.xpath("//span[contains(text(), 'Research & Development')]")).getText();
        //Значение поля "Принимающая организация"
        String organization_field = driver.findElement(
                By.xpath("//input[@data-ftid='crm_business_trip_company']")).getAttribute("value");
        //Значение поля чек-бокс "Задачи"
        String field_checkbox = driver.findElement(
                By.xpath("//input[@data-ftid='crm_business_trip_tasks_1']")).getAttribute("checked");
        //Значение поля "Город выбытия"
        String field_city_disposal = driver.findElement(
                By.xpath("//input[@name='crm_business_trip[departureCity]']")).getAttribute("value");
        //Значение поля "Город прибытия"
        String field_city_arrival = driver.findElement(
                By.xpath("//input[@name='crm_business_trip[arrivalCity]']")).getAttribute("value");
        //Значение поля "даты выбытия"
        String field_departure_date = driver.findElement(
                By.xpath("//input[@name='crm_business_trip[departureDatePlan]']")).getAttribute("value");
        //Значение поля "даты прибытия"
        String field_return_date = driver.findElement(
                By.xpath("//input[@name='crm_business_trip[returnDatePlan]']")).getAttribute("value");
        // Проверка заполнения полей
        assertAll("Следующее поле заполнено не верно: ",
                () -> assertEquals("Research & Development", division_field, "Подразделение"),
                () -> assertEquals("(Хром) Призрачная Организация Охотников",
                        organization_field, "Организация"),
                () -> assertEquals("true", field_checkbox, "Поле задача"),
                () -> assertEquals("Россия, Москва", field_city_disposal, "Город выбытия"),
                () -> assertEquals("Санкт-Петербург", field_city_arrival, "Город прибытия"),
                () -> assertEquals("2023-03-04", field_departure_date, "Даты выбытия"),
                () -> assertEquals("2023-04-04", field_return_date, "Даты прибытия")
        );
        //Нажимаем кнопку "Сохранить и закрыть"
        driver.findElement(By.xpath("//button[contains(text(), 'Сохранить и закрыть')]")).click();
        loading();

        // Проверка, что на странице появилось сообщение: "Список командируемых сотрудников не может быть пустым"
        // На странице два тэга с таким xpath, мы выбираем первый.
        String field_validation_failed = driver.findElement(
                By.xpath("//div/div/fieldset/div/div/div/span[@class='validation-failed']")).getText();
        String validation_failed = "Список командируемых сотрудников не может быть пустым";
        assertEquals( validation_failed, field_validation_failed, "Нет сообщения сообщение:" +
                "'Список командируемых сотрудников не может быть пустым'");

        System.out.println("Сообщение:" +
                " 'Список командируемых сотрудников не может быть пустым' успешно сформировалось!!!");
    }
    @AfterEach
    public void after() {
        driver.quit();
    }
    public void loading() {
        //Проверка загрузки
        wait.until(invisibilityOf(driver.findElement(
                By.xpath("//div[@class='loader-mask shown']"))));
    }
}
