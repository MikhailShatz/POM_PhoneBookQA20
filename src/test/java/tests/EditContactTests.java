package tests;

import config.AppiumConfig;
import dto.ContactDTO;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.util.Random;

public class EditContactTests extends AppiumConfig {
    @BeforeClass
    public void beforeClass(){
        new SplashPage(driver).goToAuthPage()
                .login(UserDTO.builder()
                        .email("awqfwf@gmail.com")
                        .password("Beer12345!")
                        .build());
    }
    @AfterMethod
    public void afterClass(){
        new AddNewContactPage(driver).backBtnOnEmulator();
    }
    @Test
    public void EditOneContactPositiveByEmail(){
        ContactInfoPage page = new ContactInfoPage(driver);
        int i;
        i = new Random().nextInt(1000)+1000;
        System.out.println(i);
        ContactDTO contact = ContactDTO.builder()
                        .name("Geralt" + i)
                        .lastName("Gym"+  i)
                        .email("test" + i + "@gmail.sas")
                        .phone("1234567" + i)
                        .address("Haifa")
                        .description("contact: " + i)
                        .build();
        new ContactListPage(driver).clickBtnAddNewContact().addNewContact(contact);
        new EditContactPage(driver).moveContactByPhoneNumberToTheLeft(contact.getPhone())
                .updateContactByEmail();
        Assert.assertTrue(new ContactInfoPage(driver)
                .validateCreatedContactUpdated(contact.getPhone(), page.getFakerEmail()));

    }

    @Test
    public void EditOneContactPositiveByPhone(){
        ContactInfoPage page = new ContactInfoPage(driver);
        int i;
        i = new Random().nextInt(1000)+1000;
        System.out.println(i);
        ContactDTO contact = ContactDTO.builder()
                        .name("Geralt" + i)
                        .lastName("Gym"+  i)
                        .email("test" + i + "@gmail.sas")
                        .phone("1234567" + i)
                        .address("Haifa")
                        .description("contact: " + i)
                        .build();
        new ContactListPage(driver).clickBtnAddNewContact().addNewContact(contact);
        new EditContactPage(driver).moveContactByPhoneNumberToTheLeft(contact.getPhone())
                .updateContactByPhone();
        Assert.assertTrue(new ContactInfoPage(driver)
                .validateCreatedContactUpdatedPhone(contact.getName(), contact.getLastName(), page.getRandomPhone()));
    }
}
