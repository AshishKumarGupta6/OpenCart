package TestCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public static WebDriver driver;
    public static Properties prop;
    public static Logger logger;

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browser) throws IOException {
        logger = LogManager.getLogger(this.getClass());
        prop = new Properties();
        String configPath = System.getProperty("user.dir")
                + "\\src\\test\\resources\\config.properties";

        FileInputStream fis = new FileInputStream(configPath);
        prop.load(fis);
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        driver.manage().deleteAllCookies();

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
