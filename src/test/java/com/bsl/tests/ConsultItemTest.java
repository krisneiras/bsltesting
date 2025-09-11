package com.bsl.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class ConsultItemTest extends LoginTest {

    @Test
    void setFilters() {

        Configuration.holdBrowserOpen = true;
        navigateToModule("menu-item_5","");
        $(byText("Search Item")).shouldBe(visible).click();

        $(byText("0 selected")).click();
        $(byText("6101020 - 6101020 PRPA S0097")).click();
        $("#filtersForm\\:skuFilterId").setValue("1000002");
        $("#filtersForm\\:descFilterId").setValue("Glenfiddich");
        $("#filtersForm\\:eanFilterId").setValue("26934426");
        $("#filtersForm\\:BTN-Search").click();


        $("img[src='resources/img/lock.svg']").should(exist);
        $(".clickable#exchangerate-table").click();
        //Verify that the only stores is 6101020 PRPA S0097
        $x("//p[b[text()='Store']]").shouldHave(Condition.exactText("Store [6101020 PRPA S0097]"));
        //Assertions for promotions table filtered by Store
        $$("#itemPromo-table tbody tr.clickable td:first-child")
                .shouldHave(CollectionCondition.allMatch("All rows contain exact text", el ->
                        el.getText().trim().equals("6101020 PRPA S0097")));

    }

}
