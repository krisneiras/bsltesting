package com.bsl.tests;

import com.bsl.pages.HomePage;
import com.bsl.pages.LoginPage;
import com.bsl.pages.ServerSelection;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class LoginTest {

    // Inicializamos los Page Objects directamente en la declaración para no generar errores de ejecucion
    protected LoginPage loginPage = new LoginPage();
    protected ServerSelection serverSelection = new ServerSelection();
    protected HomePage homePage = new HomePage();
    protected Dotenv dotenv;

    @BeforeEach
    public void loginAndSetup() {

        dotenv = Dotenv.configure().load();

        open(dotenv.get("LOGIN_URL"));
        loginPage.login(dotenv.get("BSL_USER"), dotenv.get("BSL_PWD"));
        $("#login-error").shouldNot(exist);
        serverSelection.cssSelection(dotenv.get("SERVER_TPADMIN"));
        webdriver().shouldHave(urlContaining(dotenv.get("HOME_URL")));
    }

    // ... tu método navigateToModule ...
    protected void navigateToModule(String mainMenuId, String subMenuId) {
        homePage.navigateTo(mainMenuId, subMenuId);
    }
}