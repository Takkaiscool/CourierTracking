package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/***
 * @author Arunkumar C
 */
public class PageControls {

    public void launchBrowser(String browser) {
        WebdriverManager.setDriver(browser);

    }

    public void launchBrowser() {
        String browser = System.getProperty("browser", "chrome");
        WebdriverManager.setDriver(browser);
    }

    public String getUrl() {
        String returnUrl = WebdriverManager.getDriver().getCurrentUrl();
        return returnUrl;
    }

    public void loadURL(String url) {
        WebdriverManager.getDriver().get(url);
    }

    private void waitForAlertPopup(int timeout) {
        WebDriverWait wait = new WebDriverWait(WebdriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(int timeout) {
        waitForAlertPopup(timeout);
        WebdriverManager.getDriver().switchTo().alert().accept();
    }

    public void declineAlert(int timeout) {
        waitForAlertPopup(timeout);
        WebdriverManager.getDriver().switchTo().alert().dismiss();
    }

    public String getAlertText(int timeout) {
        waitForAlertPopup(timeout);
        String text = WebdriverManager.getDriver().switchTo().alert().getText();
        return text;
    }

    public String getTitle() {
        String title = WebdriverManager.getDriver().getTitle();
        return title;
    }

    public void closeBrowser() {
        WebdriverManager.getDriver().close();
    }

    public void quitBrowser() {
        WebdriverManager.getDriver().quit();

    }

    public byte[] takeScreenShot() {
        byte[] screeshot = ((TakesScreenshot) WebdriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        return screeshot;
    }

    public void maximizeWindow() {
        WebdriverManager.getDriver().manage().window().maximize();
    }

    public void setWindowSize(int x, int y) {
        WebdriverManager.getDriver().manage().window().setSize(new Dimension(x, y));
    }

    public void refreshPage() {
        WebdriverManager.getDriver().navigate().refresh();
    }

    public void implicitWait(int timeInSec) {
        WebdriverManager.getDriver().manage().timeouts().implicitlyWait(timeInSec, TimeUnit.SECONDS);
    }

    public void removeImplicitWait() {
        WebdriverManager.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

    }

    public void pageLoadTimeout(int timeInSec) {
        WebdriverManager.getDriver().manage().timeouts().pageLoadTimeout(timeInSec, TimeUnit.SECONDS);
    }

    public void fullScreen() {
        WebdriverManager.getDriver().manage().window().fullscreen();
    }

    public void deleteAllCookies() {
        WebdriverManager.getDriver().manage().deleteAllCookies();
    }

    public void deleteCookie(Cookie cookie) {
        WebdriverManager.getDriver().manage().deleteCookie(cookie);
    }

    public void setCookie(Cookie cookie) {
        WebdriverManager.getDriver().manage().addCookie(cookie);
    }

    public String getEntirePageSource() {
        String source = WebdriverManager.getDriver().getPageSource();
        return source;
    }

    public void navigateToMainWindow() {
        Set<String> windowHandles = WebdriverManager.getDriver().getWindowHandles();
        String mainWindow = windowHandles.iterator().next();
        WebdriverManager.getDriver().switchTo().window(mainWindow);
    }

    public void navigateToLastChildWindow() {
        Set<String> windowHandles = WebdriverManager.getDriver().getWindowHandles();
        Iterator<String> handles = windowHandles.iterator();
        String lastWindow = new String();
        while (handles.hasNext()) {
            lastWindow = handles.next();
        }
        WebdriverManager.getDriver().switchTo().window(lastWindow);
    }

    public void navigateToWindow(int windowNumber) {
        Set<String> windowHandles = WebdriverManager.getDriver().getWindowHandles();
        Iterator<String> handles = windowHandles.iterator();
        String lastWindow = new String();
        while (handles.hasNext() && windowNumber > 0) {
            lastWindow = handles.next();
            windowNumber--;
        }
        WebdriverManager.getDriver().switchTo().window(lastWindow);
    }


    public void scrollToBottomPage() {
        JavascriptExecutor jsExcutor = (JavascriptExecutor) WebdriverManager.getDriver();
        jsExcutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToMiddlePage() {
        JavascriptExecutor jsExcutor = (JavascriptExecutor) WebdriverManager.getDriver();
        jsExcutor.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
    }

    public void scrollToQuaterPage() {
        JavascriptExecutor jsExcutor = (JavascriptExecutor) WebdriverManager.getDriver();
        jsExcutor.executeScript("window.scrollTo(0, document.body.scrollHeight/4)");
    }

    public void setLocalStorage(String key, String value) {
        JavascriptExecutor js=(JavascriptExecutor)WebdriverManager.getDriver();
        js.executeScript(String.format("window.localStorage.setItem('%s','%s');",key,value));
    }

    public void clearSessionStorage() {
        SessionStorage localStorage = ((WebStorage) WebdriverManager.getDriver()).getSessionStorage();
        localStorage.clear();

    }
}
