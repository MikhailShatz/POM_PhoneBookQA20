package tests;

import config.AppiumConfig;
import dto.ContactDTO;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ContactListPage;
import pages.SplashPage;

import java.util.Random;

public class DeleteContactTests extends AppiumConfig {
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
        new ContactListPage(driver).logOut();
    }
    @Test
    public void deleteOneContactPositive(){
        int i;
        i = new Random().nextInt(1000)+1000;
        System.out.println(i);
        Assert.assertFalse(new ContactListPage(driver).clickBtnAddNewContact()
                .addNewContact(ContactDTO.builder()
                        .name("Geralt" + i)
                        .lastName("Gym"+  i)
                        .email("test" + i + "@gmail.sas")
                        .phone("1234567" + i)
                        .address("Haifa")
                        .description("contact: " + i)
                        .build())
                .moveContactByPhoneNumberToTheRight("1234567" + i)
                .clickYesBtnPopUpForContactDelete()
                .isPhoneNumberOnThePage("1234567" + i));
    }

    @Test
    public void deleteAllContacts(){
        int i;
        i = new Random().nextInt(1000)+1000;
        System.out.println(i);
        Assert.assertTrue(new ContactListPage(driver).clickBtnAddNewContact()
                .addNewContact(ContactDTO.builder()
                        .name("Geralt" + i)
                        .lastName("Gym"+  i)
                        .email("test" + i + "@gmail.sas")
                        .phone("1234567" + i)
                        .address("Haifa")
                        .description("contact: " + i)
                        .build())
                .deleteAllContacts()
                .validateContactListEmpty());
    }
}
