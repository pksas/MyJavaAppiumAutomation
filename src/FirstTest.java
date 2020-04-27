import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
    public void testDefaultTextInSearchPlate()
    {
        mainPageObject.waitForElementAndClick(
                "xpath://*[contains(@text,'Search Wikipedia')]",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.checkingDefaultTextInSearchPlate();
    }
}