package com.bsl.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ManageProfilesTest extends BaseClassTest {

    private final String profileName = "Automation profile";

    @Test
    public void addProfile(){
        enterManageProfiles();
        $("button[data-target='#modalNewProfile']").click();
        $("#add-profile-name").setValue(profileName);
        $("#add-profile-description").setValue("Test description");
        $("input[value='Create Profile']").click();
        $("button[type='buttonPopUpMessage']").click();
    }

    @Test
    public void assignControls(){
        enterManageProfiles();
        $$("table#profiles-table.table tr")
                .find(Condition.text(profileName))
                .$(".bi.bi-pencil-square")
                .click();

        $(".form-check-input.MNU_OPERATORS").click();
        $("#frmBSProfile\\:j_idt139").click();
        $("button[type='buttonPopUpMessage']").click();

    }

//    @Test
//    public void assignProfile(){
//
//    }

    @Test
    public void deleteProfile(){
        enterManageProfiles();
        $$("table#profiles-table.table tr")
                .find(Condition.text(profileName))
                .$(".bi.bi-trash")
                .click();
        $("#deleteForm\\:j_idt83").click();
        $("button[type='buttonPopUpMessage']").click();
    }


    private void enterManageProfiles(){

        sideMenuPage.navigateToModule("menu-item_7","");
        $(byText("Manage Profiles")).click();
    }

}
