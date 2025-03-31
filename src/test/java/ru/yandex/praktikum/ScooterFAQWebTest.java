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

public class ScooterFAQWebTest extends BaseWebTest {

    @Test
    // Тест текста FAQ в Chrome
    public void checkCorrectTextAnswerOnFAQHomePage () throws InterruptedException {

        // Создает объект стартовой страницы
        HomePage homePage = new HomePage(driver);

        // Открывает стартовую страницу
        homePage.openHomePage(); //ок

        // Скроллит до FAQ
        homePage.scrollToTheHomePageFAQ();

        // Закрывает куки
        homePage.closeCookie();

        // Проверяет текст панелей
        homePage.checkPanels();
    }
}