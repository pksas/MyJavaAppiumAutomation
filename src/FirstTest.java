import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FirstTest extends CoreTestCase {

    private MainPageObject mainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();

        mainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSimpleCrush()
    {
        WebElement test_element = mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_featured_article_card_article_title"),
                "sooo...",
                5
        );
        try {
            System.out.println("text is: " + test_element.getAttribute("text"));
        } catch (NoSuchElementException e) {
            System.out.println("no text(((");
        }
        try {
            System.out.println( "index is: " + test_element.getAttribute("index"));
        } catch (NoSuchElementException e) {
            System.out.println("no index(((");
        }
        try {
            System.out.println("class is: " + test_element.getAttribute("class"));
        } catch (NoSuchElementException e) {
            System.out.println("no class(((");
        }
        try {
            System.out.println("resource-id is: " + test_element.getAttribute("resource-id"));
        } catch (NoSuchElementException e)  {
            System.out.println("no resource-id(((");
        }
        try {
            System.out.println("instance is: " + test_element.getAttribute("instance"));
        } catch (NoSuchElementException e) {
            System.out.println("no instance(((");
        }
}

    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.ClickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
}

    @Test
    public void testCompareArticleTitle()
    {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot open 'Object-oriented programming language' article",
                5
        );

        WebElement title_element = mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testSearchAndCancelSearch()
    {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Some word",
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/*[2]"),
                "Cannot find second topic searching by 'Some word'",
                15
        );

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot 'x' to cancel search",
                5
        );

        mainPageObject.waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/*[1]"),
                "Search result still present on the page",
                5
        );
    }

    @Test
    public void testDefaultTextInSearchPlate()
    {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.checkingDefaultTextInSearchPlate();
    }

    @Test
    public void testSearchWordPresentInAllSearchResults()
    {
        String search_word = "jaVa";

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_word,
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/*[1]"),
                "This search has no result!",
                15
        );

        List<WebElement> search_title_elements = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));

        for (WebElement element : search_title_elements) {
            boolean isSearchWordPresence = element.getAttribute("text").toLowerCase().contains(search_word.toLowerCase());
            Assert.assertTrue("The searched word is not in all titles of articles!!!", isSearchWordPresence);
        }
    }

    @Test
    public void testSwipeArticle()
    {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot open 'Appium' article in search",
                5
        );

        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        mainPageObject.swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the article",
                20
        );
    }

    @Test
    public void testSaveFirstArticleToMyList()
    {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot open 'Object-oriented programming language' article",
                5
        );

        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        mainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String name_of_folder = "Learning programming";

        mainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'ok' button",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find 'x' link",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                        "Cannot find navigation button to 'My list'",
                        5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find 'Learning programming' folder",
                5
        );

        mainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        mainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Linkin park Diskography";
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        mainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything result by request " + search_line,
                15
        );

        int amount_of_search_results = mainPageObject.getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Java11111";
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text='No results found']";

        mainPageObject.waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                15
        );

        mainPageObject.assertElementNoPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Java";
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot open 'Object-oriented programming language' topic searching by " + search_line,
                15
        );

        String title_before_rotation = mainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = mainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = mainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after second screen rotation",
                title_after_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot open 'Object-oriented programming language' article",
                5
        );

        driver.runAppInBackground(2);

        mainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' article after returning from background",
                5
        );
    }

    @Test
    public void testSaveTwoArticlesToMyListAndDeleteOneFrom()
    {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot open 'Object-oriented programming language' article",
                5
        );

        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find first article title",
                15
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options for saving first article",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add first article to reading list",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        mainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String name_of_folder = "Learning programming";

        mainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'ok' button",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc='Search Wikipedia']"),
                "Cannot find 'Search Wikipedia' input to find second article",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find 'search' input for the second article",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot open 'Appium' article in search",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options for adding second article to My list",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add second article to reading list",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find 'Learning programming' folder for adding second article to it",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close second article, cannot find 'x' link",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to 'My list'",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find 'Learning programming' folder with two articles",
                5
        );

        mainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find first saved article"
        );

        mainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete first saved article",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Appium']"),
                "Cannot find second article in my list",
                5
        );

        mainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_header_container']/*[@text='Appium']"),
                "Cannot find title of the second article",
                5
        );
    }

    @Test
    public void testTiltleInArticlePresence()
    {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Java";
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot open 'Object-oriented programming language' article",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/view_page_header_container']/*[@resource-id='org.wikipedia:id/view_page_title_text']";

        mainPageObject.assertElementPresent(
                By.xpath(search_result_locator),
                "We've not found some results by request '" + search_line + "'"
        );
    }
}