package com.bsl.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;

import static com.codeborne.selenide.Selenide.*;

public class ConsultTransactionTest extends LoginTest {

    @Test
    void basicFilters() {

        performBasicSearch();

        //POS
        $$("input[placeholder='From']").get(0).setValue("2");
        $$("input[placeholder='To']").get(0).setValue("3");

        //TA NUMBER
        $$("input[placeholder='From']").get(1).setValue("5");
        $$("input[placeholder='To']").get(1).setValue("10");

        //TIME
        $$("input[placeholder='From']").get(2).setValue("09:29:12");
        $$("input[placeholder='To']").get(2).setValue("10:08:00");

        //SALE NUMBER
        $("input[placeholder='Sale Number']").setValue("AWCA-02-0000");
        sleep(2000);

        $(".btn.btn-outline-primary.mr-2").click();
    }

    @Test
    void transactionTypes(){
        performBasicSearch();

        $("#filtersForm\\:j_idt56").click();
        $$(".bi.bi-arrow-down-short").get(1).click();
        $("#filtersForm\\:transactiontype-select\\:0").click();
        $("body").click();
        $(".btn.btn-outline-primary.mr-2").click();
        $("#transactions-table").$$("td").findBy(text("SIGNON")).shouldBe(visible);
    }

    @Test
    void articleNumber() {
        performBasicSearch();

        $("#filtersForm\\:j_idt58").click();
        $("body").click();
        $("input[placeholder='Article Number']").setValue("5074355");
        $(".btn.btn-outline-primary.mr-2").click();

        $$("#form-table\\:transactions-table\\:0\\:j_idt213").get(0).click();
        $("span#frmPanel\\:j_idt217\\:out_format").shouldHave(text("5074355"));
    }

    //Turnover - only declarations??
    //Article price -  Only Sales??

    @Test
    void loyaltyProfile(){
        performBasicSearch();

        $("#filtersForm\\:j_idt64").click();
        $("body").click();
        $$(".bi.bi-arrow-down-short").get(1).click();
        $("input[value='EMPL']").click();
        $(".btn.btn-outline-primary.mr-2").click();
        $$("#form-table\\:transactions-table\\:0\\:j_idt213").get(0).click();
        $("span#frmPanel\\:j_idt217\\:out_format").shouldHave(text("Dufry Employee"));

    }

    //Total

    @Test
    void mediaPayment(){
        performBasicSearch();

        $("#filtersForm\\:j_idt68").click();
        $("#filtersForm\\:j_idt82").click();
        $("body").click();
        $$(".bi.bi-arrow-down-short").get(1).click();
        $("input[value='American Express']").click();
        $("body").click();
        $("input[placeholder='Card Number']").setValue("1006");
        $(".btn.btn-outline-primary.mr-2").click();

        $$("#form-table\\:transactions-table\\:0\\:j_idt213").get(0).click();
        $(byText("Details")).click();
        $("#details-container")
                .shouldHave(Condition.and("Contiene ambos datos",text("American Express"), text("1006")));

    }

    //Paid value

    //Corrections
    @Test
    public void filterByCorrections() {

        performBasicSearch();
        $("#filtersForm\\:j_idt72").click();
        $$(".bi.bi-arrow-down-short").get(1).click();
        $("filtersForm\\:corrections-select\\:0").click();
        $("body").click();

    }

    //Operators
    @Test
    public void filterByOperators(){

        performBasicSearch();
        $("#filtersForm\\:j_idt74").click();
        $$(".bi.bi-arrow-down-short").get(1).click();
        $("#filtersForm\\:operatorsign-select\\:3").click();
        $("body").click();
        $(".btn.btn-outline-primary.mr-2").click();

        $("#form-table\\:transactions-table\\:9\\:j_idt213").click();
        $(byText("Details")).click();
        $("#details-container")
                .shouldHave(text("Cashier Supervisor"));

    }

    //Flight
    @Test
    public void flightNumber() {

        performBasicSearch();
        $("#filtersForm\\:j_idt80").click();
        $("body").click();
        $("input[placeholder='Flight Number']").setValue("BA1234");
        $(".btn.btn-outline-primary.mr-2").click();

        $$("#form-table\\:transactions-table\\:0\\:j_idt213").get(0).click();
        $("span#frmPanel\\:j_idt217\\:out_format").shouldHave(text("BA1234"));

    }

    //Item description
    @Test
    public void itemDescription() {

        performBasicSearch();
        $("#filtersForm\\:j_idt84").click();
        $("body").click();
        $("input[placeholder='Item Desc']").setValue("Chanel");
        $(".btn.btn-outline-primary.mr-2").click();

        $$("#form-table\\:transactions-table\\:0\\:j_idt213").get(0).click();
        $("span#frmPanel\\:j_idt217\\:out_format").shouldHave(text("Chanel"));

    }


    private void performBasicSearch(){

        Configuration.holdBrowserOpen = true;

        navigateToModule("menu-item_5", "");
        $("#section_5").$$("ul.list-group > li").get(1).shouldBe(visible).click();
        $(".form-control.daterange").setValue(dotenv.get("TEST_DATE"));
        $(".applyBtn.btn.btn-sm.btn-primary").click();
        $(".btn.btn-outline-primary.mr-2").click();

        //Assertion to verify results are <= 500
        int records = Integer.parseInt($("span.text-secondary").getText().split(" ")[0]);
        if (records > 500) {
            throw new AssertionError("Se muestran resultados mayores a 500: " + records);
        }

        $(byText("Add Filters")).click();

    }

}
