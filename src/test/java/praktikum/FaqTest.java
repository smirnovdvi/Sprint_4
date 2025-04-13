package praktikum;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.pages.FaqPage;

@RunWith(Parameterized.class)
public class FaqTest {

    private final String itemId;
    private final String answerText ;

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    public FaqTest(String itemId, String answerText) {

        this.itemId = itemId;
        this.answerText = answerText;
    }

    @BeforeClass
    public static void closeCookies() {
        new FaqPage(driverRule.getDriver())
                .open()
                .acceptCookies();
    }

    @Parameterized.Parameters
    public static Object[][] faqData() {
        return new Object[][]{
                {"0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
        };
    }
    //Тест прокликивает "Вопросы о важном" и проверяет на соответствие ответов ожидаемому тексту
    @Test
    public void clickOnFaqItem() {
        //Запускаем главную страницу
        new FaqPage(driverRule.getDriver())
                //Проверяем что ответы на вопросы скрыты
                .checkAnswerIsInvisible(itemId)
                //Кликаем на нужный вопрос
                .clickOnQuestion(itemId)
                //Ожидаем ответ
                .waitForAnswer(itemId)
                //Проверяем ответ на соответствие ожидаемому
                .checkCorrectText(itemId,answerText);
    }
}

