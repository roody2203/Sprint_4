package ru.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait driverWaiter;

    // Локатор выпадающего списка FAQ
    private final By dropDownList = By.className("Home_FAQ__3uVm4");

    // Кнопка Заказать в шапке страницы
    private final By orderButtonTop = By.className("Button_Button__ra12g");

    // Кнопка Заказать в середине страницы
    private final By orderButtonBottom = By.className("Button_Middle__1CSJM");

    // Кнопка закрытия куки
    private final By closeCookieButton = By.id("rcc-confirm-button");

    // Начало имени локатора строки
    private final String headingNamePrefix = "accordion__heading-";

    // Начало имени локатора выпадающего текста строки
    private final String panelNamePrefix = "accordion__panel-";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.driverWaiter = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    // Открывает стартовую страницу
    public void openHomePage() {
        driver.get(EnvConfig.BASE_URL);
    }

    // Скроллит до FAQ
    public void scrollToTheHomePageFAQ () {
        WebElement scrollDropDownList = driver.findElement(dropDownList);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", scrollDropDownList);
    }

    // Закрывает куки
    public void closeCookie() {
        driver.findElement(closeCookieButton).click();
    }

    // Возвращает текст элемента
    public String textOfPanel (By element) {
       return driver.findElement(element).getText();
    }

    // Кликает по кнопке Заказать в шапке профиля
    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    // Кликает по кнопке Заказать в середине страницы
    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    // Проверяет текст в выпадающей панели
    public void checkPanel(int headingIndex, String expectedHeadingText, String expectedPanelText) {
        // Формируем селектор для заголовка
        String headingName = headingNamePrefix + String.valueOf(headingIndex);
        // Формируем селектор для панели
        String panelName = panelNamePrefix + String.valueOf(headingIndex);

        // Создаем локатор для заголовка
        By headingLocator = By.id(headingName);
        // Создаем локатор для панели
        By panelLocator = By.id(panelName);

        // Ищем элемент заголовка
        WebElement heading = driver.findElement(headingLocator);
        // Проверяем текст заголовка
        MatcherAssert.assertThat(heading.getText(), is(expectedHeadingText));

        // Нажимаем на заголовок
        heading.click();

        // Дожидаемся загрузки панели
        driverWaiter.until(ExpectedConditions.visibilityOfElementLocated(panelLocator));

        // Ищем элемент панели
        WebElement panel = driver.findElement(panelLocator);
        // Проверяем текст панели
        MatcherAssert.assertThat(panel.getText(), is(expectedPanelText));
    }
}
