package pages;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class SearchPage extends TestBase {

    //URL
    public static final String SEARCH_URL = "https://www.autohero.com/de/search/";

    //Locators
    public String filterByFirstRegistrationXpath = "//*[@data-qa-selector='filter-year']";
    public String filterByYearXpath = "//*[@data-qa-selector='filter-year']//*[text()='Beliebig' and contains(@class,'selectedOption')]/..";
    public String year2015Xpath = "//*[@data-qa-selector='select' and @name='yearRange.min']/*[@data-qa-selector-value='2015']";
    public String sortMenuXpath = "//*[@data-qa-selector='select' and @name='sort']";
    public String descOrderButtonXpath = "//*[@data-qa-selector='select' and @name='sort']/*[text()='Höchster Preis']";
    public String resultProductXpath = "//*[@data-qa-selector='results-found']/*[@data-qa-selector-type='LIST']/*";
    public String resultProductPriceXpath = "//*[@data-qa-selector='results-found']/*[@data-qa-selector-type='LIST']//*[@data-qa-selector='price']";
    public String lastPageNumberXpath = "//*[@class='pagination']/*[last()-2]";
    public String nextPageButtonXpath = "//*[@class='pagination']/*[last()-1]//*[@aria-label='Next']";

    Actions actions = new Actions(webDriver);

    public static void tearDown() {
        webDriver.quit();
    }

    public static void open(String url) {
        webDriver.get(url);
    }

    public static WebElement findElementByXpath(String xpath) {
        return webDriver.findElement(By.xpath(xpath));
    }

    public List<WebElement> findElementsByXpath(String xpath) {
        return webDriver.findElements(By.xpath(xpath));
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public Matcher getRegistrationDate(String sequence, String yearPattern) {
        Pattern pattern = Pattern.compile(yearPattern);
        Matcher matcher = pattern.matcher(sequence);
        matcher.find();
        return matcher;
    }

    public void addElementsToResultList(List<WebElement> list, List newList) {
        IntStream.range(0, list.size()).forEach(
                i -> {
                    String element = list.get(i).getText();
                    newList.add(element);
                }
        );
    }

    public void clickNextButtonIfPageIsNotLast(int lastPageNumber, int i, String xpath) {
        WebElement nextButton = findElementByXpath(xpath);
        if (i != lastPageNumber) {
            scrollIntoView(nextButton);
            actions.moveToElement(nextButton).click().build().perform();
        }
    }

    public void waitSomeTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
