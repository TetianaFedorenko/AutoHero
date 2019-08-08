package ui_tests;

import core.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.SearchPage;
import testData.TestData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pages.SearchPage.findElementByXpath;

public class SearchSearchPageTests extends TestBase {

    SearchPage searchPage = new SearchPage();
    List<WebElement> singlePagePriceResultList;
    List<WebElement> singlePageResultList;
    List<String> actualListOfRegistrationYear = new ArrayList();
    List<String> actualPriceList = new ArrayList();


    //Verify all cars are filtered by first registration ( 2015+ ) and sorted by price descending
    @Test
    public void verifyCarsFilteredByRegistrationYearAndPriceDesc() {
        int lastPageNumber;
        //Step 1: Filter for First registration ( Erstzulassung ). Filter for FROM 2015
        searchPage.clickElement(findElementByXpath(searchPage.filterByFirstRegistrationXpath));
        searchPage.clickElement(findElementByXpath(searchPage.filterByYearXpath));
        searchPage.clickElement(findElementByXpath(searchPage.year2015Xpath));
        //Apply Filter
        //Sort cars by Price Descending ( Höchster Preis )
        searchPage.clickElement(findElementByXpath(searchPage.sortMenuXpath));
        searchPage.clickElement(findElementByXpath(searchPage.descOrderButtonXpath));
        searchPage.waitSomeTime(1000);
        lastPageNumber = Integer.parseInt(findElementByXpath(searchPage.lastPageNumberXpath).getText());
        IntStream.range(0, lastPageNumber).forEach(
                i -> {
                    searchPage.waitSomeTime(1000);
                    singlePageResultList = searchPage.findElementsByXpath(searchPage.resultProductXpath);
                    searchPage.addElementsToResultList(singlePageResultList, actualListOfRegistrationYear);

                    singlePagePriceResultList = searchPage.findElementsByXpath(searchPage.resultProductPriceXpath);
                    searchPage.addElementsToResultList(singlePagePriceResultList, actualPriceList);

                    searchPage.clickNextButtonIfPageIsNotLast(lastPageNumber, i, searchPage.nextPageButtonXpath);
                }
        );

        //Verify all cars are filtered by first registration ( 2015+ )
        IntStream.range(0, actualListOfRegistrationYear.size()).forEach(
                i -> {
                    Matcher matcher = searchPage.getRegistrationDate(actualListOfRegistrationYear.get(i), TestData.availableYearPattern);
                    assertTrue(matcher.group().matches(TestData.requiredYearPattern), TestData.NOT_FILTERED_BY_FIRST_REGISTRATION);
                }
        );

        //Verify all cars are sorted by price descending
        IntStream.range(1, actualPriceList.size()).forEach(
                i -> {
                    double previousElement = Double.parseDouble(actualPriceList.get(i - 1).substring(TestData.FIRST_PRICE_CHAR, TestData.LAST_PRICE_CHAR));
                    double nextElement = Double.parseDouble(actualPriceList.get(i).substring(TestData.FIRST_PRICE_CHAR, TestData.LAST_PRICE_CHAR));
                    assertTrue(nextElement <= previousElement, TestData.NOT_SORTED_IN_DESC_ORDER);
                }
        );
    }
}
