package com.bsl.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static com.codeborne.selenide.Condition.visible;

public class LoanReportsTest extends BaseClassTest {
    @Test

    void enterLoans() {
        Configuration.holdBrowserOpen = true;
        sideMenuPage.navigateToModule("menu-item_3","");
        $(byText("Loan reports")).shouldBe(visible).click(); //Los elementos de la lista no tienen un submenuID
        webdriver().shouldHave(urlContaining(dotenv.get("LOAN_URL")));

        $("#item-sales-daterange-input-1").setValue(dotenv.get("TEST_DATE"));
        $(".applyBtn.btn.btn-sm.btn-primary").click();
        $(".form-control.btn.btn-primary").click();

        //Seleccionar el operador a confirmar
        ElementsCollection operatorsList = $$("#current-table tbody tr");
        sleep(2000);
        operatorsList.get(0).click();

        //Seleccionar el reporte
        ElementsCollection inputDebit = $$("input.JSFInputNumber").filter(visible);
        sleep(3000);
        inputDebit.get(0).click();

        //rellenar campo
        inputDebit.get(0).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        inputDebit.get(0).sendKeys("300");

        //Confirmar
        $("#processButton").click();
        $("[id*='confirmModal']").shouldBe(Condition.visible);
        $("[value='Confirm']")
                .shouldBe(Condition.visible, Condition.enabled)
                .click();

        //Validacion
        $("#PopUpModal")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.or("text matches either option",
                        Condition.text("Till settlement confirmed"),
                        Condition.text("Error!! No exist a terminal in workgoup")
                ));

        $("button[type='buttonPopUpMessage']")
                .shouldBe(Condition.visible, Condition.enabled)
                .click();
    }
}

// Usar esto si hay helper"!!

//boolean success = false;
//
//    for (SelenideElement input : inputDebit) {
//        if (tryInput(input, "300")) {
//success = true;
//        break; // no seguimos probando si ya hubo éxito
//        }
//        }
//
//        if (!success) {
//        throw new AssertionError("Ningún input lanzó el mensaje esperado de éxito");
//    }


