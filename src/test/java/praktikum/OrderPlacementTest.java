package praktikum;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;

@RunWith(Parameterized.class)
public class OrderPlacementTest {

    private DriverRule driverRule;
    private WebDriver driver;

    private final String orderButton;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String name;
    private final String metro;

    public OrderPlacementTest(String orderButton, String name, String surname, String address, String phoneNumber, String metro) {
        this.orderButton = orderButton;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.metro = metro;
    }

    @Before
    public void setUp() {
        driverRule = new DriverRule();
        driverRule.before();
        driver = driverRule.getDriver();
    }

    @After
    public void tearDown() {
        driverRule.after();
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {MainPage.topOrderButton, "Иван", "Иванов", "Москва Кремль", "Красносельская", "81234567890"},
                {MainPage.bottomOrderButton, "Петр", "Петров", "Москва ул.Королева д.1", "Лубянка", "80987456321"},
        };
    }

    @Test
    public void OrderPlacement() {
        var mainPage = new MainPage(driver);
        System.out.println(orderButton + name + surname + address + metro + phoneNumber);
        mainPage.openMainPage();
        mainPage.acceptCookies();
        mainPage.clickOrderButton(orderButton);
        mainPage.fillOrderForm(name, surname, address, metro, phoneNumber);
    }
}