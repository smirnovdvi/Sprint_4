package praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.pages.MainPage;

@RunWith(Parameterized.class)
public class OrderPlacementTest {


    @Rule
    public DriverRule driverRule = new DriverRule();
    private final String orderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phoneNumber;



    public OrderPlacementTest(String orderButton, String name, String surname, String address, String phoneNumber, String metro) {

        this.orderButton = orderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro=metro;
        this.phoneNumber = phoneNumber;



    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {MainPage.topOrderButton, "Иван", "Иванов", "Москва Кремль", "Красносельская", "81234567890" },
                {MainPage.bottomOrderButton, "Петр", "Петров", "Москва ул.Королева д.1", "Лубянка", "80987456321" },
        };
    }

    @Test
    public void OrderPlacement() {

        var mainPage = new MainPage(driverRule.getDriver());
        System.out.println(orderButton + name + surname + address + metro + phoneNumber);
        mainPage.openMainPage();
        mainPage.acceptCookies();
        mainPage.clickOrderButton(orderButton);
        mainPage.fillOrderForm(name, surname, address, metro, phoneNumber);


    }
}
