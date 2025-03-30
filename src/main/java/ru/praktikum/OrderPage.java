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
    private final By fieldName = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/input");

    //Локатор поля Фамилия
    private final By fieldSurname = By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/input");

    //Локатор поля Адрес
    private final By fieldAddress = By.xpath("/html/body/div/div/div[2]/div[2]/div[3]/input");

    //Локатор поля Станция метро
    private final By fieldMetroStation = By.xpath("/html/body/div/div/div[2]/div[2]/div[4]/div/div/input");

    //Локатор поля Телефон
    private final By fieldPhone = By.xpath("/html/body/div/div/div[2]/div[2]/div[5]/input");

    //Локатор кнопки Далее
    private final By buttonFurther = By.xpath("/html/body/div/div/div[2]/div[3]/button");

    //Локатор поля Когда привезти самокат
    private final By fieldWhenBringTheScooter = By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/div/div/input");

    //Локатор поля Срок аренды
    private final By fieldRentalPeriod = By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/div/div[1]");

    //Локатор срока Четверо суток в выпадающем списке Срока аренды
    private final By fourDays = By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/div[2]/div[4]");

    //Локатор для выбора цвета самоката Черный Жемчуг
    private final By colorScooterBlack = By.cssSelector("#black");

    //Локатор поля Комментарий для курьера
    private final By fieldComment = By.xpath("/html/body/div/div/div[2]/div[2]/div[4]/input");

    //Локатор кнопки Далее
    private final By buttonFurther2 = By.xpath("/html/body/div/div/div[2]/div[3]/button[2]");

    //Локатор текста всплывающего Окна подтверждения
    private final By textOfConfirmDialog = By.xpath("/html/body/div/div/div[2]/div[5]/div[1]");

    //Локатор кнопки Да в диалоговом окне Хотите принять заказ?
    private final By buttonYesConfirmDialog = By.xpath("/html/body/div/div/div[2]/div[5]/div[2]/button[2]");

    //Локатор диалогового окна Заказ оформлен
    private final By textOfSuccesfullOrder = By.xpath("/html/body/div/div/div[2]/div[5]/div[1]");


    //Ввод данных на странице Для кого самокат
    public void fillPageWhoIsOrder(String name, String surname, String address, String metroStation, String phone) {
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldSurname).sendKeys(surname);
        driver.findElement(fieldAddress).sendKeys(address);
        driver.findElement(fieldMetroStation).click();
        driver.findElement(fieldMetroStation).sendKeys(metroStation, Keys.ARROW_DOWN, Keys.ENTER);
        driver.findElement(fieldPhone).sendKeys(phone);

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

    //Ввод данных на странице Про аренду
    public void fillPageAboutRent(String date, String comment) {
        driver.findElement(fieldWhenBringTheScooter).click();
        driver.findElement(fieldWhenBringTheScooter).sendKeys(date, Keys.ENTER);
        driver.findElement(fieldRentalPeriod).click();
        driver.findElement(fourDays).click();
        driver.findElement(colorScooterBlack).click();
        driver.findElement(fieldComment).sendKeys(comment);

    }

    public void checkConfirmDialog() {
       String text = driver.findElement(textOfConfirmDialog).getText();
       MatcherAssert.assertThat("Хотите оформить заказ?\n ", is(text));
    }

    public void clickButtonYesConfirmDialog()
    {
        driver.findElement(buttonYesConfirmDialog).click();
        driverWaiter.until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public void checkSuccessfullOrderDialog() {
        String text = driver.findElement(textOfSuccesfullOrder).getText();
        MatcherAssert.assertThat(text, startsWith("Заказ оформлен\n"));
    }
}
