package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private static final String name_of_folder = "Learning programming";
    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyFirstList(name_of_folder);
            articlePageObject.closeArticle();
        } else {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.goToMainWikiPage();
        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyList();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isIOS()) {
            myListsPageObject.closeSyncYourSavedArticlesQuestionWindow();
        }
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder);
        }
        myListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyListAndDeleteOneFrom()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String first_article_title = articlePageObject.getArticleTitle();
        String second_article_title = "Appium";

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyFirstList(name_of_folder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        articlePageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring(second_article_title);
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyExistigList(name_of_folder);
            articlePageObject.closeArticle();
        } else {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.goToMainWikiPage();
        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyList();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isIOS()) {
            myListsPageObject.closeSyncYourSavedArticlesQuestionWindow();
        }
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder);
        }

        myListsPageObject.swipeByArticleToDelete(first_article_title);
        myListsPageObject.assertThereIsNotArticleWithThisTitle(first_article_title);
        myListsPageObject.waitForArticleByTitleAndOpenIt(second_article_title);

        if (Platform.getInstance().isAndroid()) {
            assertEquals(
                    "Cannot find title of remaining article",
                    articlePageObject.getArticleTitle(), second_article_title
            );
        } else {
            assertEquals(
                    "",
                    articlePageObject.getArticleNavigationBar(), second_article_title
            );
        }
    }
}
