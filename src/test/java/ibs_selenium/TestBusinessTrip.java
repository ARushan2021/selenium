package ibs_selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class TestBusinessTrip {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
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
        // Проверка поля "Подразделение"
        String division_field = driver.findElement(
                By.xpath("//span[contains(text(), 'Research & Development')]")).getText();
        String division_field_expected = "Research & Development";
        assertEquals("Поле Подразделение заполнена не верно", division_field, division_field_expected);
        // Проверка поля "Принимающая организация"
        String organization_field = driver.findElement(
                By.xpath("//input[@data-ftid='crm_business_trip_company']")).getAttribute("value");
        String organization_expected = "(Хром) Призрачная Организация Охотников";
        assertEquals("Поле организация заполнена не верно", organization_expected, organization_field);
        // Проверка чек-бокса "Задачи"
        String field_checkbox = driver.findElement(
                By.xpath("//input[@data-ftid='crm_business_trip_tasks_1']")).getAttribute("checked");
        String checkbox = "true";
        assertEquals("Поле задача проставлено не верно", checkbox, field_checkbox);
        //Проверка поля "Город выбытия"
        String field_city_disposal = driver.findElement(
                By.xpath("//input[@name='crm_business_trip[departureCity]']")).getAttribute("value");
        String city_disposal = "Россия, Москва";
        assertEquals("Поле Город выбытия заполнено не верно", city_disposal, field_city_disposal);
        //Проверка поля "Город прибытия"
        String field_city_arrival = driver.findElement(
                By.xpath("//input[@name='crm_business_trip[arrivalCity]']")).getAttribute("value");
        String city_arrival = "Санкт-Петербург";
        assertEquals("Поле Город прибытия заполнено не верно", city_arrival, field_city_arrival);
        //Проверка даты выбытия
        String field_departure_date = driver.findElement(
                By.xpath("//input[@name='crm_business_trip[departureDatePlan]']")).getAttribute("value");
        String departure_date = "2023-03-04";
        assertEquals("Поле даты выбытия заполнено не верно", departure_date, field_departure_date);
        //Проверка даты прибытия
        String field_return_date = driver.findElement(
                By.xpath("//input[@name='crm_business_trip[returnDatePlan]']")).getAttribute("value");
        String return_date = "2023-04-04";
        assertEquals("Поле даты прибытия заполнено не верно", return_date, field_return_date);

        //Нажимаем кнопку "Сохранить и закрыть"
        driver.findElement(By.xpath("//button[contains(text(), 'Сохранить и закрыть')]")).click();
        loading();

        // Проверка, что на странице появилось сообщение: "Список командируемых сотрудников не может быть пустым"
        // На странице два тэга с таким xpath, мы выбираем первый.
        String field_validation_failed = driver.findElement(
                By.xpath("//div/div/fieldset/div/div/div/span[@class='validation-failed']")).getText();
        String validation_failed = "Список командируемых сотрудников не может быть пустым";
        assertEquals("Нет сообщения сообщение:" +
                " 'Список командируемых сотрудников не может быть пустым'", validation_failed, field_validation_failed);

        System.out.println("Сообщение:" +
                " 'Список командируемых сотрудников не может быть пустым' успешно сформировалось!!!");
    }

    @After
    public void after() {
        driver.quit();
    }

    public void loading() {
        //Проверка загрузки
        wait.until(invisibilityOf(driver.findElement(
                By.xpath("//div[@class='loader-mask shown']"))));
    }
}
