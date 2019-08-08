package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class WebDriverFactory {

    static final String CHROMEDRIVER_PATH = "./src/test/resources/chromedriver.exe";
    static final String FIREFOXDRIVER_PATH = "./src/test/resources/geckodriver.exe";

    static {
        try {
            System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
            System.setProperty("webdriver.gecko.driver", FIREFOXDRIVER_PATH);
        } catch (Exception e) {
            System.out.println("Cannot launch FireFox or Chrome driver \n" + e.getMessage());
        }
    }

    public static WebDriver getWebDriver(String browserType) throws IOException {
        switch (browserType) {
            case "FIREFOX":
                return new FirefoxDriver();
            case "CHROME":
                return new ChromeDriver();
            default:
                throw new IllegalArgumentException("Browser is not supported" + browserType);
        }
    }
}
