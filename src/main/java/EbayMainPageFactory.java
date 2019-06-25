import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EbayMainPageFactory {
    private final WebDriver driver;

    @FindBy(css ="#gh-ac")
    private WebElement input;

    @FindBy(css = "#gh-btn")
    private  WebElement button;

    @FindBy(linkText = "register")
    private WebElement linkText;
    @FindBy(css = "#gf-BIG > table > tbody > tr > td:nth-child(3) > ul > li:nth-child(8) > a")
    private WebElement linkFacebook;
    @FindBy(css ="#srp-river-results-listing1 > div > div.s-item__info.clearfix > a > h3")
    private WebElement product;
    @FindBy(css ="#vi-atl-lnk > a > span.vi-atw-txt" )
    private WebElement wathcLink;


    public EbayMainPageFactory(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public static EbayMainPageFactory open (WebDriver driver) throws InterruptedException {
        driver.get("https://www.ebay.com/");
        Thread.sleep(1000);
        return new EbayMainPageFactory(driver);
    }


    public EbayMainPageFactory enterFilterCriteria (String filterCriteria){
        input.sendKeys(filterCriteria);
        return this;
    }
    public EbayFilterResultPage clickOnSearchButton () {
        button.click();
        return new EbayFilterResultPage(driver);
// запис другого варіанту
//         EbayFilterResultPage page2 = new EbayFilterResultPage(driver);
//         return page2;
    }
    public EbayFilterResultPage getPageWithResults (String filterCriteria) {
        return enterFilterCriteria(filterCriteria).clickOnSearchButton();
    }
    public EbayRegisterPage getRegisterPage () {
        linkText.click();
        return new EbayRegisterPage(driver);
    }
    public EbayMainPageFactory clickOnSMLink () {
        linkFacebook.click();
        return this;
    }
    public EbayMainPageFactory getProductDetails () {
        product.click();
        return this;
    }
    public EbayMainPageFactory clickOnWathchLink () {
        wathcLink.click();
        return this;
    }

}
