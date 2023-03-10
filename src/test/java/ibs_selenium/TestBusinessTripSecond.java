package ibs_selenium;

import ibs_selenium.extension.DriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import project.BasePage;
import project.pages.AssertFilledFieldBTPage;
import project.pages.steps.AssertFilledFieldBTSteps;
import project.pages.steps.FilledFieldBTSteps;
import project.pages.steps.LoginSteps;
import project.pages.steps.NewBusinessTripSteps;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static project.properties.TestProperties.getInstance;

@ExtendWith(DriverExtension.class)
class TestBusinessTripSecond extends BasePage {

    private final Properties properties = getInstance().getProperties();
    private final LoginSteps loginSteps = new LoginSteps();
    private final NewBusinessTripSteps newBusinessTripSteps = new NewBusinessTripSteps();
    private final FilledFieldBTSteps filledFieldBTSteps = new FilledFieldBTSteps();
    private final AssertFilledFieldBTSteps assertFilledFieldBT = new AssertFilledFieldBTSteps();
    private final AssertFilledFieldBTPage assertFilledFieldBTPage = new AssertFilledFieldBTPage();


    @Test
    void test() {
        //Шаг.1 Авторизация
        loginSteps.login(properties.getProperty("LOGIN"), properties.getProperty("PASSWORD"));
        //Шаг.2 Страница "Командировки"
        newBusinessTripSteps.newBusinessTrip();
        //Шаг.3 Заполнение полей
        filledFieldBTSteps.filledField(properties.getProperty("inputArrivalCity"), properties.getProperty("departureDate"), properties.getProperty("returnDate"));

        assertFilledFieldBT.assertBT();

        assertAll("Следующее поле заполнено не верно: ",
                () -> assertEquals("Research & Development", assertFilledFieldBTPage.getDivisionField(), "Подразделение"),
                () -> assertEquals("(Хром) Призрачная Организация Охотников",
                        assertFilledFieldBTPage.getOrganizationField(), "Организация"),
                () -> assertEquals("true", assertFilledFieldBTPage.getFieldCheckbox(), "Поле задача"),
                () -> assertEquals("Россия, Москва", assertFilledFieldBTPage.getFieldCityDisposal(), "Город выбытия"),
                () -> assertEquals("Sankt-Peterburg", assertFilledFieldBTPage.getFieldCityArrival(), "Город прибытия"),
                () -> assertEquals("2023-03-04", assertFilledFieldBTPage.getFieldDepartureDate(), "Даты выбытия"),
                () -> assertEquals("2023-04-04", assertFilledFieldBTPage.getFieldReturnDate(), "Даты прибытия")
        );



       /*
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
        assertEquals(validation_failed, field_validation_failed, "Нет сообщения сообщение:" +
                "'Список командируемых сотрудников не может быть пустым'");

        System.out.println("Сообщение:" +
                " 'Список командируемых сотрудников не может быть пустым' успешно сформировалось!!!");*/
    }

}
