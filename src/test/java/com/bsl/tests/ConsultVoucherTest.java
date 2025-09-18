package com.bsl.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ConsultVoucherTest extends BaseClassTest {

    @Test
    void voucherConsult () {

        sideMenuPage.navigateToModule("menu-item_5", "");
        $(byText("Search Vouchers")).shouldBe(visible).click();

        $(".form-control.daterange").setValue(dotenv.get("TEST_DATE"));

        $$("#dropdownMenuButton").get(1).click();
        $("input[value='7815001']").click();

        $$("#dropdownMenuButton").get(2).click();
        $("input[value='NEW']").click();

        $$("#dropdownMenuButton").get(3).click();
        $("input[value='ACCOUNT']").click();

        $$("#dropdownMenuButton").get(4).click();
        $("input[value='Layaway']").click();
        $("body").click();

        $("input[value='Search']").click();
        $("#frmTable\\:exchangerate-table\\:landlord-table\\:0\\:j_idt117").click();

        //Verificación
        $("form#frmProcessData").shouldHave(text("9980187003791003240907052"));
        $("tbody#vouchers-details-table tr").shouldHave(text("1973"));


    }

}
