package ru.appline.project.pages.steps;

import ru.appline.project.pages.FilledFieldBTPage;

public class FilledFieldBTSteps {

    public AssertFilledFieldBTSteps filledField(String inputArrivalCity, String departureDate, String returnDate) {
        FilledFieldBTPage filledFieldBTPage = new FilledFieldBTPage();
        filledFieldBTPage.FilledFieldBT(inputArrivalCity, departureDate, returnDate);


        return new AssertFilledFieldBTSteps();
    }

}
