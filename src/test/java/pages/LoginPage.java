package pages;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import utils.logs.JSErrorLogs;
import utils.logs.Log;

public class LoginPage extends BasePage {
    /**
     * Constructor
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Web Elements
     */
    By userNameId                = By.id("email");
    By passwordId                = By.id("password");
    By loginButtonId             = By.id("loginButton");
    By errorMessageUsernameXpath = By.xpath("//*[@id=\"loginForm\"]/div[1]/div/div");
    By errorMessagePasswordXpath = By.xpath("//*[@id=\"loginForm\"]/div[2]/div/div");
    By accept = By.cssSelector(".banner__accept-button");

    /**
     * Page Methods
     */

    public LoginPage acceptCookiesPrompt() throws InterruptedException {
        Log.info("Accept cookies prompt");
        //This Element is inside single shadow DOM.
        String cssSelectorForHost1 = ".efilli-layout-n11";
        Thread.sleep(1000);
        SearchContext shadow = driver.findElement(By.cssSelector(cssSelectorForHost1)).getShadowRoot();
        Thread.sleep(1000);
        shadow.findElement(accept).click();
        return this;
    }

    public LoginPage loginToN11(String username, String password) {
        Log.info("Trying to login the N11.");
        writeText(userNameId, username);
        writeText(passwordId, password);
        click(loginButtonId);
        return this;
    }

    //Verify Username Condition
    public LoginPage verifyLoginUserName(String expectedText) {
        Log.info("Verifying login username.");
        waitVisibility(errorMessageUsernameXpath);
        assertEquals(readText(errorMessageUsernameXpath), expectedText);
        return this;
    }

    //Verify Password Condition
    public LoginPage verifyLoginPassword(String expectedText) {
        Log.info("Verifying login password.");
        waitVisibility(errorMessagePasswordXpath);
        assertEquals(readText(errorMessagePasswordXpath), expectedText);
        return this;
    }

    //Verify Password Condition
    public LoginPage verifyLogError() {
        Log.info("Verifying javascript login errors.");
        assertTrue(JSErrorLogs.isLoginErrorLog(driver));
        return this;
    }
}