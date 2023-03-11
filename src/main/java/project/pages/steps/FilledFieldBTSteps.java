package project.pages.steps;

import project.pages.FilledFieldBTPage;

public class FilledFieldBTSteps {
    public AssertFilledFieldBTSteps filledField (String inputArrivalCity, String departureDate, String returnDate){
        FilledFieldBTPage filledFieldBTPage = new FilledFieldBTPage();
        filledFieldBTPage.FilledFieldBT(inputArrivalCity, departureDate, returnDate);


        return new AssertFilledFieldBTSteps();
    }

}
