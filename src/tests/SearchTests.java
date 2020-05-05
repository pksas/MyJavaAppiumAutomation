package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        String search_line = "Linkin park Diskography";
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticle();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        String search_line = "Java1111111111";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.ClickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testSearchAndCancelSearch()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Some word");
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticle();
        assertTrue("No more than one search result was found", amount_of_search_results > 1);
        searchPageObject.clickSearchClearButton();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testSearchWordPresentInAllSearchResults()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("best");
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticle();
        assertTrue("There're less than 3 search result", amount_of_search_results > 2);

        searchPageObject.waitForSearchElementsByTitleAndDescriptions();
    }

    @Test
    public void testDefaultTextInSearchPlate()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();

        searchPageObject.checkingDefaultTextInSearchPlate();
    }
}
