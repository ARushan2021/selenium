package ru.appline.project.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.appline.project.BasePage;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.appline.project.properties.TestProperties.getInstance;

public class AssertFilledFieldBTPage extends BasePage {

    private final Properties properties = getInstance().getProperties();


    @FindBy(xpath = "//button[@class='btn btn-success main-group action-button']")
    public WebElement checkedBtn;

    @FindBy(xpath = "//span[contains(text(), 'Research & Development')]")
    private WebElement divisionValue;

    @FindBy(xpath = "//input[@data-ftid='crm_business_trip_company']")
    private WebElement businessTripCompany;

    @FindBy(xpath = "//input[@data-ftid='crm_business_trip_tasks_1']")
    private WebElement checkBox;

    @FindBy(xpath = "//input[@name='crm_business_trip[departureCity]']")
    private WebElement departureCity;

    @FindBy(xpath = "//input[@name='crm_business_trip[arrivalCity]']")
    private WebElement arrivalCity;

    @FindBy(xpath = "//input[@name='crm_business_trip[departureDatePlan]']")
    private WebElement departureDate;

    @FindBy(xpath = "//input[@name='crm_business_trip[returnDatePlan]']")
    private WebElement returnDate;

    @FindBy(xpath = "//button[contains(text(), 'Сохранить и закрыть')]")
    private WebElement saveAndCloseBtn;

    @FindBy(xpath = "//div/div/fieldset/div/div/div/span[@class='validation-failed']")
    private WebElement validationFailed;

    @FindBy(xpath = "//a[contains(text(), 'Командированные сотрудники')]")
    private WebElement businessTripEmployees;

    @FindBy(xpath = "//a[contains(text(), 'Основная информация')]")
    private WebElement MainInformation;

    @FindBy(xpath = "//a[contains(text(), 'Сведения')]")
    private WebElement Informations;

    //Нажимаем кнопку сохранить, что-бы введенное значения сохранились в тэгах, атрибутах value, checked
    @Step("шаг5 Нажимаем кнопку сохранить")
    public void CheckedValue() {
        checkedBtn.click();
        loading();
    }

    //Значение поля "даты выбытия" форматируем как в environment.properties
    String dtDep = departureDate.getAttribute("value");
    String dtDepField = dtDep.substring(8, 10) + "." + dtDep.substring(5, 7) + "." + dtDep.substring(0, 4);

    //Значение поля "даты прибытия" форматируем как в environment.properties
    String dtRet = returnDate.getAttribute("value");
    String dtRetField = dtRet.substring(8, 10) + "." + dtRet.substring(5, 7) + "." + dtRet.substring(0, 4);

    @Step("шаг6 Проверка всех введенных полей")
    public void AssertAllBT() {
        MainInformation.click();
        assertAll("Следующее поле заполнено не верно: ",
                () -> assertEquals(properties.getProperty("Division"),
                        divisionValue.getText(), "Подразделение"),
                () -> assertEquals("(Хром) Призрачная Организация Охотников",
                        businessTripCompany.getAttribute("value"), "Организация"),
                () -> assertEquals(properties.getProperty("Checkbox"),
                        checkBox.getAttribute("checked"), "Поле задача"));

        Informations.click();
        assertAll("Следующее поле заполнено не верно: ",
                () -> assertEquals("Россия, Москва",
                        departureCity.getAttribute("value"), "Город выбытия"),
                () -> assertEquals(properties.getProperty("inputArrivalCity"),
                        arrivalCity.getAttribute("value"), "Город прибытия"),
                () -> assertEquals(properties.getProperty("departureDate"), dtDepField, "Даты выбытия"),
                () -> assertEquals(properties.getProperty("returnDate"), dtRetField, "Даты прибытия"));
    }

    @Step("шаг7 Нажимаем кнопку 'Сохранить и закрыть'")
    public void saveAndClose() {
        saveAndCloseBtn.click();
        loading();
    }

    @Step("шаг8 Проверка сообщения сообщение: 'Список командируемых сотрудников не может быть пустым'")
    public void assertEqualsBT() {
        businessTripEmployees.click();
        String validation_failed = "Список командируемых сотрудников не может быть пустым";
        assertEquals(validation_failed, validationFailed.getText(), "Нет сообщения сообщение:" +
                "'Список командируемых сотрудников не может быть пустым'");


    }

    @Step("шаг9 Выводим сообщение, что все успешно")
    public void printMessage() {
        System.out.println("Сообщение:" +
                " 'Список командируемых сотрудников не может быть пустым' успешно сформировалось!!!");
    }
}


