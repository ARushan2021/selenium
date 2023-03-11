package ibs_selenium;

import project.pages.AssertFilledFieldBTPage;
import project.pages.ErrorMessagePage;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static project.properties.TestProperties.getInstance;

public class BaseTestAssert {

    private final AssertFilledFieldBTPage assertFilledFieldBTPage = new AssertFilledFieldBTPage();
    private final Properties properties = getInstance().getProperties();
    private final ErrorMessagePage errorMessagePage = new ErrorMessagePage();

    // Проверка заполнения полей
    public void AssertAllBT(){
        assertAll("Следующее поле заполнено не верно: ",
                () -> assertEquals(properties.getProperty("Division"),
                        assertFilledFieldBTPage.getDivisionField(), "Подразделение"),
                () -> assertEquals("(Хром) Призрачная Организация Охотников",
                        assertFilledFieldBTPage.getOrganizationField(), "Организация"),
                () -> assertEquals(properties.getProperty("Checkbox"),
                        assertFilledFieldBTPage.getFieldCheckbox(), "Поле задача"),
                () -> assertEquals("Россия, Москва",
                        assertFilledFieldBTPage.getFieldCityDisposal(), "Город выбытия"),
                () -> assertEquals(properties.getProperty("inputArrivalCity"),
                        assertFilledFieldBTPage.getFieldCityArrival(), "Город прибытия"),
                () -> assertEquals(properties.getProperty("departureDate"),
                        assertFilledFieldBTPage.getFieldDepartureDate(), "Даты выбытия"),
                () -> assertEquals(properties.getProperty("returnDate"),
                        assertFilledFieldBTPage.getFieldReturnDate(), "Даты прибытия"));
    }

    public void assertEqualsBT (){
    String validation_failed = "Список командируемых сотрудников не может быть пустым";
    assertEquals(validation_failed, errorMessagePage.getValidationFailed(), "Нет сообщения сообщение:" +
            "'Список командируемых сотрудников не может быть пустым'");
    }
}
