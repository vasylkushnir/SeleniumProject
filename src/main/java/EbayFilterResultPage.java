import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class EbayFilterResultPage {
    private final WebDriver driver;

    public EbayFilterResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(className = "srp-save-null-search__heading")
    private WebElement field;
//    public EbayFilterResultPage(WebDriver driver) {
//        this.driver = driver;
//    }

    public String getTitleFromResultPage() {
        return driver.getTitle();
    }

    public WebElement getMessage () {
        return field;
    }

//    public EbayFilterResultPage getProductDetails () {
//        WebElement product = driver.findElement(By.cssSelector("#srp-river-results-listing1 > div > div.s-item__info.clearfix > a > h3"));
//        product.click();
//        return this;
//    }

}


