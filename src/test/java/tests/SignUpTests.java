package tests;

import com.github.javafaker.Faker;
import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.ContactListPage;
import pages.SplashPage;
import utils.RandomUtils;

public class SignUpTests extends AppiumConfig {
    RandomUtils randomUtils = new RandomUtils();
    String email = randomUtils.generateEmail(7);

    boolean isFlagUserLogin = false;
    boolean flagIsPopUpErrorDisplays = false;

    Faker faker = new Faker();
    String randomEmail = faker.internet().emailAddress();
    @AfterMethod
    public void afterMethod() {
        if (isFlagUserLogin) {
            isFlagUserLogin = false;
            new ContactListPage(driver).logOut();
        } else if (flagIsPopUpErrorDisplays) {
            flagIsPopUpErrorDisplays = false;
            new AuthenticationPage(driver).clickOkBtnAlert();
        }
    }

    @Test
    public void positiveRegTest(){
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().reg(UserDTO.builder()
                        .email(randomEmail)
                        .password("Beer12345!")
                        .build())
                        .validateContactListOpened());
    }

    @Test
    public void negativeRegEmptyEmail(){
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillPassword("Beer12345")
                .clickRegButtonNegative().validateErrorTitleAlertCorrect());
    }
}
