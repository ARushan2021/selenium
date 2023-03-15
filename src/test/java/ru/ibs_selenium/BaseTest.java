package ru.ibs_selenium;

import ru.appline.project.BasePage;
import ru.appline.project.pages.steps.LoginSteps;

import java.util.Properties;

import static ru.appline.project.properties.TestProperties.getInstance;

class BaseTest extends BasePage {

    private final Properties properties = getInstance().getProperties();
    private final LoginSteps loginSteps = new LoginSteps();

    public void authorization() {
        //Авторизация
        loginSteps
                .login(properties.getProperty("LOGIN"),
                        properties.getProperty("PASSWORD"));
    }
}


