package com.bsl.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class BslUsersTest extends BaseClassTest {

    @Test
    public void succesfullyAddNewUser() {

        String username = "testuser@dufry.m";

        enterBslUsers();
        $(byText("Add new User")).click();
        $("#add-name").setValue(username);
        $("#new-backStore-user-form\\:add-user-profile").click();
        $("option[value='22']").click();
        $("#new-backStore-user-form\\:j_idt91").click();
        $("button.btn.ml-2").click();
    }

    @Test
    public void deleteExistingUser() {

        enterBslUsers();

    }

    @Test
    public void assignStoresIndividually() {

        enterBslUsers();

    }

    @Test
    public void assignStoresInBulk() {

        enterBslUsers();

    }

    private void enterBslUsers(){

        sideMenuPage.navigateToModule("menu-item_7","");
        $(byText("BSLite Users")).click();

    }

}
