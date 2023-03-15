package ru.appline.project.pages.steps;

import ru.appline.project.pages.AssertFilledFieldBTPage;

public class AssertFilledFieldBTSteps {

    AssertFilledFieldBTPage assertFilledFieldBTPage = new AssertFilledFieldBTPage();

    public void assertBT() {
        assertFilledFieldBTPage.CheckedValue();
        assertFilledFieldBTPage.AssertAllBT();
        assertFilledFieldBTPage.saveAndClose();
        assertFilledFieldBTPage.assertEqualsBT();
        assertFilledFieldBTPage.printMessage();
    }
}
