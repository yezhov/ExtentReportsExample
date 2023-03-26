package tests;

import static utils.extentreports.ExtentTestManager.startTest;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test(priority = 0, description = "Invalid Login Scenario with wrong username and password.")
    public void invalidLoginTest_InvalidUserNameInvalidPassword(Method method) throws InterruptedException {
        //ExtentReports Description
        startTest(method.getName(), "Invalid Login Scenario with invalid username and password.");
        homePage
                .goToN11()
                .goToLoginPage()
                .acceptCookiesPrompt()
                .loginToN11("onur@swtestacademy.com", "11122233444")
                .verifyLogError();
    }

    @Test(priority = 1, description = "Invalid Login Scenario with empty username and password.")
    public void invalidLoginTest_EmptyUserEmptyPassword(Method method) {
        //ExtentReports Description
        startTest(method.getName(), "Invalid Login Scenario with empty username and password.");
        homePage
                .goToN11()
                .goToLoginPage()
                .loginToN11("", "")
                .verifyLoginUserName("Lütfen e-posta adresinizi girin.")
                .verifyLoginPassword("Bu alanın doldurulması zorunludur.");
    }


}