package ru.appline.project.pages.steps;

import io.qameta.allure.Step;
import ru.appline.project.pages.FilledFieldBTPage;

public class FilledFieldBTSteps {
    //@Step("Заполнение полей командировки")
    public AssertFilledFieldBTSteps filledField (String inputArrivalCity, String departureDate, String returnDate){
        FilledFieldBTPage filledFieldBTPage = new FilledFieldBTPage();
        filledFieldBTPage.FilledFieldBT(inputArrivalCity, departureDate, returnDate);


        return new AssertFilledFieldBTSteps();
    }

}
