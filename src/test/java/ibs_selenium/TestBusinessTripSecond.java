package ibs_selenium;

import ibs_selenium.extension.DriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import project.BasePage;
import project.pages.AssertFilledFieldBTPage;
import project.pages.ErrorMessagePage;
import project.pages.steps.*;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static project.properties.TestProperties.getInstance;

@ExtendWith(DriverExtension.class)
class TestBusinessTripSecond extends BasePage {

    private final Properties properties = getInstance().getProperties();
    private final LoginSteps loginSteps = new LoginSteps();
    //private final NewBusinessTripSteps newBusinessTripSteps = new NewBusinessTripSteps();
    //private final FilledFieldBTSteps filledFieldBTSteps = new FilledFieldBTSteps();
    //private final AssertFilledFieldBTSteps assertFilledFieldBT = new AssertFilledFieldBTSteps();
    private final AssertFilledFieldBTPage assertFilledFieldBTPage = new AssertFilledFieldBTPage();
    private final ErrorMessagePage errorMessagePage = new ErrorMessagePage();
    private final ErrorMessageStep errorMessageStep = new ErrorMessageStep();

    @Test
    void test() {
        //Шаг.1 Авторизация
        loginSteps
                .login(properties.getProperty("LOGIN"),
                    properties.getProperty("PASSWORD"))
                //Шаг.2 Страница "Командировки"
                .newBusinessTrip()
                //Шаг.3 Заполнение полей
                .filledField(properties.getProperty("inputArrivalCity"),
                            properties.getProperty("departureDate"),
                            properties.getProperty("returnDate"))
                //Нажимаем кнопку сохранить, что-бы введенное значения сохранились в тэгах, атрибутах value, checked
                .assertBT();


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
                        assertFilledFieldBTPage.getFieldReturnDate(), "Даты прибытия")
        );
        //Нажимаем кнопку "Сохранить и закрыть"
        errorMessageStep.saveAndClose();
        // Проверка, что на странице появилось сообщение: "Список командируемых сотрудников не может быть пустым"
        // На странице два тэга с таким xpath, мы выбираем первый.
        String validation_failed = "Список командируемых сотрудников не может быть пустым";
        assertEquals(validation_failed, errorMessagePage.getValidationFailed(), "Нет сообщения сообщение:" +
                "'Список командируемых сотрудников не может быть пустым'");

        errorMessageStep.printErrorMessage();

    }

}
/*
1) Значения для проверки подтягиваются из environment.properties, кроме трех полей,
которые заполняются на кирилице. Сначала все было успешно,
потом java начала читать значения из файлики не в той кодировке(Плавающий баг). Кодировка везде стоит UTF-8
2) assertAll и assertEquals добавить в main/java/prodject/ не получилось,
т.к. туда не подтягивается библиотека org.junit.jupiter. Ассерты оставил на страничке с тестом.
 */
