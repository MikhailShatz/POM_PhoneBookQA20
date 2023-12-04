package tests;

import config.AppiumConfig;
import dto.ContactDTO;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddNewContactPage;
import pages.ContactListPage;
import pages.SplashPage;

import java.util.Random;

public class AddNewContactTests extends AppiumConfig {
    boolean flagToCloseAlert = false;
    @BeforeClass
    public void beforeClass(){
        new SplashPage(driver).goToAuthPage()
                .login(UserDTO.builder()
                .email("awqfwf@gmail.com")
                .password("Beer12345!")
                .build());
    }
    @AfterClass
    public void afterClass(){
        //need to open contact page before logout
        new ContactListPage(driver).logOut();
    }
    @AfterMethod
    public void afterMethod(){
        if (flagToCloseAlert){
            flagToCloseAlert =false;
            new AddNewContactPage(driver).clickOkCloseAlert().backBtnOnEmulator();

        }
    }
    @Test
    public void positiveAddNewContact(){
        int i;
        i = new Random().nextInt(1000)+1000;
        System.out.println(i);
        ;
        Assert.assertTrue(new ContactListPage(driver).clickBtnAddNewContact()
                .addNewContact(ContactDTO.builder()
                        .name("Geralt" + i)
                        .lastName("Gym"+  i)
                        .email("test" + i + "@gmail.sas")
                        .phone("1234567" + i)
                        .address("Haifa")
                        .description("contact: " + i)
                        .build())
                    .validateCurrentContactCreated(i)); //homework
    }
    @Test
    public void negativeTestEmptyPhone(){
        int i;
        i = new Random().nextInt(1000)+1000;
        System.out.println(i);
        flagToCloseAlert = true;
        Assert.assertTrue(new ContactListPage(driver).clickBtnAddNewContact()
                .addNewContactNegative(ContactDTO.builder()
                        .name("Geralt" + i)
                        .lastName("Gym"+  i)
                        .email("test" + i + "@gmail.sas")
                        .phone("")
                        .address("Haifa")
                        .description("contact: " + i)
                        .build())
                .validateErrorMessage());
    }
}
