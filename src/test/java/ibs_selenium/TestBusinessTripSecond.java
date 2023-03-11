package ibs_selenium;

import ibs_selenium.extension.DriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import project.BasePage;
import project.pages.steps.ErrorMessageStep;
import project.pages.steps.LoginSteps;

import java.util.Properties;

import static project.properties.TestProperties.getInstance;

@ExtendWith(DriverExtension.class)
class TestBusinessTripSecond extends BasePage {

    private final Properties properties = getInstance().getProperties();
    private final LoginSteps loginSteps = new LoginSteps();
    private final ErrorMessageStep errorMessageStep = new ErrorMessageStep();
    private final BaseTestAssert baseTestAssert = new BaseTestAssert();

    @Test
    void test() {
        //Авторизация
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
        // Проверка заполнения полей
        baseTestAssert.AssertAllBT();
        //Нажимаем кнопку "Сохранить и закрыть"
        errorMessageStep.saveAndClose();
        // Проверка, что на странице появилось сообщение: "Список командируемых сотрудников не может быть пустым"
        baseTestAssert.assertEqualsBT();

        errorMessageStep.printErrorMessage();
    }
}
/*
1) Значения для проверки подтягиваются из environment.properties, кроме трех полей,
которые заполняются на кирилице. Сначала все было успешно,
потом java начала читать значения из файлики не в той кодировке. Кодировка везде стоит UTF-8

2) assertAll и assertEquals добавить в main/java/prodject/ не получилось,
т.к. туда не подтягивается библиотека org.junit.jupiter. Ассерты вынес на отдельную страничку.
 */
