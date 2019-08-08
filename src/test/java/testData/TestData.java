package testData;

public interface TestData {

    String DRIVER = "driver";

    String NOT_SORTED_IN_DESC_ORDER = "Cars are not sorted from highest to lowest price!";
    String NOT_FILTERED_BY_FIRST_REGISTRATION = "Cars are not filtered by first registration!";

    String availableYearPattern = "[0|1][0-9]/[1|2][9|0][0-9][0-9]";
    String requiredYearPattern = "[0|1][0-9]/201[5-9]";

    int FIRST_PRICE_CHAR = 0;
    int LAST_PRICE_CHAR = 6;
}
