package ru.praktikum;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait driverWaiter;

    public OrderPage(WebDriver driver)
    {
        this.driver = driver;
        this.driverWaiter = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    //Локатор поля Имя
    private final By fieldName = By.cssSelector(".Input_Responsible__1jDKN[placeholder='* Имя']");

    //Локатор поля Фамилия
    private final By fieldSurname = By.cssSelector(".Input_Responsible__1jDKN[placeholder='* Фамилия']");

    //Локатор поля Адрес
    private final By fieldAddress = By.cssSelector(".Input_Responsible__1jDKN[placeholder='* Адрес: куда привезти заказ']");

    //Локатор поля Станция метро
    private final By fieldMetroStation = By.cssSelector(".select-search__input[placeholder='* Станция метро']");

    //Локатор поля Телефон
    private final By fieldPhone = By.cssSelector(".Input_Responsible__1jDKN[placeholder='* Телефон: на него позвонит курьер']");

    //Локатор кнопки Далее
    private final By buttonFurther = By.cssSelector(".Button_Middle__1CSJM");

    //Локатор поля Когда привезти самокат
    private final By fieldWhenBringTheScooter = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Когда привезти самокат']");

    //Локатор поля Срок аренды
    private final By fieldRentalPeriod = By.cssSelector(".Dropdown-placeholder");

    //Локатор срока Четверо суток в выпадающем списке Срока аренды
    private final By fourDays = By.cssSelector(".Dropdown-option:nth-of-type(4)");

    //Локатор для выбора цвета самоката Черный Жемчуг
    private final By colorScooterBlack = By.cssSelector("#black");

    //Локатор поля Комментарий для курьера
    private final By fieldComment = By.cssSelector(".Input_Responsible__1jDKN[placeholder='Комментарий для курьера']");

    //Локатор кнопки Заказать
    private final By buttonFurther2 = By.cssSelector(".Button_Middle__1CSJM:nth-of-type(2)");

    //Локатор текста всплывающего Окна подтверждения
    private final By textOfConfirmDialog = By.cssSelector(".Order_ModalHeader__3FDaJ");

    //Локатор кнопки Да в диалоговом окне Хотите принять заказ?
    private final By buttonYesConfirmDialog = By.cssSelector(" div.Order_Modal__YZ-d3 > div.Order_Buttons__1xGrp > button:nth-child(2)");

    //Локатор диалогового окна Заказ оформлен
    private final By textOfSuccesfullOrder = By.cssSelector(".Order_ModalHeader__3FDaJ");

    // Ввод данных в поле Имя
    public void fillName(String value) {
        driver.findElement(fieldName).sendKeys(value);
    }

    // Ввод данных в поле Фамилия
    public void fillSurname(String value) {
        driver.findElement(fieldSurname).sendKeys(value);
    }

    // Ввод данных в поле Адрес
    public void fillAddress(String value) {
        driver.findElement(fieldAddress).sendKeys(value);
    }

    // Ввод данных в поле Станция метро
    public void fillMetroStation(String value) {
        driver.findElement(fieldMetroStation).click();
        driver.findElement(fieldMetroStation).sendKeys(value, Keys.ARROW_DOWN, Keys.ENTER);
    }

    // Ввод данных в поле Телефон
    public void fillPhone(String value) {
        driver.findElement(fieldPhone).sendKeys(value);
    }

    //Ввод данных на странице Для кого самокат
    public void fillPageWhoIsOrder(String name, String surname, String address, String metroStation, String phone) {
        fillName(name);
        fillSurname(surname);
        fillAddress(address);
        fillMetroStation(metroStation);
        fillPhone(phone);
    }

    public void clickButtonFurther() {
        driver.findElement(buttonFurther).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public void clickButtonFurther2() {
        driver.findElement(buttonFurther2).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    // Ввод данных в поле Дата доставки
    public void fillDate(String value) {
        driver.findElement(fieldWhenBringTheScooter).click();
        driver.findElement(fieldWhenBringTheScooter).sendKeys(value, Keys.ENTER);
    }

    // Выбор Срока аренды
    public void fillRentalPeriod() {
        driver.findElement(fieldRentalPeriod).click();
        driver.findElement(fourDays).click();
    }

    // Выбор Черного цвета самоката
    public void fillColor() {
        driver.findElement(colorScooterBlack).click();
    }

    // Ввести данные в поле Комментарий
    public void fillComment(String value) {
        driver.findElement(fieldComment).sendKeys(value);
    }

    //Ввод данных на странице Про аренду
    public void fillPageAboutRent(String date, String comment) {
        fillDate(date);
        fillRentalPeriod();
        fillColor();
        fillComment(comment);
    }

    public void checkConfirmDialog() {
       String text = driver.findElement(textOfConfirmDialog).getText();
       MatcherAssert.assertThat(ReferenceData.CONFIRM_DIALOG_TEXT, is(text));
    }

    public void clickButtonYesConfirmDialog()
    {
        driver.findElement(buttonYesConfirmDialog).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public void checkSuccessfullOrderDialog() {
        String text = driver.findElement(textOfSuccesfullOrder).getText();
        MatcherAssert.assertThat(text, startsWith(ReferenceData.SUCCESS_ORDER_DIALOG_TEXT));
    }
}
