package praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.pages.MainPage;
import praktikum.pages.OrderPage;
import praktikum.pages.RentPage;

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

    public OrderPlacementTest(String orderButton, String name, String surname, String address, String metro, String phoneNumber) {

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
                //Данные для запуска через верхнюю кнопку "Заказать"
                {MainPage.topOrderButton, "Иван", "Иванов", "Москва Кремль", "Красносельская", "81234567890" },
                //Данные для запуска через нижнюю кнопку "Заказать"
                {MainPage.bottomOrderButton, "Петр", "Петров", "Москва ул.Королева д.1", "Лубянка", "80987456321" },
        };
    }
    //Тест проверяет сценарий оформления аренды самоката через обе кнопки "Заказать" и различным набором личных данных заказчика
    @Test
    public void OrderPlacement() {

        var mainPage = new MainPage(driverRule.getDriver());
        //Запускаем главную страницу
        mainPage.openMainPage();
        //Подтверждаем и закрываем Cookies
        mainPage.acceptCookies();
        //Ожидаем и нажимаем кнопку "Заказать"
        mainPage.clickOrderButton(orderButton);
        var orderPage = new OrderPage(driverRule.getDriver());
        //Заполняем поля в форме заказа
        orderPage.fillOrderForm(name, surname, address, metro, phoneNumber);
        //Подтверждаем заказ кнопкой "Далее"
        orderPage.clickButtonNext();
        var rentPage = new RentPage(driverRule.getDriver());
        //Заполняем поля в форме аренды
        rentPage.fillRentForm();
        //Подтверждаем заказ
        rentPage.confirmOrder();
        //Проверяем оформление заказа ожидаю модального окна подтверждения
        rentPage.checkOrder();
    }
}
