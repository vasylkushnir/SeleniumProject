import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class TestExamples  {
    private WebDriver driver;
    private EbayMainPageFactory ebayMain;
    private String login;
    private String password;
    private String incorrectPassword;

    @BeforeTest
    public void getProperties() throws IOException,java.io.FileNotFoundException {
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream("src/test/resources/properties");
        properties.load(file);
        login = properties.getProperty("prop.login");
        password = properties.getProperty("prop.password");
        incorrectPassword = properties.getProperty("prop.incorrectPassword");
        file.close();
    }


//        @BeforeTest
//        public void beforeTest() throws java.lang.InterruptedException {
//            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver();
//            EbayMainPageFactory.open(driver);
//        }
//
//        @AfterTest
//        public void afterTest() {
//            if (driver != null) {
//                driver.quit();
//                driver = null;
//            }
//        }

        @AfterMethod
        public void afterMethod() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }

//        @BeforeMethod
//        public void beforeMethod() throws java.lang.InterruptedException {
//            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver();
//            EbayMainPageFactory.open(driver);
//        }

@BeforeMethod
@Parameters({"browser"})
public void setUp(String browser) throws java.lang.InterruptedException {
    driver = WebDriverFactory.createDriver(browser);
    driver.get("https://www.ebay.com/");
  //  EbayMainPageFactory.open(driver);
    ebayMain = new EbayMainPageFactory(driver);
}
        @Test(priority = 1)
        @Parameters({"filterCriteria"})
        public void addProductToWatchList (String filterCriteria)  {
            EbaySignInPage ebaySignInPage = new EbaySignInPage(driver);
            ebaySignInPage.getSignInPage();
            ebaySignInPage.getLogin(login);
            ebaySignInPage.getPassword(password);
            ebaySignInPage.clickOnSignInButton();
            EbayMainPageFactory ebayMainPage = new EbayMainPageFactory(driver);
            EbayMainPageFactory ebayMainPageFactory = new EbayMainPageFactory(driver);
            ebayMainPage.enterFilterCriteria(filterCriteria).clickOnSearchButton();
            ebayMainPageFactory.getProductDetails().clickOnWathchLink();
            ebaySignInPage.getShoppingCart();
            Assert.assertTrue(ebaySignInPage.startShoppingButton().isEnabled());
        }
        @Test(priority = 2)
        @Parameters({"filterCriteria"})
        public void pageFactoryFindProduct (String filterCriteria) {
            EbayMainPageFactory mainPage = new EbayMainPageFactory(driver);
            EbayFilterResultPage resultPage = mainPage.getPageWithResults(filterCriteria);
            String title = resultPage.getTitleFromResultPage();
            Assert.assertTrue(title.contains(filterCriteria));
        }
        @Test(priority = 3)
        @Parameters({"incorrectFilterCriteria"})
        public void pageObjectFindNoProduct(String incorrectFilterCriteria) {
            EbayMainPageFactory mainPage = new EbayMainPageFactory(driver);
            EbayFilterResultPage resultPage = mainPage.getPageWithResults(incorrectFilterCriteria);
            Assert.assertTrue(resultPage.getMessage().isEnabled());

        }
        @Test(priority = 4)
        public void pageObjectfindProductsUnder10dolars()  {
            EbayProductListPage ebayProductListPage = new EbayProductListPage(driver);
            ebayProductListPage.getPageWithProducts().getProductSectionType().isPresented();
            Assert.assertEquals(false, ebayProductListPage.isItemPrice());
        }
        @Test(priority = 6)
        @Parameters({"titleContains"})
        public void getRegisterPage(String titleContains) {
            EbayMainPageFactory mainPage = new EbayMainPageFactory(driver);
            EbayRegisterPage registerPage = mainPage.getRegisterPage();
            Assert.assertTrue(registerPage.getTitleFromRegisterPage().contains(titleContains));
        }
        @Test(priority = 5)
        public void pageObjectgoToSocialNetwork()  {
            EbayMainPageFactory mainPage = new EbayMainPageFactory(driver);
            Assert.assertNotEquals(driver.getCurrentUrl(), mainPage.clickOnSMLink());

        }
        @Test(priority = 7)
        public void pageObjectLogIn () {
            EbaySignInPage ebaySignInPage = new EbaySignInPage(driver);
            ebaySignInPage.getSignInPage();
            ebaySignInPage.getLogin(login);
            ebaySignInPage.getPassword(password);
            ebaySignInPage.clickOnSignInButton();
            ebaySignInPage.getShoppingCart();
            Assert.assertTrue(driver.getCurrentUrl().contains("https://cart.ebay.com/"));
        }
        @Test(priority = 8)
        public void pageObjectLogInwithBadCred() {
            EbaySignInPage ebaySignInPage = new EbaySignInPage(driver);
            ebaySignInPage.getSignInPage();
            ebaySignInPage.getLogin(login);
            ebaySignInPage.getPassword(incorrectPassword);
            ebaySignInPage.clickOnSignInButton();
            Assert.assertNotNull(ebaySignInPage.getMessage());

        }
    }

