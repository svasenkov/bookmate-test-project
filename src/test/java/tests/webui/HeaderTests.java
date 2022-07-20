package tests.webui;


import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.webui.domain.HeaderTab;
import tests.webui.domain.Language;
import tests.webui.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;

@Tag("WebUI")
@Epic("WebUI")
@Feature("Header")
@Owner("vonoelv")
class HeaderTests extends TestBase {

    public MainPage mainPage = new MainPage();

    @BeforeEach
    @Override
    @Step("Open https://bookmate.com")
    public void beforeEach() {
        open("/");
        mainPage.acceptCookiesIfNeeded();
    }

    @Test
    @DisplayName("Overview page is open by click on bookmate logo")
    void checkClickOnLogo() {
        mainPage.clickOnBookmateLogo()
                .checkPageIsOpen("https://bookmate.com/");
    }

    @Test
    @DisplayName("Ability to switch to each header tab")
    void checkHeaderTabsOpening() {
        for (HeaderTab headerTab : HeaderTab.values()) {
            mainPage.openHeaderTab(headerTab)
                    .checkPageIsOpen(headerTab.url);
        }
    }

    @Test
    @DisplayName("Ability to open Manage Subscription page")
    void checkSubscriptionPageOpening() {
        mainPage.openSubscriptionPage()
                .checkPageIsOpen("https://bookmate.com/subscription?dscvr=header");
    }

    @Test
    @DisplayName("Ability to open login dialog")
    void checkLoginDialogOpening() {
        mainPage.openLoginDialog()
                .checkLoginPageIsVisible();
    }

    @Test
    @DisplayName("Ability to switch to each language")
    void checkLanguageSwitching() {
        for (Language language : Language.values()) {
            mainPage.selectLanguage(language.langNative)
                    .checkPageIsOpen(language.url);
        }
    }
}
