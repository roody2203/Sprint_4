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
    private final By howMuchIsItAndHowPay = By.id("accordion__heading-0");

    // Локатор выпадающего текста 1-ой строки
    private final By howMuchIsItAndHowPayPanel = By.id("accordion__panel-0");

    // Локатор 2-ой строки
    private final By wantSeveralScootersAtOnce = By.id("accordion__heading-1");

    // Локатор выпадающего текста 2-ой строки
    private final By wantSeveralScootersAtOncePanel = By.id("accordion__panel-1");

    // Локатор 3-ей строки
    private final By howRentalTimeIsCalculated = By.id("accordion__heading-2");

    // Локатор выпадающего текста 3-ей строки
    private final By howRentalTimeIsCalculatedPanel = By.id("accordion__panel-2");

    // Локатор 4-ой строки
    private final By canIOrderAScooterForToday = By.id("accordion__heading-3");

    // Локатор выпадающего текста 4-ой строки
    private final By canIOrderAScooterForTodayPanel = By.id("accordion__panel-3");

    // Локатор 5-ой строки
    private final By isItPossibleToExtendTheOrder = By.id("accordion__heading-4");

    // Локатор выпадающего текста 5-ой строки
    private final By isItPossibleToExtendTheOrderPanel = By.id("accordion__panel-4");

    // Локатор 6-ой строки
    private final By areYouBringingTheCharger = By.id("accordion__heading-5");

    // Локатор выпадающего текста 6-ой строки
    private final By areYouBringingTheChargerPanel = By.id("accordion__panel-5");

    // Локатор 7-ой строки
    private final By isItPossibleToCancelTheOrder = By.id("accordion__heading-6");

    // Локатор выпадающего текста 7-ой строки
    private final By isItPossibleToCancelTheOrderPanel = By.id("accordion__panel-6");

    // Локатор 8-ой строки
    private final By liveAcrossTheMKAD = By.id("accordion__heading-7");

    // Локатор выпадающего текста 8-ой строки
    private final By liveAcrossTheMKADPanel = By.id("accordion__panel-7");

    // Кнопка Заказать в шапке страницы
    private final By orderButtonTop = By.className("Button_Button__ra12g");

    // Кнопка Заказать в середине страницы
    private final By orderButtonBottom = By.className("Button_Middle__1CSJM");

    // Кнопка закрытия куки
    private final By closeCookieButton = By.id("rcc-confirm-button");


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

    // Проверяет  текст выпадающих панелей в разделе FAQ
    public void checkPanels() {
        checkPanelText(howMuchIsItAndHowPay, howMuchIsItAndHowPayPanel, ReferenceData.HOW_MUCH_IS_IT_AND_HOW_PAY);
        checkPanelText(wantSeveralScootersAtOnce, wantSeveralScootersAtOncePanel, ReferenceData.WANT_SEVERAL_SCOOTERS_AT_ONCE);
        checkPanelText(howRentalTimeIsCalculated, howRentalTimeIsCalculatedPanel, ReferenceData.HOW_RENTAL_TIME_IS_CALCULATED);
        checkPanelText(canIOrderAScooterForToday, canIOrderAScooterForTodayPanel, ReferenceData.CAN_I_ORDER_A_SCOOTER_FOR_TODAY);
        checkPanelText(isItPossibleToExtendTheOrder, isItPossibleToExtendTheOrderPanel, ReferenceData.IS_IT_POSSIBLE_TO_EXTEND_THE_ORDER);
        checkPanelText(areYouBringingTheCharger, areYouBringingTheChargerPanel, ReferenceData.ARE_YOU_BRINGING_THE_CHARGER);
        checkPanelText(isItPossibleToCancelTheOrder, isItPossibleToCancelTheOrderPanel, ReferenceData.IS_IT_POSSIBLE_TO_CANCEL_THE_ORDER);
        checkPanelText(liveAcrossTheMKAD, liveAcrossTheMKADPanel, ReferenceData.LIVE_ACROSS_THE_MKAD);
    }

    // Проверяет текст в выпадающей панели
    public void checkPanelText(By header, By panel, String expectedText) {
        driver.findElement(header).click();
        driverWaiter.until(ExpectedConditions.visibilityOfElementLocated(panel));
        String panelText = textOfPanel(panel);
        MatcherAssert.assertThat(expectedText, is(panelText));
    }
}
