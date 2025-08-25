package com.bsl.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class OperatorManagementTests extends LoginTest {

    @BeforeEach
    void navigateToOperatorsMenu(){
        Configuration.holdBrowserOpen = true;
        navigateToModule("menu-item_2", "section_2.submenu"); //No tienen id los submenuuu
        webdriver().shouldHave(urlContaining(dotenv.get("OPERATORS_URL")));
    }

    @Test
    void enterOperatorsFunctionality() {
        //Se ejecuta el BeforeEach
    }

    @Test
    void createOperator() {
        Configuration.holdBrowserOpen = true;

        navigateToModule("menu-item_2", "section_2.submenu"); //No tienen id los submenuuu
        webdriver().shouldHave(urlContaining(dotenv.get("OPERATORS_URL")));

        $(byText("Add new operator")).click();
        $("#add-employee-id").shouldBe(visible).setValue("1514");
        $("#add-name").setValue("Test");
        $("#add-surname").setValue("Surname");
        $("#add-duration-days").setValue("30");
        $("#new-user-form\\:j_idt97").click();
        $("#txPopUpMessage").shouldHave(text("The user was added correctly"));
        // Encuentra el elemento del botón
        SelenideElement acceptButton = $(byText("Accept"));
        sleep(2000);
        // Ejecuta un clic de JavaScript directamente sobre el elemento
        executeJavaScript("arguments[0].click()", acceptButton);

        // Aserción para verificar que el pop-up se ha cerrado
        $("#PopUpModal").shouldNotBe(visible);
    }

    @Test
    void editPersonalData (){

        $$(".bi.bi-pencil-square").get(1).click();
        $("#frmEmployeeData\\:employee-name").setValue("UITEST");
        $("#frmEmployeeData\\:updateCashier").click();
        confirmUpdates();

    }

    @Test

    void editOperatorsPermissions () {

        $$(".bi.bi-pencil-square").get(1).click();
        $("#nav-operator-data-tab").click();

        SelenideElement checkbox = $("#store-profiles-table")
                .$$("tr")           // todas las filas
                .get(2)             // índice 2 = 3ra fila
                .$("input[type='checkbox']");  // el checkbox de esa fila

        if (!checkbox.isSelected()) {
            checkbox.click(); // marcar si no está marcado
        } else {
            checkbox.click(); // opcional: desmarcar si ya estaba marcado
        }

        //Seleccionando el dropdown de Profile ID
        SelenideElement profileDropdown = $("#store-profiles-table").$$("select").get(1);

        // Seleccionar por texto visible
        profileDropdown.selectOption("Supervisor (1)");

        profileDropdown.click();

        //Confirm update
        $("#frmEmployeeData\\:updateCashier").click();
        confirmUpdates();
    }

    @Test
    void defaultPassword() {

        $$(".bi.bi-pencil-square").get(1).click();
        $(byText("Default password")).click();
        confirmUpdates();

    }

    @Test
    void closePos() {
        $$(".bi.bi-pencil-square").get(1).click();
        $("#nav-operator-data-tab").click();
        $(byText("Close POS")).click();
        sleep(2000);
        $(byText("Accept")).click();
    }

    @Test
    void generateBadge () {

        $$(".bi.bi-pencil-square").get(1).click();
        $("#nav-operator-data-tab").click();

        SelenideElement badgeDropdown =  $(byText("Badge")).shouldBe(visible);
        badgeDropdown.click();

        $("input[value='Print badge']")
                .click();

        // Esperar a que haya más de una pestaña abierta
        Selenide.Wait().until(webDriver -> WebDriverRunner.getWebDriver().getWindowHandles().size() > 1);

        // Obtener handles
        Set<String> handles = WebDriverRunner.getWebDriver().getWindowHandles();
        System.out.println("Pestañas abiertas: " + handles.size());
        //Será correcto si el numero de pestanas abiertas es 2

        //Cambiar a la nueva pestaña y verificar el contenido
        String mainWindow = WebDriverRunner.getWebDriver().getWindowHandle();

        // Cambiar a la nueva ventana
        for (String handle : WebDriverRunner.getWebDriver().getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                WebDriverRunner.getWebDriver().switchTo().window(handle);
                break;
            }
        }

        // Verificar que la URL contiene qrcode
        Assertions.assertTrue(WebDriverRunner.url().contains("qrcode.xhtml"));

        // Cerrar la nueva ventana y volver a la principal
        // WebDriverRunner.getWebDriver().close();
        // WebDriverRunner.getWebDriver().switchTo().window(mainWindow);

    }

    //Private porque solo lo vamos a utilizar dentro de la clase OperatorManagementTest
    private void confirmUpdates () {

        sleep(2000);
        $(byText("Ok")).shouldBe(visible).click();
        // Verifica que el elemento exista en el DOM, sin importar si es visible o no.
        $(byText("Ok")).should(exist).click();
        sleep(2000);
        $(byText("Accept")).click();

    }

}