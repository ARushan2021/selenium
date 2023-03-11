package project.pages.steps;

import project.pages.ErrorMessagePage;

public class ErrorMessageStep {

    ErrorMessagePage errorMessagePage = new ErrorMessagePage();

    public void saveAndClose() {
        errorMessagePage.saveAndClose();
    }
    public void printErrorMessage(){
        errorMessagePage.printMessage();
    }
}
