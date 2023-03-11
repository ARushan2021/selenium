package ru.ibs_selenium.extension;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static ru.appline.project.DriverManager.closeDriver;
import static ru.appline.project.DriverManager.getWebDriver;

public class DriverExtension implements BeforeAllCallback, AfterAllCallback {


    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        closeDriver();
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        getWebDriver();
    }
}
