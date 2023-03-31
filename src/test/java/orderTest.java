import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class orderTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--disable-dev-shm-usage");
        option.addArguments("--no-sandbox");
        option.addArguments("--headless");
        driver = new ChromeDriver(option);
    }

    @AfterEach
    void rearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void correctDataTest() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Алексей");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79054358754");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button.button_view_extra.button_size_m.button_theme_alfa-on-white")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        assertEquals(expected, actual);
    }

    // @Test
    //  void shouldTest1() {
    //    open("http://localhost:9999");
    //    $("span[data-test-id=name] input").setValue("Алексей");
    //    $("span[data-test-id=phone] input").setValue("+79054358754");
    //    $("[data-test-id=agreement]").click();
    //    $("button.button.button_view_extra.button_size_m.button_theme_alfa-on-white").click();
    //    $("[data-test-id=order-success]").shouldHave(text(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    //  }

}
