package project.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.BasePage;

public class FilledFieldBTPage extends BasePage {

    @FindBy(xpath = "//option[@value='1']")
    private WebElement divisionField;

    @FindBy(xpath = "//a[@id='company-selector-show']")
    private WebElement companySelectorShow;

    @FindBy(xpath = "//span[@class='select2-chosen']")
    private WebElement chooseSelect;

    @FindBy(xpath = "//div[text()='(Хром) Призрачная Организация Охотников']")
    private WebElement chromeSelect;

    @FindBy(xpath = "//input[@data-name='field__1']")
    private WebElement checkBoxTickets;

    @FindBy(xpath = "//input[@name='crm_business_trip[arrivalCity]']")
    private WebElement arrivalCity;

    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]")
    private WebElement departureDatePlan;

    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")
    private WebElement returnDatePlan;

    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")
    private WebElement closeCalendarDatePlan;

    public void FilledFieldBT(String inputArrivalCity, String departureDate, String returnDate) {
        //Поле Подразделение
        divisionField.click();
        //Поле Принимающая организация
        companySelectorShow.click();
        chooseSelect.click();
        chromeSelect.click();
        //Проставление чек-бокса "Заказ билетов"
        checkBoxTickets.click();
        // Заполнение поля "Город прибытия" "Санкт-Петербург"
        arrivalCity.sendKeys(inputArrivalCity);
        // Заполнение поля "Планируемая дата выезда*" "04.03.2023"
        departureDatePlan.sendKeys(departureDate);
        //Заполнение поля "Планируемая дата возвращения "04.04.2023"
        returnDatePlan.sendKeys(returnDate);
        // При заполнении поля "Планируемая дата возвращения" открывается календарик, закрываем его.
        closeCalendarDatePlan.sendKeys(Keys.ESCAPE);
    }
}

