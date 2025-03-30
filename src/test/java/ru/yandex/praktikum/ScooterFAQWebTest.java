package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.EnvConfig;

import ru.praktikum.HomePage;

import java.time.Duration;

public class ScooterFAQWebTest {

    private WebDriver chrome_driver;

    private WebDriver firefox_driver;

    @Before
    public void StartUp() {
        // Создаем драйвер для браузера Chrome
        WebDriverManager.chromedriver().setup();

        chrome_driver = new ChromeDriver();
        chrome_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.IMPLICITY_WAIT));

        // Создаем драйвер для браузера Firefox
        WebDriverManager.chromedriver().setup();

        firefox_driver = new FirefoxDriver();
        firefox_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.IMPLICITY_WAIT));
    }

    @Test
    // Тест текста FAQ в Chrome
    public void checkCorrectTextAnswerOnFAQHomePageChrome () throws InterruptedException {

        // Создает объект стартовой страницы
        HomePage homePage = new HomePage(chrome_driver);

        // Открывает стартовую страницу
        homePage.openHomePage(); //ок

        // Скроллит до FAQ
        homePage.scrollToTheHomePageFAQ();

        // Закрывает куки
        homePage.closeCookie();

        // Проверяет текст панелей
        homePage.checkPanels();


    }

    @Test
    // Тест текста FAQ в Mozilla
    public void checkCorrectTextAnswerOnFAQHomePageFirefox () throws InterruptedException {

        // Cоздаем объект стартовой страницы
        HomePage homePage = new HomePage(firefox_driver);

        // Открываем стартовую страницу
        homePage.openHomePage();

        // Скроллим до FAQ
        homePage.scrollToTheHomePageFAQ();

        // Закрываем куки
        homePage.closeCookie();

        // Проверяем текст панелей
        homePage.checkPanels();
    }

    @After
    public void teardown() {
        chrome_driver.quit();
        firefox_driver.quit();
    }

}