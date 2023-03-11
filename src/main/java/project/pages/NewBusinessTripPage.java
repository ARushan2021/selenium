package project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.BasePage;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class NewBusinessTripPage extends BasePage {

    @FindBy(xpath = "//*[@id='main-menu']/ul/li[2]/a/span[text()='Расходы']")
    private WebElement expensesBtn;

    @FindBy(xpath = "//*[@id='main-menu']/ul/li[2]/a/span/ancestor::li//ul[@class='dropdown-menu menu_level_1']")
    private WebElement menuExpenses;

    @FindBy(xpath = "//span[text()='Командировки']")
    private WebElement businessTripBtn;

    @FindBy(xpath = "//a[@title='Создать командировку']")
    private WebElement newBusinessTripBtn;

    @FindBy(xpath = "//h1[@class='user-name']")
    private WebElement headerCreateBusinessTrip;

    public void createBusinessTrip (){
        expensesBtn.click();
        wait.until(visibilityOf(menuExpenses));
        businessTripBtn.click();
        loading();
        newBusinessTripBtn.click();
        loading();
        wait.until(visibilityOf(headerCreateBusinessTrip));
    }
}
