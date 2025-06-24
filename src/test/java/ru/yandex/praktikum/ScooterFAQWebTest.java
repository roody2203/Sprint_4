package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.EnvConfig;

import ru.praktikum.HomePage;
import ru.praktikum.ReferenceData;

import java.sql.Ref;
import java.time.Duration;
@RunWith(Parameterized.class)
public class ScooterFAQWebTest extends BaseWebTest {
    private final int headingIndex;
    private final String headingText;
    private final String panelText;

    //
    public ScooterFAQWebTest(int headingIndex, String headingText, String panelText) {
        this.headingIndex = headingIndex;
        this.headingText = headingText;
        this.panelText = panelText;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {1}")
    public static Object[][] getPanelText() {
        return new Object[][] {
                {0, ReferenceData.HOW_MUCH_IS_IT_AND_HOW_PAY_HEADING, ReferenceData.HOW_MUCH_IS_IT_AND_HOW_PAY_PANEL},
                {1, ReferenceData.WANT_SEVERAL_SCOOTERS_AT_ONCE_HEADING, ReferenceData.WANT_SEVERAL_SCOOTERS_AT_ONCE_PANEL},
                {2, ReferenceData.HOW_RENTAL_TIME_IS_CALCULATED_HEADING, ReferenceData.HOW_RENTAL_TIME_IS_CALCULATED_PANEL},
                {3, ReferenceData.CAN_I_ORDER_A_SCOOTER_FOR_TODAY_HEADING, ReferenceData.CAN_I_ORDER_A_SCOOTER_FOR_TODAY_PANEL},
                {4, ReferenceData.IS_IT_POSSIBLE_TO_EXTEND_THE_ORDER_HEADING, ReferenceData.IS_IT_POSSIBLE_TO_EXTEND_THE_ORDER_PANEL},
                {5, ReferenceData.ARE_YOU_BRINGING_THE_CHARGER_HEADING, ReferenceData.ARE_YOU_BRINGING_THE_CHARGER_PANEL},
                {6, ReferenceData.IS_IT_POSSIBLE_TO_CANCEL_THE_ORDER_HEADING, ReferenceData.IS_IT_POSSIBLE_TO_CANCEL_THE_ORDER_PANEL},
                {7, ReferenceData.LIVE_ACROSS_THE_MKAD_HEADING, ReferenceData.LIVE_ACROSS_THE_MKAD_PANEL}
        };
    }

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

        // Проверяет текст панели
        homePage.checkPanel(headingIndex, headingText, panelText);
    }
}