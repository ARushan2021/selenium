package project.pages.steps;

import project.pages.LoginPage;

public class LoginSteps {

    public void login(String login, String password){
        LoginPage loginPage = new LoginPage();
        loginPage.enterLoginPassword(login, password);
        loginPage.submitClick();
    }
}
