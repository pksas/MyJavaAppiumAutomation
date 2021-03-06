package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
            SEARCH_INIT_ELEMENT ,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_TEXT_CLEAR_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL,
            SEARCH_PLATE_ELEMENT;

    protected static HashMap<String, String> TITLES_AND_DESCRIPTIONS;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchByTitleAndDescriptionElement(String title, String description)
    {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    }

    public void ClickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void clickSearchClearButton()
    {
        this.waitForElementAndClick(SEARCH_TEXT_CLEAR_BUTTON, "Cannot find and click 'x' button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button still present on the page", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticle()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything result by request",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNoPresent(SEARCH_RESULT_ELEMENT, "We've found any results");
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath =getResultSearchByTitleAndDescriptionElement(title, description);
        this.waitForElementPresent(
                search_result_xpath,
                String.format("There is no search result with title '%s' and description '%s'", title, description));
    }

    public void waitForSearchElementsByTitleAndDescriptions()
    {
        for(Map.Entry<String, String> entry : TITLES_AND_DESCRIPTIONS.entrySet()) {
            waitForElementByTitleAndDescription(entry.getKey(), entry.getValue());
        }
    }

    public void checkingDefaultTextInSearchPlate() {
        WebElement search_element = waitForElementPresent(
                SEARCH_PLATE_ELEMENT,
                "Cannot find search input",
                5
        );

        if (Platform.getInstance().isAndroid()) {
            Assert.assertEquals(
                    "We don't see default text 'Search…' in search plate!",
                    "Search…",
                    search_element.getAttribute("text")
            );
        } else {
            Assert.assertEquals(
                    "We don't see default text 'Search…' in search plate!",
                    "Search Wikipedia",
                    search_element.getAttribute("name")
            );
        }
    }
}
