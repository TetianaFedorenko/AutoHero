package core;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.SearchPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static pages.SearchPage.open;
import static pages.SearchPage.tearDown;
import static testData.TestData.DRIVER;

public class TestBase {

    public static WebDriver webDriver;

    @BeforeAll
    public static void startDriver() throws IOException {
        webDriver = WebDriverFactory.getWebDriver(System.getProperty(DRIVER));
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void openPage() {
        open(SearchPage.SEARCH_URL);
    }

    @AfterAll
    public static void stopDriver() {
        tearDown();
    }
}
