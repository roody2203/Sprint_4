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

    // Локатор 1-ой строки
    private final By firstHeading = By.id("accordion__heading-0");

    // Локатор выпадающего текста 1-ой строки
    private final By firstPanel = By.id("accordion__panel-0");

    // Локатор 2-ой строки
    private final By secondHeading = By.id("accordion__heading-1");

    // Локатор выпадающего текста 2-ой строки
    private final By secondPanel = By.id("accordion__panel-1");

    // Локатор 3-ей строки
    private final By thirdHeading = By.id("accordion__heading-2");

    // Локатор выпадающего текста 3-ей строки
    private final By thirdPanel = By.id("accordion__panel-2");

    // Локатор 4-ой строки
    private final By fourthHeading = By.id("accordion__heading-3");

    // Локатор выпадающего текста 4-ой строки
    private final By fourthPanel = By.id("accordion__panel-3");

    // Локатор 5-ой строки
    private final By fifthHeading = By.id("accordion__heading-4");

    // Локатор выпадающего текста 5-ой строки
    private final By fifthPanel = By.id("accordion__panel-4");

    // Локатор 6-ой строки
    private final By sixthHeading = By.id("accordion__heading-5");

    // Локатор выпадающего текста 6-ой строки
    private final By sixthPanel = By.id("accordion__panel-5");

    // Локатор 7-ой строки
    private final By seventhHeading = By.id("accordion__heading-6");

    // Локатор выпадающего текста 7-ой строки
    private final By seventhPanel = By.id("accordion__panel-6");

    // Локатор 8-ой строки
    private final By eighthHeading = By.id("accordion__heading-7");

    // Локатор выпадающего текста 8-ой строки
    private final By eighthPanel = By.id("accordion__panel-7");

    // Кнопка Заказать в шапке страницы
    private final By orderButtonTop = By.className("Button_Button__ra12g");

    // Кнопка Заказать в середине страницы
    private final By orderButtonBottom = By.className("Button_Middle__1CSJM");


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
        driver.findElement(By.id("rcc-confirm-button")).click();
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

    // Проверяет  текст выпадающих панелей в разделе FAQ
    public void checkPanels() {
        checkPanelText(firstHeading, firstPanel, "Сутки — 400 рублей. Оплата курьеру — наличными или картой.");
        checkPanelText(secondHeading, secondPanel, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим." );
        checkPanelText(thirdHeading, thirdPanel,"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.");
        checkPanelText(fourthHeading, fourthPanel,"Только начиная с завтрашнего дня. Но скоро станем расторопнее.");
        checkPanelText(fifthHeading, fifthPanel,"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.");
        checkPanelText(sixthHeading, sixthPanel,"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.");
        checkPanelText(seventhHeading, seventhPanel,"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.");
        checkPanelText(eighthHeading, eighthPanel,"Да, обязательно. Всем самокатов! И Москве, и Московской области.");
    }

    // Проверяет текст в выпадающей панели
    public void checkPanelText(By header, By panel, String expectedText) {
        driver.findElement(header).click();
        driverWaiter.until(ExpectedConditions.visibilityOfElementLocated(panel));
        String panelText = textOfPanel(panel);
        MatcherAssert.assertThat(expectedText, is(panelText));
    }
}
