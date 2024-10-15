package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/***
 * @author Arunkumar C
 */

public class ElementControls {

    public void type(WebElement element, String data) {
        element.sendKeys(data);
    }

    public void typeAndEnter(WebElement element, String data) {
        element.sendKeys(data + Keys.ENTER);
    }

    public void typeAndTab(WebElement element, String data) {
        element.sendKeys(data + Keys.TAB);
    }

    public void click(WebElement element) {
        element.click();
    }

    public String getText(WebElement element) {
        String textValue = element.getText();
        return textValue;

    }

    public String getTextFieldData(WebElement element) {
        String textFieldValue = element.getAttribute("value");
        return textFieldValue;
    }

    public void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getAttribute(WebElement element, String attribute) {
        String attributeValue = element.getAttribute(attribute);
        return attributeValue;
    }

    public void waitTillVisible(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(WebdriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitTillStaleness(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(WebdriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public void waitTillInteract(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(WebdriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitTillInVisible(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(WebdriverManager.getDriver(),Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }


    public void waitTillUnPresence(By element, int timeout) {
        WebDriverWait wait = new WebDriverWait(WebdriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver webDriver) {
                try {
                    return webDriver.findElements(element).size() == 0;
                } catch (StaleElementReferenceException exception) {
                    return webDriver.findElements(element).size() == 0;
                }

            }
        });
    }

    public void waitTillPresence(By element, int timeout) {
        WebDriverWait wait = new WebDriverWait(WebdriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void selectDropDownByIndex(WebElement element, int index) {
        Select sel = new Select(element);
        sel.selectByIndex(index);
    }

    public void selectDropDownByValue(WebElement element, String value) {
        Select sel = new Select(element);
        sel.selectByValue(value);
    }

    public void selectDropDownByVisibleText(WebElement element, String value) {
        Select sel = new Select(element);
        sel.selectByVisibleText(value);
    }

    public void deselectDropDownByIndex(WebElement element, int index) {
        Select sel = new Select(element);
        sel.deselectByIndex(index);
    }

    public void deselectDropDownByValue(WebElement element, String value) {
        Select sel = new Select(element);
        sel.deselectByValue(value);
    }

    public void deselectDropDownByVisibleText(WebElement element, String value) {
        Select sel = new Select(element);
        sel.deselectByVisibleText(value);
    }

    public void rightClick(WebElement element) {
        Actions act = new Actions(WebdriverManager.getDriver());
        act.contextClick(element).build().perform();
    }

    public void rightClick() {
        Actions act = new Actions(WebdriverManager.getDriver());
        act.contextClick().build().perform();
    }

    public void moveToElement(WebElement element) {
        Actions act = new Actions(WebdriverManager.getDriver());
        act.moveToElement(element).perform();
    }

    public void moveToElement(WebElement element, int x, int y) {
        Actions act = new Actions(WebdriverManager.getDriver());
        act.moveToElement(element, x, y).build().perform();
    }

    public void dragAndDrop(WebElement drag, WebElement drop) {
        Actions act = new Actions(WebdriverManager.getDriver());
        act.dragAndDrop(drag, drop).build().perform();
    }

    public void dragAndDrop(WebElement drag, int x, int y) {
        Actions act = new Actions(WebdriverManager.getDriver());
        act.dragAndDropBy(drag, x, y).build().perform();
    }

    public void uploadFile(WebElement element, String filePath) {
        element.sendKeys(filePath);
    }

    public boolean isVisible(WebElement element) {
        boolean isDisplayed = element.isDisplayed();
        return isDisplayed;

    }

    public boolean isPresent(By element) {
        boolean isDisplayed = WebdriverManager.getDriver().findElements(element).size() > 0;
        return isDisplayed;

    }

    public boolean isEnabled(WebElement element) {
        boolean isEnabled = element.isEnabled();
        return isEnabled;
    }

    public boolean isSelected(WebElement element) {
        boolean isSelected = element.isSelected();
        return element.isSelected();
    }

    public WebElement find(By locator) {
        WebElement element = WebdriverManager.getDriver().findElement(locator);
        return element;
    }

    public List<WebElement> finds(By locator) {
        List<WebElement> elements = WebdriverManager.getDriver().findElements(locator);
        return elements;
    }

    public void scrollToElement(WebElement element) {
        moveToElement(element);
    }

    public void scrollToElementUsingJSExecutor(WebElement element) {
        ((JavascriptExecutor) WebdriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(false);", element);
    }
    public void scrollToElementUsingJSExecutor1(WebElement element) {
        ((JavascriptExecutor) WebdriverManager.getDriver()).executeScript("arguments[0].scrollTo(0,arguments[0].scrollHeight)", element);
    }

    public void clickOnElementUsingJSExecutor(WebElement element) {
        ((JavascriptExecutor) WebdriverManager.getDriver()).executeScript("arguments[0].click();", element);
    }

    public void waitTillTextPresentInTextField(By locator, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(WebdriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
    }

    public String getCssValue(WebElement element, String attribute) {
        String cssValue = element.getCssValue(attribute);
        return cssValue;

    }

    public void clearText(WebElement element) {
        element.clear();
    }

    public void clearTextForRect(WebElement element) {
        element.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
    }

}
