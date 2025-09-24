import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class DriverConfig {
    public static WebDriver driver;
    public static PropertyFile pdata = new PropertyFile();

    private static List<String> sellerIds = List.of("1", "2", "3", "4", "6", "1", "2", "3",  "4");
    private static List<String> marketplaceIds = List.of("47", "77", "115", "125", "2", "24", "16", "31", "38","126");

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public  static void main(String[] args) throws Exception {
//        WebDriverManager.safaridriver().setup();
//        WebDriver driver = new SafariDriver();
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver=new ChromeDriver();

//        WebDriverManager.firefoxdriver().setup();
//        WebDriver driver=new FirefoxDriver();

        FirefoxOptions opt= new FirefoxOptions();
        opt.addArguments("-headless");
        WebDriver  driver=new FirefoxDriver(opt);


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.get(pdata.getPropertyFile("urlQA"));
        driver.get(pdata.getPropertyFile("urlfuture"));
        driver.get(pdata.getPropertyFile("urlfuture"));
        System.out.println("URL entered");
        driver.findElement(By.xpath("(//button[@class='authorization__btn'])[81]")).click();

        driver.findElement(By.cssSelector("input[aria-label=\"auth-bearer-value\"]")).sendKeys(pdata.getPropertyFile("authorization"));
        driver.findElement(By.xpath("//button[text()='Authorize']")).click();
        driver.findElement(By.xpath("//button[text()='Close']")).click();
        driver.findElement(By.cssSelector("button[class='btn try-out__btn']")).click();
        System.out.println("clicked on try it out button");

        String yesterday = LocalDate.now().minusDays(1).format(DATE_TIME_FORMATTER);
        String today = LocalDate.now().format(DATE_TIME_FORMATTER);

        driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys(yesterday);
        driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys(today);
        driver.findElement(By.xpath("(//td[@class='parameters-col_description'])[5]")).click();
        driver.findElement(By.cssSelector("option[value=\"SINGLE_LINE_SINGLE_UNIT\"]")).click();

        for (String sellerId : sellerIds) {
            driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys(sellerId);

            for (String mpId : marketplaceIds) {

                driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(mpId);
//                System.out.println("MP id entered");
                System.out.println(mpId);

                // execute
                driver.findElement(By.xpath("//button[text()='Execute']")).click();
                Thread.sleep(2000);

                driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();

            }

            driver.findElement(By.cssSelector("input[type=\"text\"]")).clear();
        }

        driver.quit();

    }
}




