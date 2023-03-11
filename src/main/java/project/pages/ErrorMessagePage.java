package project.pages;

import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.BasePage;

public class ErrorMessagePage extends BasePage {

    @FindBy(xpath = "//button[contains(text(), 'Сохранить и закрыть')]")
    private WebElement saveAndCloseBtn;
    @FindBy(xpath = "//div/div/fieldset/div/div/div/span[@class='validation-failed']")
    private WebElement validationFailed;

    //Нажимаем кнопку "Сохранить и закрыть"
    public void saveAndClose() {
        saveAndCloseBtn.click();
        loading();
    }
    //Значение поля с ошибкой
    public String getValidationFailed() {
        return validationFailed.getText();
    }

    //Выводим сообщение, что все успешно
    public void printMessage() {
        System.out.println("Сообщение:" +
                " 'Список командируемых сотрудников не может быть пустым' успешно сформировалось!!!");
    }

}
