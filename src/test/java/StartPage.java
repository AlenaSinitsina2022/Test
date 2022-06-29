import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class StartPage {
    WebDriver driver;

    public StartPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "zen-ui-search__input")
    WebElement search;

    @FindBy(xpath = "//*[@aria-label=\"Найти\"]")
    WebElement findButton;

    @FindBy(xpath = "//*[@aria-label=\"О пирамидах в тестировании и реальных сложностях автоматизатора\"]")
    WebElement article;

    public void searchArticle (String nameArticle){
        search.sendKeys(nameArticle);
        findButton.click();
    }

    public void checkArticle(){
        article.isEnabled();
    }
}
