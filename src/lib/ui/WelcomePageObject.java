package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject
{
    private static final String
        STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT_LINK = "id:New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANG_TEXT = "id:Add or edit preferred languages",
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_TEXT = "id:Learn more about data collected",
        NEXT_LINK = "id:Next",
        GET_STARTED_BUTTON = "id:Get started",
        SKIP_LINK = "id:Skip";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, String.format("Cannot find '%s' link", STEP_LEARN_MORE_LINK), 10);
    }

    public void waitForNewWaysToExploreText()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT_LINK, String.format("Cannot find '%s' link",STEP_NEW_WAYS_TO_EXPLORE_TEXT_LINK), 10);
    }

    public void waitForAddOrEditPreferredLangText()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_TEXT, String.format("Cannot find '%s' link", STEP_ADD_OR_EDIT_PREFERRED_LANG_TEXT), 10);
    }

    public void waitForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_TEXT, String.format("Cannot find '%s' link", STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_TEXT), 10);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_LINK, String.format("Cannot find and click '%s' button", NEXT_LINK), 10);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON, String.format("Cannot find and click '%s' button", GET_STARTED_BUTTON), 10);
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP_LINK, "Cannot find and click 'Skip' link", 5);
    }
}
