package com.bsl.pages;

import static com.codeborne.selenide.Selenide.*;


public class LoginPage {

    public void login(String user, String password) {

        $("#user").setValue(user);
        $("#password").setValue(password);
        $("#formid\\:loginBtn").click();

    }

}