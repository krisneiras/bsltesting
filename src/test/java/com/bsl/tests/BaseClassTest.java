package com.bsl.tests;

import com.bsl.pages.AuthPage;
import com.bsl.pages.SideMenuPage;
import com.codeborne.selenide.Configuration;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeEach;

public class BaseClassTest {

    protected static final Dotenv dotenv = Dotenv.configure().load();
    protected final AuthPage authPage = new AuthPage();
    protected final SideMenuPage sideMenuPage = new SideMenuPage();

    @BeforeEach
    void setupLogin() {
//        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        authPage.loginAndServerSelection();
    }

}
