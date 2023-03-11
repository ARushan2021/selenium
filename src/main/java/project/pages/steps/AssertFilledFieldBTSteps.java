package project.pages.steps;

import project.pages.AssertFilledFieldBTPage;

public class AssertFilledFieldBTSteps {

    public ErrorMessageStep assertBT() {
        AssertFilledFieldBTPage assertFilledFieldBTPage = new AssertFilledFieldBTPage();
        assertFilledFieldBTPage.CheckedValue();

        return new ErrorMessageStep();
    }
}
