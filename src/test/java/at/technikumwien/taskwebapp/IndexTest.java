package at.technikumwien.taskwebapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Tag("extended")
public class IndexTest {
    @LocalServerPort
    private long port;

    private WebDriver driver;
    private Wait<WebDriver> wait;

    @BeforeAll
    public static void setUpBeforeClass(){
        WebDriverManager.chromedriver().setup();
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
    }

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver(
                new ChromeOptions().setHeadless(true) // run without GUI
        );
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,3);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testIndexWithDONEState(){
        driver.get(getUrl(""));

        var button = driver.findElement(By.tagName("button"));
        assertEquals("Switch state", button.getText());

        var list = driver.findElements(By.tagName("li"));
        assertEquals(1,list.size());

        var h1 = driver.findElement(By.tagName("h1"));
        assertEquals("DONE ToDos", h1.getText());

        button.submit();

        wait.until(
                ExpectedConditions.urlToBe(getUrl("?state=TODO"))
        );
    }

    @Test
    public void testIndexWithDOINGState(){
        driver.get(getUrl("?state=DOING"));

        var button = driver.findElement(By.tagName("button"));
        assertEquals("Switch state", button.getText());

        var list = driver.findElements(By.tagName("li"));
        assertEquals(1,list.size());

        var h1 = driver.findElement(By.tagName("h1"));
        assertEquals("DOING ToDos", h1.getText());

        button.submit();

        wait.until(
                ExpectedConditions.urlToBe(getUrl("?state=TODO"))
        );
    }

    @Test
    public void testIndexWithTODOState(){
        driver.get(getUrl("?state=TODO"));

        var button = driver.findElement(By.tagName("button"));
        assertEquals("Switch state", button.getText());

        var list = driver.findElements(By.tagName("li"));
        assertEquals(1,list.size());

        var h1 = driver.findElement(By.tagName("h1"));
        assertEquals("TODO ToDos", h1.getText());

        button.submit();

        wait.until(
                ExpectedConditions.urlToBe(getUrl("?state=TODO"))
        );
    }

    private String getUrl(String path){
        return "http://localhost:"+port+"/"+path;
    }
}
