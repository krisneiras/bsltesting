package com.bsl.tests;

import com.bsl.pages.AuthPage;
import com.bsl.pages.SideMenuPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;

public class BaseClassTest {

    protected final AuthPage authPage = new AuthPage();
    protected final SideMenuPage sideMenuPage = new SideMenuPage();

    @BeforeEach
    void setupLogin() {
        Configuration.holdBrowserOpen = true;
        authPage.loginAndServerSelection();
    }

}
