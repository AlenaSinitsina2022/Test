import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UI {
    StartPage startPage;

    @BeforeClass
    public void initChrome(){
        Chromer.start();
        Chromer.initElements();
        startPage= Chromer.startPage;
    }

    @Test
    public void test1 (){
        startPage.searchArticle("пирамида тестирования");
        startPage.checkArticle();
        startPage.searchArticle("");
    }

    @AfterClass
    public void after(){
        Chromer.closeDriver();
    }
}
