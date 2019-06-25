import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EbayShoppingCart {
    private final WebDriver driver;

    public EbayShoppingCart(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(css = ".start-shop")
    WebElement startShopButton;

//    public EbayShoppingCart(WebDriver driver) {
//        this.driver = driver;
//    }
    public String getTitle () {
        return driver.getTitle();
    }
    public WebElement startShoppingButton () {
        return startShopButton;
     }
}
