package com.bsl.pages;

import static com.codeborne.selenide.Selenide.*;


public class LoginPage {

    public void login(String user, String password) {

        $("#formid\\:user").setValue(user);
        $("#formid\\:password").setValue(password);
        $("#formid\\:loginBtn").click();

    }

}