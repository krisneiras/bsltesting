package com.bsl.pages;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class ServerSelection {

    public void cssSelection(String server) {

        $("#select-server").$(".dropdown-toggle").click();
        //Searching dropdown option by its text
        $("#server-dropdown").$(byText(server)).click();
        //Search login button by text
        $(byText("Login")).click();

    }

}
