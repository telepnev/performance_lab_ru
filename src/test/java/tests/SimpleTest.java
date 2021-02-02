package tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.*;
import models.ShodowUser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest extends TestBase {
    ShodowUser user = new ShodowUser();

    @Test
    @DisplayName("Проверить, что главная страница открывается")
    @Feature("Regression")
    @Story("Открытие главной страницы")
    @Owner("Telepnev")
    @Severity(SeverityLevel.CRITICAL)
    public void openMainPageTest() {
        step("Открываем главную страницу", ()
                -> open("https://www.performance-lab.ru/"));
        step("Проверяем что нужный нам сайт открылся", () -> {
            $x("//a[@title='Перфоманс Лаб']").shouldBe(Condition.visible);
            $("h1").shouldHave(text("Выберите продукт, чтобы начать тестирование"));
        });

    }

    @Test
    @DisplayName("Проверка формы заказа")
    @Feature("Regression")
    @Story("Пользователь заполняет форму обратной связи")
    @Owner("Telepnev")
    public void orderServiceTest() {
        step("Открываем главную страницу", () ->
                open("https://www.performance-lab.ru/"));
        step("Переходим 'Заказать услугу'", () ->
                $(".usr-req-button").click());
        switchTo().frame($("#hs-form-iframe-0"));
        step("Проверяем что нужная нам форма открылась", () ->
        {
            $("h1").shouldHave(text("Свяжитесь с нами"));
        });
        step("Заполняем форму, поле 'Имя'", () -> {
            $x("//input[@name='firstname']").val(user.getFirstName());
        });
        step("Заполняем форму, поле 'Фамилия'", () -> {
            $x("//input[@name='lastname']").val(user.getLastName());
        });
        step("Заполняем форму, поле 'Email'", () -> {
            $x("//input[@name='email']").val(user.getUserEmail());
        });
        step("Заполняем форму, поле 'Телефон'", () -> {
            $x("//input[@name='phone']").val(user.getUserPhone());
        });
        step("Заполняем форму, поле 'Компания'", () -> {
            $x("//input[@name='company']").val(user.getCompany());
        });
        step("Заполняем форму, поле 'Сообщение'", () -> {
            $x("//div[@class='input']/textarea").val(user.getMessage());
        });
        step("Нажимаем 'Отправить'", () -> {
            $x("//div[@class='actions']/input").click();
        });
        step("Заполняем форму, поле 'Сообщение'", () -> {
            $("body").shouldHave(text("Нажимая отправить, Вы соглашаетесь принять условия"));
        });

        step("Capcha", () -> {
        });
    }

    @Test
    @DisplayName("Проверка трех главных форм заказа")
    @Feature("Regression")
    @Story("Пользователь должен иметь возможность перехода для заказа")
    @Owner("Telepnev")
    public void checkingBasicServicesTest() {
        step("Открываем главную страницу", ()
                -> open("https://www.performance-lab.ru/"));
        step("Проверяем на главной странице наличие трех главных сервисов", () ->
                $$x("//ul[@class='ee-icon-list']//li").shouldHaveSize(3));

        step("Проверяем наличие кнопки 'Сайт'", () ->
                $("#site_btn").shouldBe(visible));
        step("Проверяем наличие кнопки 'Мобильное приложение'", () ->
                $("#mobile_btn").shouldBe(visible));
        step("Проверяем наличие кнопки 'ИТ-Система'", () ->
                $("#enterprise_btn").shouldBe(visible));
    }

    @Test
    @DisplayName("Проверка перехода 'Сайт'")
    @Feature("Regression")
    @Story("Пользователь должен иметь возможность перехода для заказа")
    @Owner("Telepnev")
    public void checkingTheLinkSiteTest() {
        step("Открываем главную страницу", ()
                -> open("https://www.performance-lab.ru/"));
        step("Кликаем на 'Сайт button'", () -> $("#site_btn").click());
        step("Проверяем корректность перехода", () -> {
            String urlTitle = switchTo().window(1).getCurrentUrl();
            assertEquals("https://qa.city/", urlTitle);
        });
    }

    @Test
    @DisplayName("Проверка перехода 'Мобильное приложение'")
    @Feature("Regression")
    @Story("Пользователь должен иметь возможность перехода для заказа")
    @Owner("Telepnev")
    public void checkingTheLinkMobileTest() {
        step("Открываем главную страницу", ()
                -> open("https://www.performance-lab.ru/"));
        step("Кликаем на 'Мобильное приложение button'", () -> $("#mobile_btn").click());
        step("Проверка корректности перехода по ссылке", () -> {
            switchTo().frame($("#hs-form-iframe-0"));
            $("h1").shouldHave(text("Свяжитесь с нами"));
        });

    }

    @Test
    @DisplayName("Проверка перехода 'ИТ-Система'")
    @Feature("Regression")
    @Story("Пользователь должен иметь возможность перехода для заказа")
    @Owner("Telepnev")
    public void checkingTheLinkItSystemTest() {
        step("Открываем главную страницу", ()
                -> open("https://www.performance-lab.ru/"));
        step("Кликаем на 'ИТ-Система button'", () -> $("#mobile_btn").click());
        step("Проверка корректности перехода по ссылке", () -> {
            switchTo().frame($("#hs-form-iframe-0"));
            $("h1").shouldHave(text("Свяжитесь с нами"));
        });
    }

    @Test
    @Disabled("Ждем доработки 4 функции")
    @DisplayName("Проверка четырех главных форм заказа")
    @Feature("Regression")
    @Story("Пользователь должен иметь возможность перехода для заказа")
    @Owner("Telepnev")
    public void checkingServicesTest() {
        step("Открываем главную страницу", ()
                -> open("https://www.performance-lab.ru/"));
        step("Проверяем на главной странице наличие трех главных сервисов", () ->
                $$x("//ul[@class='ee-icon-list']//li").shouldHaveSize(3));

        step("Проверяем наличие кнопки 'Сайт'", () ->
                $("#site_btn").shouldBe(visible));
        step("Проверяем наличие кнопки 'Мобильное приложение'", () ->
                $("#mobile_btn").shouldBe(visible));
        step("Проверяем наличие кнопки 'ИТ-Система'", () ->
                $("#enterprise_btn").shouldBe(visible));
    }

    @Test
    @DisplayName("Проверка формы заказа")
    @Feature("Regression")
    @Story("Проверка корректности отработки формы заказа при неправильном заполнении")
    @Owner("Telepnev")
    public void negativeOrderServiceTest() {
        step("Открываем главную страницу", () ->
                open("https://www.performance-lab.ru/"));
        step("Переходим 'Заказать услугу'", () ->
                $(".usr-req-button").click());
        switchTo().frame($("#hs-form-iframe-0"));
        step("Проверяем что нужная нам форма открылась", () ->
        {
            $("h1").shouldHave(text("Свяжитесь с нами"));
        });
        step("Заполняем форму, поле 'Имя'", () -> {
            $x("//input[@name='firstname']").val(user.getFirstName());
        });
        step("Заполняем форму, поле 'Фамилия'", () -> {
            $x("//input[@name='lastname']").val(user.getLastName());
        });
        step("Заполняем форму, поле 'Email'", () -> {
            $x("//input[@name='email']").val(user.getUserEmail());
        });
        step("Заполняем форму, поле 'Телефон'", () -> {
            $x("//input[@name='phone']").val("");
        });
        step("Заполняем форму, поле 'Компания'", () -> {
            $x("//input[@name='company']").val(user.getCompany());
        });
        step("Заполняем форму, поле 'Сообщение'", () -> {
            $x("//div[@class='input']/textarea").val(user.getMessage());
        });
        step("Нажимаем 'Отправить'", () -> {
            $x("//div[@class='actions']/input").click();
        });
        step("Заполняем форму, поле 'Сообщение'", () -> {
            $("body").shouldHave(text("Нажимая отправить, Вы соглашаетесь принять условия"));
        });

        step("Capcha", () -> {
        });
    }


}
