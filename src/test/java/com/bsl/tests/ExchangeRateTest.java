package com.bsl.tests;

import com.bsl.pages.AuthPage;
import com.bsl.pages.SideMenuPage;
import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ExchangeRateTest {

//Instanciar el AuthPage y SideMenuPage

    private final AuthPage authPage = new AuthPage();
    SideMenuPage sideMenuPage = new SideMenuPage();

    @Test
    public void updateExchangeRate() {

        Configuration.holdBrowserOpen = true;

        authPage.loginAndServerSelection();
        sideMenuPage.navigateToModule("menu-item_6","menu-item_32");
        $(byText("Exchange Rate")).click();

        $$("#dropdownMenuButton").get(1).click();
        $("#store-er-input").setValue("Reference");
        $("#filtersForm\\:store-select\\:0").click();
        $("body").click();

        $$("#dropdownMenuButton").get(2).click();
        $("#currency-er-input").setValue("British Pound");
        $("#filtersForm\\:currency-select\\:11").click();
        $("body").click();

        $(".btn.btn-outline-primary.mr-2").click();
        $("input[value='Details']").click();

        $("#filtersForm\\:basic-text8").setValue("1.15");
        $("input[value='Update']").click();
        $("input[value='Close X']").click();

        ElementsCollection exchangeRate = $$x("//tbody[@id='exchangerate-table']//tr/td[5]");
        for (SelenideElement cell : exchangeRate) {
            cell.shouldHave(Condition.exactText("1.15"));
        }

    }

}
