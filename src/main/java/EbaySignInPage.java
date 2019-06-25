import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EbaySignInPage {
    private final WebDriver driver;

    public EbaySignInPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

//    public EbaySignInPage(WebDriver driver) {
//        this.driver = driver;
//    }
     @FindBy(xpath = "//*[@id=\"gh-ug\"]/a")
     private WebElement signInLink;
    @FindBy(id = "userid")
    private WebElement getLogin;
    @FindBy(id ="pass")
    private  WebElement getPassword;
    @FindBy (id = "sgnBt")
    private WebElement signInButton;
    @FindBy(id = "gh-cart-i")
    private WebElement shoppingCart;
    @FindBy(css = "#errf")
    private WebElement message;
    @FindBy(css = ".start-shop")
    private WebElement startShopButton;

    public EbaySignInPage getSignInPage () {
        signInLink.click();
        return  new EbaySignInPage(driver);
    }

    public EbaySignInPage getLogin(String Login) {
        getLogin.clear();
        getLogin.sendKeys(Login);
        return this;
    }

    public EbaySignInPage getPassword(String Password) {
        getPassword.clear();
        getPassword.sendKeys(Password);
        return this;
    }

    public EbayMainPageFactory clickOnSignInButton() {
        signInButton.click();
        return new EbayMainPageFactory(driver);
    }

    public  EbayShoppingCart getShoppingCart () {
        shoppingCart.click();
        return new EbayShoppingCart(driver);
    }
    public  String getMessage () {
        message.isDisplayed();
        return message.getText();
    }
    public WebElement startShoppingButton () {
        return startShopButton;
    }

}

