package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTiONS_ADD_TO_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        SEARCH_BUTTON,
        FOLDER_BY_NAME_TPL,
        GO_TO_MAIN_PAGE_BUTTON,
        NAVIGATION_BAR;

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFolderNameToSavingElement(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    /* TEMPLATES METHODS */

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on the page", 15);
    }

    public String getArticleTitle()
    {
        waitForCloseArticleButton();
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public WebElement waitForCloseArticleButton()
    {
        return this.waitForElementPresent(CLOSE_ARTICLE_BUTTON, "Cannot find close article button on the page", 15);
    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    80
            );
        } else {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);
        }

    }

    public void addArticleToMyFirstList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTiONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press 'ok' button",
                5
        );
    }

    public void closeArticle()
    {

        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find 'x' link",
                5
        );
    }

    public void initSearchInput()
    {
        this.waitForElementAndClick(
                SEARCH_BUTTON,
                "Cannot find search button in article",
                5
        );
    }

    public void addArticleToMyExistigList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTiONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                getFolderNameToSavingElement(name_of_folder),
                "Cannot find '" + name_of_folder + "' folder for adding article to it",
                5
        );
    }

    public void addArticlesToMySaved()
    {
        this.waitForElementAndClick(OPTiONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 15);
    }

    public void goToMainWikiPage()
    {
        this.waitForElementAndClick(GO_TO_MAIN_PAGE_BUTTON, "Cannot find 'W' button to go to the main page", 5);
    }

    public String getArticleNavigationBar()
    {
        WebElement element = this.waitForElementPresent(NAVIGATION_BAR, "Cannot find navigation bar on the page");
        return element.getAttribute("name");
    }
}
