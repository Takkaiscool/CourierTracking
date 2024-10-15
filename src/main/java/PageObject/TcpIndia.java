package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PageControls;
import utils.WebdriverManager;

import java.time.Duration;
import java.util.regex.Pattern;

public class TcpIndia {

    @FindBy(id = "ContentPlaceHolderForTop_ContentPlaceHolderQuickLinkForTop_TextBox1")
    public WebElement awbNo;

    @FindBy(xpath = "//button[text()='SEARCH']")
    public WebElement searchBtn;
    @FindBy(id = "ContentPlaceHolderMid_ContentPlaceHolder2_lbl_trackVal")
    public WebElement status;
    public TcpIndia(){
        PageFactory.initElements(WebdriverManager.getDriver(),this);
    }

    public String getStatus(String tracking) throws Exception {
        PageControls pageControls=new PageControls();
        pageControls.loadURL("https://www.tpcindia.com/");
        pageControls.maximizeWindow();
        WebDriverWait wait=new WebDriverWait(WebdriverManager.getDriver(), Duration.ofSeconds(120));
        awbNo.sendKeys(tracking);

        wait.until(new ExpectedCondition<Object>() {

            @Override
            public Object apply(WebDriver input) {
                System.out.println(input.findElement(By.id("ContentPlaceHolderForTop_ContentPlaceHolderQuickLinkForTop_txtCaptcha")).getAttribute("value"));
                return input.findElement(By.id("ContentPlaceHolderForTop_ContentPlaceHolderQuickLinkForTop_txtCaptcha")).getAttribute("value").length()==4;
            }
        });
        searchBtn.click();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(  "ContentPlaceHolderMid_ContentPlaceHolder2_lbl_trackVal"))).getText();

    }

}
