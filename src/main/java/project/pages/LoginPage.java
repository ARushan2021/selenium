package project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.BasePage;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//form[@id='login-form']")
    private WebElement loginForm;

    @FindBy(id = "prependedInput")
    private WebElement loginRow;

    @FindBy(id = "prependedInput2")
    private WebElement passwordRow;

    @FindBy(id = "_submit")
    private WebElement submitBtn;

    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement subtitle;

    public void enterLoginPassword(String login, String password) {
        wait.until(visibilityOf(loginForm)); //Авторизация на сайте
        loginRow.sendKeys(login);
        passwordRow.sendKeys(password);
    }

    public void submitClick() {
        submitBtn.click();
        wait.until(visibilityOf(subtitle));//Проверка заголовка "Панель быстрого запуска"
    }
}
