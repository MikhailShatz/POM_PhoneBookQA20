package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    AppiumDriver<MobileElement> driver;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this); // only for mobile testing
    }

    public MobileElement findElementBase(By by){
        return driver.findElement(by);
    }

    public void waitElement(MobileElement el, int time){
        new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(el));
    }
    public void typeTextBase(MobileElement el, String text){
        el.click();
        el.clear();
        el.sendKeys(text);
        driver.hideKeyboard();
    }

    public void clickBase(MobileElement el){
        el.click();
    }

    public String getTextBase(MobileElement el){
        return el.getText().toUpperCase().trim();
    }

    public boolean isTextEqual(MobileElement el, String text){
        if(getTextBase(el).equals(text.toUpperCase())){
            return true;
        }else{
            System.out.println("actual res: " + getTextBase(el) +
                    "expected res: " + text.toUpperCase());
        }
        return false;
    }

    public void pause(long mill){
        try {
            Thread.sleep(mill);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void scrollToElementBaseBy(By by){
        scrollToElementMobEl(findElementBase(by));
    }

    private void scrollToElementMobEl(MobileElement el) {
        Rectangle rect = el.getRect();
        int xTo = rect.getX() + rect.getWidth()/2;
        int yTo = rect.getY() + rect.getHeight()/2;

        TouchAction<?> action = new TouchAction<>(driver);
        action
                .longPress(PointOption.point(xTo, yTo))
                .moveTo(PointOption.point(xTo, 0))
                .release()
                .perform();
    }

    public void backBtnOnEmulator(){
        driver.navigate().back();
    }
}
