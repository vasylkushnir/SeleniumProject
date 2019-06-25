import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EbayProductListPage {
    private final WebDriver driver;
    private boolean itemPrice;

    public boolean isItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(boolean itemPrice) {
        this.itemPrice = itemPrice;
    }

    public EbayProductListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

//    public EbayProductListPage(WebDriver driver) {
//        this.driver = driver;
//    }
    @FindBy(css = "#mainContent > div.hl-cat-nav > ul > li.hl-cat-nav__js-tab.hl-cat-nav__no-sub > a")
    private WebElement section;
    @FindBy(xpath = "//*[@id=\"w9-bModCarousel-xCarousel-x-carousel-items\"]/ul/li[1]/div/a/div/img")
    private WebElement productSection;
    @FindBy(className = "s-item__price")
    private List<WebElement> listProduct;


    public  EbayProductListPage getPageWithProducts () {
        section.click();
        return new EbayProductListPage(driver);
    }
    public  EbayProductListPage getProductSectionType (){
        productSection.click();
        return this;
    }
    public Boolean isPresented (){
     //   List<WebElement> listProduct = driver.findElements(By.className("s-item__price"));
        Boolean isItemPrice = false;
        for(WebElement listProducts: listProduct)
        {
            Double itemPrice1 = Double.parseDouble(listProducts.getText().substring(1));
            if (itemPrice1 > 10.00) {
                isItemPrice = true;
            }
        }
        return isItemPrice;
    }

}
