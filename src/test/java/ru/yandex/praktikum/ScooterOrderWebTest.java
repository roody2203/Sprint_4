package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.EnvConfig;

import ru.praktikum.HomePage;
import ru.praktikum.OrderPage;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class ScooterOrderWebTest {

    private WebDriver chrome_driver;

    private WebDriver firefox_driver;

    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String dateOfDelivery;
    private final String commentForCourier;

    // Инициализировали поля в конструкторе
    public ScooterOrderWebTest(String name, String surname, String address, String metroStation, String phone, String dateOfDelivery, String commentForCourier) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.dateOfDelivery = dateOfDelivery;
        this.commentForCourier = commentForCourier;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentialsForCheckOrderPage() {
        // Генерируем тестовые данные
        return new Object[][]{
                {"Ирина", "Великая", "Покровская, 68", "Бульвар Рокоссовского", "81234567899", "30.07.2057", "Великая"},
                {"Анна Мария", "Сатинова", "Левитана, 8", "Войковская", "89876543221", "23.19.2065", "Привет! Пожалуйста - не оставляйте рядом у двери..."},
                {"Патрон", "СумадинЛях", "ул Полка д 15", "ВДНХ", "+72536457864", "15.10.2025", "Прошу позвонить по номеру 25464123654"},
                {"Михаил", "Заводчиков", "ул Шпица, дом 75", "Маяковская", "+72153456124", "27.12.2026", "В доме злая собака. Прошу не пугаться"},
        };
    }

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

    // Метод тестирования страницы оформления заказа
    public void checkOrderPage(WebDriver driver, boolean useTopOrderButton) throws InterruptedException {

        driver.get(EnvConfig.BASE_URL);

        // Создаем объект стартовой страницы
        HomePage homePage = new HomePage(driver);

        //Закрываем куки
        homePage.closeCookie();

        // Кликаем по кнопке заказа в шапке страницы
        if (useTopOrderButton) {
            homePage.clickOrderButtonTop();
        } else {
            homePage.clickOrderButtonBottom();
        }

        // Проверяем, что открылась нужная страница после клика по кнопке Заказать в шапке страницы
        MatcherAssert.assertThat(driver.getCurrentUrl(), is(EnvConfig.ORDER_URL));

        // Создаем объект страницы заказа
        OrderPage orderPage = new OrderPage(driver);

        // Заполняем поля диалога Для кого самокат
        orderPage.fillPageWhoIsOrder(name, surname, address, metroStation, phone);
        // Нажимаем на кнопку Далее
        orderPage.clickButtonFurther();

        // Заполняем поля диалога Про аренду
        orderPage.fillPageAboutRent(dateOfDelivery, commentForCourier);
        //Нажимаем на кнопку Далее
        orderPage.clickButtonFurther2();

        // Проверяем диалог Хотите оформить заказ?
        orderPage.checkConfirmDialog();

        //Нажимаем на кнопку Да
        orderPage.clickButtonYesConfirmDialog();

        //Проверяем диалог Заказ оформлен
        orderPage.checkSuccessfullOrderDialog();
    }

    @Test
    // Тест создания заказа по верхней кнопке в браузере Mozilla FireFox
    public void checkOrderPageByTopButtonFirefox () throws InterruptedException {
        checkOrderPage(firefox_driver, true);
    }

    @Test
    // Тест создания заказа по верхней кнопке в браузере Google Chrome
    public void checkOrderPageByTopButtonChrome () throws InterruptedException {
        checkOrderPage(chrome_driver, true);
    }

    @Test
    // Тест создания заказа по нижней кнопке в браузере Mozilla FireFox
    public void checkOrderPageByBottomButtonFirefox () throws InterruptedException {
        checkOrderPage(firefox_driver, false);
    }

    @Test
    // Тест создания заказа по нижней кнопке в браузере Google Chrome
    public void checkOrderPageByBottomButtonChrome () throws InterruptedException {
        checkOrderPage(chrome_driver, false);
    }

    // Закрываем браузер после каждого теста
    @After
    public void teardown() {
        chrome_driver.quit();
        firefox_driver.quit();
    }

}