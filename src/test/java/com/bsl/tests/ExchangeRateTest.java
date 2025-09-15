package com.bsl.tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ExchangeRateTest extends BaseClassTest {


    @Test
    public void updateExchangeRate() {

        // ==================
        // Datos para el test
        // ==================
        String store = "Reference";
        String currency = "British Pound";
        String expectedRate = "1.15";

        // ===============================
        // Navegación al módulo Exchange Rate
        // ===============================
        sideMenuPage.navigateToModule("menu-item_6","menu-item_32");
        $(byText("Exchange Rate")).click();

        // ===============================
        // Aplicar filtros y abrir detalles
        // ===============================
        $$("#dropdownMenuButton").get(1).click();
        $("#store-er-input").setValue(store);
        $("#filtersForm\\:store-select\\:0").click();
        $("body").click();

        $$("#dropdownMenuButton").get(2).click();
        $("#currency-er-input").setValue(currency);
        $("#filtersForm\\:currency-select\\:11").click();
        $("body").click();

        $(".btn.btn-outline-primary.mr-2").click();
        $("input[value='Details']").click();

        // ===============================
        // Actualizar el tipo de cambio
        // ===============================
        $("#filtersForm\\:basic-text8").setValue(expectedRate);
        $("input[value='Update']").click();
        $("input[value='Close X']").click();

        // ===============================
        // Verificación del valor actualizado
        // ===============================
        ElementsCollection exchangeRate = $$x("//tbody[@id='exchangerate-table']//tr/td[5]");
        for (SelenideElement cell : exchangeRate) {
            cell.shouldHave(Condition.exactText(expectedRate));
        }

    }

}
