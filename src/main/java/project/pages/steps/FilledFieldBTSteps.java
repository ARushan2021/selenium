package project.pages.steps;

import project.pages.FilledFieldBTPage;

public class FilledFieldBTSteps {
    public void filledField (String inputArrivalCity, String departureDate, String returnDate){
        FilledFieldBTPage filledFieldBTPage = new FilledFieldBTPage();
        filledFieldBTPage.FilledFieldBT(inputArrivalCity, departureDate, returnDate);


    }

}
