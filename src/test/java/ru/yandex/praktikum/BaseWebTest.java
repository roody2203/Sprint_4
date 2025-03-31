package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.EnvConfig;

import java.time.Duration;

public class BaseWebTest {
    protected WebDriver driver;

    @Before
    public void StartUp() {
        // Создаем драйвер для браузера Chrome
        //WebDriverManager.chromedriver().setup();

        //driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.IMPLICITY_WAIT));

        // Создаем драйвер для браузера Firefox
        WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.IMPLICITY_WAIT));
        driver.manage().window().maximize();
    }

    @After
    public void teardown() {
        //chrome_driver.quit();
        driver.quit();
    }
}
