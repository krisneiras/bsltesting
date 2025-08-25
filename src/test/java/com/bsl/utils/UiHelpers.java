package com.bsl.utils;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class UiHelpers {

    // Limpia y escribe en un input
    public static void clearAndType(SelenideElement input, String value) {
        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        input.sendKeys(value);
    }

    // Intenta con un input y retorna si fue éxito
    public static boolean tryInput(SelenideElement input, String value) {
        clearAndType(input, value);

        $("#processButton").click();
        $("[id*='confirmModal']").shouldBe(Condition.visible);
        $("[value='Confirm']").shouldBe(Condition.visible, Condition.enabled).click();

        String message = $("#PopUpModal").shouldBe(Condition.visible).getText();
        $("button[type='buttonPopUpMessage']").click();

        return message.contains("Till settlement confirmed");
    }
}
