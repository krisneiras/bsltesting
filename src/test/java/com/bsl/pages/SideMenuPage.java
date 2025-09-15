package com.bsl.pages;

import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SideMenuPage {

    /**
     * Navega a un módulo y submódulo, abriendo el sidebar si hace falta.
     *
     * @param mainMenuId ID del módulo principal.
     * @param subMenuId  ID del submódulo. Puede ser null o vacío si no hay submódulo.
     */
    public void navigateToModule(String mainMenuId, String subMenuId) {
        // Abrir sidebar si está cerrado
        if (!$("#sidebarMenu").isDisplayed() || $("#sidebarMenu").getAttribute("style").contains("width: 0px")) {
            $("button.btn.btn-secondary.ml-2.mr-3")
                    .shouldBe(visible)
                    .click();

            $("#sidebarMenu")
                    .shouldBe(visible)
                    .shouldNotHave(attributeMatching("style", ".*width: 0px.*"));
        }

        // Navegar al módulo principal por ID
        $("#" + mainMenuId)
                .shouldBe(visible, Duration.ofSeconds(10))
                .shouldBe(enabled)
                .scrollTo()
                .click();

        // Navegar al submódulo por ID si se pasó
        if (subMenuId != null && !subMenuId.trim().isEmpty()) {
            $("#" + subMenuId)
                    .shouldBe(visible, Duration.ofSeconds(5))
                    .click();
        }
    }
}
