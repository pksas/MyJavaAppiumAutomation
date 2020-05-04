package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

import java.util.HashMap;

public class IosSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "id:Search Wikipedia";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SEARCH_TEXT_CLEAR_BUTTON = "id:Clear text";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCollectionView/XCUIElementTypeCell";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]/../XCUIElementTypeStaticText[contains(@name,'{DESCRIPTION}')]";

        TITLES_AND_DESCRIPTIONS = new HashMap<>();
        TITLES_AND_DESCRIPTIONS.put("Best", "Disambiguation page providing links to topics that could be referred to by the same search term");
        TITLES_AND_DESCRIPTIONS.put("Best Buy", "Consumer electronics retailer");
        TITLES_AND_DESCRIPTIONS.put("Best of the Super Juniors", "Professional wrestling tournament");
    }

    public IosSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
