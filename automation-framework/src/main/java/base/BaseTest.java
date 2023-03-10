package base;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    private WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("headless", "false");
        if (System.getProperty("os.name").contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", "browser-driver/chromedriver-windows.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "browser-driver/chromedriver");
        }
        String headless = System.getProperty("headless");

        ChromeDriverManager.chromedriver();
        if("true".equals(headless)) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
        } else {
            driver = new ChromeDriver();
        }
        driver.get("https://www.saucedemo.com/");
    }

    @AfterSuite
    public void afterSuite() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
