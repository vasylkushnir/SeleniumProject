import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EbayRegisterPage {
    private  final WebDriver driver;

    public EbayRegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getTitleFromRegisterPage () {
        return driver.getTitle();
    }
}
