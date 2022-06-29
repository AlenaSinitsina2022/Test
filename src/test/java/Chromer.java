import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Chromer {
    public static WebDriver driver;
    public static StartPage startPage;
    static ChromeOptions chromeOptions = new ChromeOptions();

    public static WebDriver start(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        chromeOptions.addArguments("--headless");
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://zen.yandex.ru/");

        return driver;
    }

    public static void initElements(){
        startPage = new StartPage(driver);
    }

    public static void closeDriver() {
        driver.close();
    }
}
