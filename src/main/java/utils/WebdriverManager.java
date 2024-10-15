package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


/***
 * @author Arunkumar C
 */

public class WebdriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    protected static void setDriver(String browserName) {
        boolean headless = System.getProperty("headless", "false").equalsIgnoreCase("true");
        String runEnv = System.getProperty("runEnv", "local");
        switch (browserName.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                //options.addArguments("user-data-dir=C:\\Users\\c8905845\\AppData\\Local\\Google\\Chrome\\User Data");//Remove it
                //String userDir=System.getProperty("user.dir");
                //options.addArguments("user-data-dir="+userDir+"/VendorComplianceUIAutomation/Dump/User Data");//Remove it
                options.addArguments("--disable-notifications", "--no-sandbox");
                options.setAcceptInsecureCerts(true);
                String downloadFilepath = System.getProperty("user.dir") + File.separator + "downloads";
                System.out.println(downloadFilepath);
                Map<String, String> chromePrefs = new HashMap<>();
                chromePrefs.put("download.default_directory", downloadFilepath);
                options.setExperimentalOption("prefs", chromePrefs);
                driver.set(new ChromeDriver(options));
                break;
            case "edge":
                EdgeOptions edgeOptions=new EdgeOptions();
                edgeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);

                driver.set(new EdgeDriver(edgeOptions));
                break;
            case "internetexplorer":
                InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                internetExplorerOptions.requireWindowFocus();
                internetExplorerOptions.introduceFlakinessByIgnoringSecurityDomains();
                internetExplorerOptions.ignoreZoomSettings();
                //internetExplorerOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
                DesiredCapabilities capabilities=new DesiredCapabilities();
                capabilities.acceptInsecureCerts();
                //capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
                driver.set(new InternetExplorerDriver(internetExplorerOptions));
                //driver.set(new InternetExplorerDriver(capabilities));
                break;
            case "safari":
                driver.set(new SafariDriver());
                break;
        }


    }
}
