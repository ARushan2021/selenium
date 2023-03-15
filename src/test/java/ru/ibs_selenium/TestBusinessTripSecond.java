package ru.ibs_selenium;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.appline.project.pages.steps.NewBusinessTripSteps;
import ru.ibs_selenium.extension.AllureExtension;
import ru.ibs_selenium.extension.DriverExtension;

import java.util.Properties;

import static ru.appline.project.properties.TestProperties.getInstance;

@ExtendWith({DriverExtension.class, AllureExtension.class})
@DisplayName("Проверка заполнения новой заявки на командировку (2)")
@Link("http://training.appline.ru/")
@Owner("Абдурахманов Рушан")
@Epic("Курс: selenium")
@Feature("Заполнение полей training.appline.ru")
@Story("Заполнение Командировки")
class TestBusinessTripSecond extends BaseTest {

    private final Properties properties = getInstance().getProperties();
    private final BaseTest baseTest = new BaseTest();
    private final NewBusinessTripSteps newBusinessTripSteps = new NewBusinessTripSteps();


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет, " +
            "что нельзя зарегистрировать новую командировку не заполнив поля командируемых сотрудников")
    void test() {
        //Авторизация
        baseTest.authorization();
        //Открытие странички с новой командировкой
        newBusinessTripSteps
                .newBusinessTrip()
                //Заполнение полей
                .filledField(properties.getProperty("inputArrivalCity"),
                        properties.getProperty("departureDate"),
                        properties.getProperty("returnDate"))
                //Проверяем введенные значения во всех полях
                // и проверяем сообщение: "Список командируемых сотрудников не может быть пустым"
                .assertBT();
    }
}
/*
1) Значения для проверки подтягиваются из environment.properties, кроме трех полей,
которые заполняются на кирилице. Сначала все было успешно,
потом java начала читать значения из файлики не в той кодировке. Кодировка везде стоит UTF-8
main/java/ru/appline/project/pages/AssertFilledFieldBTPage - AssertAllBT()
 */
