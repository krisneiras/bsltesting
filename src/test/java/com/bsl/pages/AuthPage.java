package com.bsl.pages;

import io.github.cdimascio.dotenv.Dotenv;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class AuthPage {

    private final Dotenv dotenv = Dotenv.configure().load();

    public void loginAndServerSelection () {

        open(dotenv.get("LOGIN_URL"));

        //Login
        $("#user").setValue(dotenv.get("BSL_USER"));
        $("#password").setValue(dotenv.get("BSL_PWD"));
        $("#formid\\:loginBtn").click();
        $("#login-error").shouldNot(exist);

        //Server selection
        $("#select-server").$(".dropdown-toggle").click();
        //Searching dropdown option by its text
        $("#server-dropdown").$(byText(dotenv.get("SERVER_TPADMIN"))).click();
        $("#select-server\\:loginBtn").click();
        webdriver().shouldHave(urlContaining(dotenv.get("HOME_URL")));

    }

}
