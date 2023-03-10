package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static project.DriverManager.getWebDriver;

public class BasePage {

    @FindBy(xpath = "//div[@class='loader-mask shown']")
    private WebElement loadingIcon;

    protected static WebDriver driver = getWebDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, ofSeconds(20));

    public BasePage() {
        initElements(driver, this);
    }

    public void loading() {
        wait.until(invisibilityOf(loadingIcon));
        }
}

