package com.bsl.pages;

import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.*;


public class HomePage {

    public void openSideBarMenu () {
        // Método para abrir el menu hamburguesa

            // Hacemos clic en el ícono del menú a traves de la clase de la etiqueta i
            $("button.btn.btn-secondary.ml-2.mr-3")
                    .shouldBe(visible)
                    .click();

            // Verificamos que el menú realmente se expandió (no tiene width 0px)
            $("#sidebarMenu")
                    .shouldBe(visible)
                    .shouldHave(attributeMatching("style", "^(?!.*width: 0px).*$"));
    }

    /**
     * Método genérico para navegar a cada modulo y su respectivo subemnu.
     *
     * @param mainMenu El nombre del grupo principal (ej: "Operators")
     * @param subMenu El nombre del submenú. Puede ser null o vacío si no aplica.
     */
        public void navigateTo(String mainMenu, String subMenu) {
            openSideBarMenu();
            // Seleccionar el grupo del menú principal
            $("#" + mainMenu)
                    .shouldBe(visible, Duration.ofSeconds(10))
                    .shouldBe(enabled)
                    .scrollTo()
                    .click();

            // Si hay submenú, hacer clic
            if (subMenu != null && !subMenu.trim().isEmpty()) {
                $("#" + subMenu) // Seleccionamos por ID
                        .shouldBe(visible, Duration.ofSeconds(5))
                        .click();
            }
        }
}
