package com.bsl.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ManageTerminalsTest extends BaseClassTest{

    private final String terminal = "PRJOXPOS007K";

    @Test
    public void addNewTerminal(){

        enterManageTerminals();
        $(byText("Add new Terminal")).click();
        $("#add-name").setValue(terminal);
        $("#add-description").setValue("Test description");
        $("#new-terminal-form\\:j_idt100").click();
        $("button[type='buttonPopUpMessage']").click();
        $("button.close").click();

    }

    @Test
    public void editTerminal(){
        enterManageTerminals();
        $$("#terminal-table tr")
                .findBy(Condition.text(terminal))
                .$(".bi.bi-pencil-square")
                .click();
        $("input[name='frmComputer\\:j_idt108']").shouldBe(Condition.visible).setValue("Test description updated");
        $("#frmComputer\\:update-terminal-btn").click();
        $(".btn.btn-primary.bootbox-accept").click();
        sleep(2000);
        $("button[type='buttonPopUpMessage']").click();
        sleep(2000);
        $(".bi.bi-x").click();

    }

    @Test
    public void deleteTerminal(){
        enterManageTerminals();
        $$("#terminal-table tr")
                .findBy(Condition.text(terminal))
                .$(".btn.btn-danger")
                .click();
        $("#deleteForm\\:j_idt86").click();

        $("#txPopUpMessage").shouldHave(Condition.text("The computer was updated propertly"));
    }

    private void enterManageTerminals(){

        sideMenuPage.navigateToModule("menu-item_7","");
        $(byText("Manage Terminals")).click();

    }


}
