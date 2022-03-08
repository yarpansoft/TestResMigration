package TestPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebTest {
    WebDriver webDriver;
    String loginFieldXpath = "//input[@id='username']";
    String submitLoginButtonXpath = "//input[@id='loginButton']";
    String passwordFieldXpath = "//input[@type='password']";
    String submitPasswordButtonXpath = "//input[@id='loginButton']";
    String invalidLoginErrorMessageXpath = "//div[@id='responseMessage']";
    String invalidLoginErrorMessageText = "There is no account for the username or email you entered.";
    String invalidPasswordErrorMessageXpath = "//div[@class='error-status FieldState-message FieldState_error-message']";
    String invalidPasswordErrorMessageText = "Incorrect password.";
    String successLoginPageTitle = "Evernote Web";
    String baseUrlPageTitle = "Welcome Back";

    @Test
    public void test_01() {
        Assert.assertEquals(webDriver.getTitle(), baseUrlPageTitle);
    }

    @Test
    public void testShouldBeFailed() {
        Assert.assertEquals(webDriver.getTitle(), successLoginPageTitle);
    }

    @Test
    public void testShouldBeCrashed() {
        webDriver.findElement(By.xpath(successLoginPageTitle)).click();
    }




    @BeforeTest
    public void driverInit() {
        System.setProperty("webdriver.chrome.driver", TestData.driverPath);
        //WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        //webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.navigate().to(TestData.baseUrl);
    }

    @AfterTest
    public void tearDown() {
        webDriver.close();
        webDriver.quit();
    }

    @AfterMethod
    public void testResult(){

    }


    //@Test(description = "Successful Login")
    public void testSuccessfulLogin() {
        //TestName = Successful Login
        webDriver.findElement(By.xpath(loginFieldXpath)).sendKeys(TestData.usernameValid);
        webDriver.findElement(By.xpath(submitLoginButtonXpath)).click();
        webDriver.findElement(By.xpath(passwordFieldXpath)).sendKeys(TestData.passwordValid);
        webDriver.findElement(By.xpath(submitPasswordButtonXpath)).click();
        Assert.assertEquals(webDriver.getTitle(), successLoginPageTitle);
    }

    //@Test(description = "Test with Invalid Username")
    public void testInvalidUsername() {
        webDriver.findElement(By.xpath(loginFieldXpath)).sendKeys(TestData.usernameInvalid);
        webDriver.findElement(By.xpath(submitLoginButtonXpath)).click();
        String errorMessage = waitUntilElementVisibleByXpath(invalidLoginErrorMessageXpath, TestData.timeoutDefault).getText();
        System.out.println("errorMessage: " + errorMessage);
        Assert.assertEquals(errorMessage, invalidLoginErrorMessageText);
    }


    public WebElement waitUntilElementVisibleByXpath(String xPath, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSeconds);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        return element;
    }
}
