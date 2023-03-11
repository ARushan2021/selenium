package project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.BasePage;

public class AssertFilledFieldBTPage extends BasePage {

    @FindBy(xpath = "//button[@class='btn btn-success main-group action-button']")
    public WebElement checkedBtn;

    @FindBy(xpath = "//span[contains(text(), 'Research & Development')]")
    private WebElement divisionValue;

    @FindBy(xpath = "//input[@data-ftid='crm_business_trip_company']")
    private WebElement businessTripCompany;

    @FindBy(xpath = "//input[@data-ftid='crm_business_trip_tasks_1']")
    private WebElement checkBox;

    @FindBy(xpath = "//input[@name='crm_business_trip[departureCity]']")
    private WebElement departureCity;

    @FindBy(xpath = "//input[@name='crm_business_trip[arrivalCity]']")
    private WebElement arrivalCity;

    @FindBy(xpath = "//input[@name='crm_business_trip[departureDatePlan]']")
    private WebElement departureDate;

    @FindBy(xpath = "//input[@name='crm_business_trip[returnDatePlan]']")
    private WebElement returnDate;

    //Нажимаем кнопку сохранить, что-бы введенное значения сохранились в тэгах, атрибутах value, checked
    public void CheckedValue() {
        checkedBtn.click();
        loading();

    }

    public String getDivisionField() {
        return divisionValue.getText(); //Значение поля "Подразделение"
    }

    public String getOrganizationField() {
        return businessTripCompany.getAttribute("value"); //Значение "Принимающая организация"
    }
    public String getFieldCheckbox() {
        return checkBox.getAttribute("checked");//Значение поля чек-бокс "Задачи"
    }

    public String getFieldCityDisposal() {

        return departureCity.getAttribute("value");//Значение поля "Город выбытия"
    }
    public String getFieldCityArrival(){
        return arrivalCity.getAttribute("value");//Значение поля "Город выбытия"
    }
    public String getFieldDepartureDate(){ //Значение поля "даты выбытия" форматируем как в environment.properties
       String dtDep = departureDate.getAttribute("value");
       String dtDepField = dtDep.substring(8, 10) + "." + dtDep.substring(5, 7) + "." + dtDep.substring(0, 4);
       return dtDepField;
    }
    public String getFieldReturnDate() { //Значение поля "даты прибытия" форматируем как в environment.properties
        String dtRet = returnDate.getAttribute("value");
        String dtRetField = dtRet.substring(8, 10) + "." + dtRet.substring(5, 7) + "." + dtRet.substring(0, 4);
        return dtRetField;
    }
}


