package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PageControls;
import utils.WebdriverManager;

import java.time.Duration;

public class StCourier {

    @FindBy(id = "track")
    public WebElement search;

    @FindBy(id = "awb_no")
    public WebElement awb_no;

    @FindBy(xpath = "//td[text()='Current Status']//following-sibling::td")
    public WebElement currentStatus;
    public StCourier(){
        PageFactory.initElements(WebdriverManager.getDriver(),this);
    }

    public String getStatus(String trackingnumber) throws Exception {
        PageControls pageControls=new PageControls();
        pageControls.loadURL("https://stcourier.com/track/shipment");
        pageControls.maximizeWindow();
        System.out.println(trackingnumber);
        Thread.sleep(2000);
        awb_no.sendKeys(trackingnumber);
        search.click();
        Thread.sleep(2000);
        if(awb_no.isDisplayed()&&WebdriverManager.getDriver().findElements(By.xpath("//td[text()='Current Status']//following-sibling::td")).isEmpty()){
            if(awb_no.getAttribute("value").contains("")){
                awb_no.sendKeys(trackingnumber);
            }
        }
        WebDriverWait wait = new WebDriverWait(WebdriverManager.getDriver(), Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Current Status']//following-sibling::td"))).getText();

    }
}
