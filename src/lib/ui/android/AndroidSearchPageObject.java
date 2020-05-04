package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

import java.util.HashMap;

public class AndroidSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_TEXT_CLEAR_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{TITLE}')]/..//*[contains(@text,'{DESCRIPTION}')]";

        TITLES_AND_DESCRIPTIONS = new HashMap<>();
        TITLES_AND_DESCRIPTIONS.put("Best", "Wikimedia disambiguation page");
        TITLES_AND_DESCRIPTIONS.put("Best Buy", "Consumer electronics retailer");
        TITLES_AND_DESCRIPTIONS.put("Best of the Super Juniors", "NJPW tournament");
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
