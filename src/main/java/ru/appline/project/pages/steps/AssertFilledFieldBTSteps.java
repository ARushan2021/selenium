package ru.appline.project.pages.steps;

import io.qameta.allure.Step;
import ru.appline.project.pages.AssertFilledFieldBTPage;

public class AssertFilledFieldBTSteps {

    AssertFilledFieldBTPage assertFilledFieldBTPage = new AssertFilledFieldBTPage();
    //@Step("Проверка заполненных полей командировки")
    public void assertBT() {
        assertFilledFieldBTPage.CheckedValue();
        assertFilledFieldBTPage.AssertAllBT();
        assertFilledFieldBTPage.saveAndClose();
        assertFilledFieldBTPage.assertEqualsBT();
        assertFilledFieldBTPage.printMessage();
    }
}
