package com.bsl.tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;



public class CashReportsTest extends LoginTest {
    @Test

    void confirmTillSettlement(){
        enterSessionReport();

        $$("span.bi.bi-x-circle.icon-red").get(1).click();

        // Busca todos los inputs con el atributo 'class'
        ElementsCollection allInputs = $$("input.JSFInputNumber").filter(Condition.visible);

        SelenideElement inputDeclaBooked = allInputs.get(0);

        //Ejecutamos scripts en JS para primero asegurarnos que el elemento es visible, y luego hacemos clic.
        Selenide.executeJavaScript("arguments[0].focus();", inputDeclaBooked);
        Selenide.executeJavaScript("arguments[0].click();", inputDeclaBooked);
        inputDeclaBooked.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        inputDeclaBooked.sendKeys("20");
        inputDeclaBooked.pressTab();

        //Confirmamos la declaracion
        $("#processButton").click();


        //Manejo de ventana emergente
        // Esperar que el modal aparezca
        $("[id*='confirmModal']").shouldBe(Condition.visible);

        // Click en el botón Confirm usando su atributo, aunque parezca un modal en bootstrap sigue siendo el mismo DOM
        $("[value='Confirm']")
                .shouldBe(Condition.visible, Condition.enabled)
                .click();

        //Validacion
        $("#PopUpModal")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.or("text matches either option",
                        Condition.text("Till Settlement confirmed"),
                        Condition.text("Error!! No exist a terminal in workgoup")
                ));

        $("button[type='buttonPopUpMessage']")
                .shouldBe(Condition.visible, Condition.enabled)
                .click();
        //El test funciona, hay que manejar mejor los backdrops!!

    }

    @Test
    void correctTillSettlement() {
        enterSessionReport();

        //Entramos a las pestaña de confirmados
        $("#pills-historical-tab").click();

        $$("span.bi.bi-x-circle.icon-red").get(1).click();

        // Busca todos los inputs con el atributo 'class'
        ElementsCollection allInputs = $$("input.JSFInputNumber").filter(Condition.visible);

        // Selecciona el segundo input de la colección (índice 1)
        SelenideElement inputDeclaBooked = allInputs.get(0);

        // Luego, usa el código que ya te funcionó para interactuar
        Selenide.executeJavaScript("arguments[0].focus();", inputDeclaBooked);
        Selenide.executeJavaScript("arguments[0].click();", inputDeclaBooked);
        inputDeclaBooked.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        inputDeclaBooked.sendKeys("20");
        inputDeclaBooked.pressTab();

        //Confirmamos la declaracion
        // Find all elements with the ID 'processButton' and select the first one (index 0)
        $("#processButton").click();


        //Manejo de ventana emergente
        // Esperar que el modal aparezca
        $("[id*='confirmModal']").shouldBe(Condition.visible);

        //Ingresamos codigo de operador
        $("#frmCashDetails\\:j_idt97\\:0\\:j_idt101").setValue("1");

        // Click en el botón Confirm usando su atributo, aunque parezca un modal en bootstrap sigue siendo el mismo DOM
        $("[value='Confirm']")
                .shouldBe(Condition.visible, Condition.enabled)
                .click();

    }

    @Test
    void printSessionReports() {

    }

    private void enterSessionReport () {
        Configuration.holdBrowserOpen = true;
        navigateToModule("menu-item_3","");

        $(byText("Session Report")).click();

        webdriver().shouldHave(urlContaining(dotenv.get("SESSION_URL")));

        $("#item-sales-daterange-input-1").setValue(dotenv.get("TEST_DATE"));
        $(".applyBtn.btn.btn-sm.btn-primary").click();
        $(".form-control.btn.btn-primary").click();

        $("h2").shouldHave(text("cash report selection"));
    }
}
