package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IosArticlePageObject extends ArticlePageObject
{
    static
    {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTiONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        SEARCH_BUTTON = "id:Search Wikipedia";
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
    }

    public IosArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
