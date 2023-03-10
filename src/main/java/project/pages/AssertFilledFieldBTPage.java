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
    public String getFieldDepartureDate(){
        return departureDate.getAttribute("value");//Значение поля "даты выбытия"
    }
    public String getFieldReturnDate(){
        return returnDate.getAttribute("value");
    }



    //String divisionField = divisionValue.getText(); //Значение поля "Подразделение"
    //String organizationField = businessTripCompany.getAttribute("value");//Значение "Принимающая организация"
    //String fieldCheckbox = checkBox.getAttribute("checked");//Значение поля чек-бокс "Задачи"
    //String fieldCityDisposal = departureCity.getAttribute("value");//Значение поля "Город выбытия"
    //String fieldCityArrival = arrivalCity.getAttribute("value");//Значение поля "Город прибытия"
    //String fieldDepartureDate = departureDate.getAttribute("value");//Значение поля "даты выбытия"
    //String fieldReturnDate = returnDate.getAttribute("value");//Значение поля "даты прибытия"

/*        Assertions.assertAll("Следующее поле заполнено не верно: ",
                () -> assertEquals("Research & Development", divisionField, "Подразделение"),
                () -> assertEquals("(Хром) Призрачная Организация Охотников",
                        organizationField, "Организация"),
                () -> assertEquals("true", fieldCheckbox, "Поле задача"),
                () -> assertEquals("Россия, Москва", fieldCityDisposal, "Город выбытия"),
                () -> assertEquals("Санкт-Петербург", fieldCityArrival, "Город прибытия"),
                () -> assertEquals("2023-03-04", fieldDepartureDate, "Даты выбытия"),
                () -> assertEquals("2023-04-04", fieldReturnDate, "Даты прибытия")*/


}


