package com.bsl.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BslUsersTest extends BaseClassTest {

    private final String username = "testuserrr@dufry.com";
    private final String css = "BARAJAS T1";

    public void succesfullyAddNewUser() {


        enterBslUsers();
        $(byText("Add new User")).click();
        $("#add-name").setValue(username);
        $("#new-backStore-user-form\\:add-user-profile").click();
        $("option[value='22']").click();
        $("#new-backStore-user-form\\:j_idt91").click();
        $("button[type='buttonPopUpMessage']").click();

    }


    public void deleteExistingUser() {

        $$("#employees-table tr")
                .findBy(Condition.text(username))
                .$("input[value='Delete']")  // o el botón que corresponda
                .click();

    }


    @Test
    public void assignStoresIndividually() {

        enterBslUsers();

        String username = "testuserrr@dufry.com";
        $$("#employees-table tr")
                .findBy(Condition.text(username))
                .$(".bi.bi-pencil-square")  // o el botón que corresponda
                .click();

        $("#userForm\\:user-profile").click();
        $("option[value='QATeam']").click();
        $("#general-search-input-bsuser").setValue("Boston");
        $("#userForm\\:j_idt64\\:2\\:j_idt68").click();
        $$("input[type='checkbox']").get(1).click();
        $("input[value='Save']").shouldBe(Condition.visible).click();
        $("#userForm\\:j_idt73").shouldBe(Condition.visible).click();
        $("button[type='buttonPopUpMessage']").click();

    }

    @Test
    public void assignStoresInBuk() {

        enterBslUsers();
        editTestUser();

        $$("#tableUserCSS tr")
                .findBy(Condition.text(css))
                .$("#userForm\\:j_idt64\\:1\\:AddAll")
                .click();

    }

    @Test
    public void assignCssInBulk() {

        enterBslUsers();
        editTestUser();

        $(".btn.bt-primary.dropdown-toggle ").click();
        $("input[value='Add all CSS/Stores']").shouldBe(Condition.visible).click();
        $("input[value='Confirm']").shouldBe(Condition.visible).click();

    }


    private void enterBslUsers(){

        sideMenuPage.navigateToModule("menu-item_7","");
        $(byText("BSLite Users")).click();

    }

    private void editTestUser() {

        $$("#employees-table tr")
                .findBy(Condition.text(username))
                .$(".bi.bi-pencil-square")
                .click();

    }

}
