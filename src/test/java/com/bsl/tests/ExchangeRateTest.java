package com.bsl.tests;

import com.bsl.pages.AuthPage;
import com.bsl.pages.SideMenuPage;
import com.codeborne.selenide.Browser;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ExchangeRateTest {

//Instanciar el AuthPage y SideMenuPage

    private final AuthPage authPage = new AuthPage();
    SideMenuPage sideMenuPage = new SideMenuPage();

    @Test
    public void testLoginFlow() {

        Configuration.holdBrowserOpen = true;

        authPage.loginAndServerSelection();
        sideMenuPage.navigateToModule("menu-item_6","menu-item_32");
        $(byText("Exchange Rate")).click();

        $$("#dropdownMenuButton").get(1).click();
        $("#filtersForm\\:store-select\\:0").click();
        $("body").click();

        $$("#dropdownMenuButton").get(2).click();
        $("#filtersForm\\:currency-select\\:11").click();
        $("body").click();

        $(".btn.btn-outline-primary.mr-2").click();

    }

}
