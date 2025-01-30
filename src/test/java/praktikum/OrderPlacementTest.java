package praktikum;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.FaqPage;
import praktikum.pages.MainPage;

@RunWith(Parameterized.class)
public class OrderPlacementTest {


    @ClassRule
    public static DriverRule driverRule = new DriverRule();
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
        this.metro=metro;

    }
    @BeforeClass
    public static void closeCookies() {
        new MainPage(driverRule.getDriver())
                .openMainPage()
                .acceptCookies();
    }

    @Parameterized.Parameters
    public static Object[][] buttonData() {
        return new Object[][]{
                {MainPage.topOrderButton, "Иван", "Иванов", "Москва Кремль", "Красносельская", "81234567890" },
                {MainPage.bottomOrderButton, "Петр", "Петров", "Москва ул.Королева д.1", "Лубянка", "80987456321" },
        };
    }

    @Test
    public void OrderPlacement() {
        new MainPage(driverRule.getDriver())
                .clickOrderButton(orderButton)
                .fillOrderForm(name);



        //var mainPage = new MainPage(driver);

        //mainPage.openMainPage();

        //mainPage.clickOnStatus();

        //String invalidId = "123";
        //mainPage.typeOrderId(invalidId);

        //var statusPage = mainPage.clickOnGo();

        //statusPage.checkNotFoundMessage();
    }
}
