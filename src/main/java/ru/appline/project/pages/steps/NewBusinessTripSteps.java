package ru.appline.project.pages.steps;

import io.qameta.allure.Step;
import ru.appline.project.pages.NewBusinessTripPage;

public class NewBusinessTripSteps {

    //@Step("Открытие страницы новой командировки")
    public FilledFieldBTSteps newBusinessTrip (){
        NewBusinessTripPage newBusinessTripPage = new NewBusinessTripPage();
        newBusinessTripPage.createBusinessTrip();

    return new FilledFieldBTSteps();

    }
}
