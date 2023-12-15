package ru.netology.sachko.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.sachko.data.DataHelper;
import ru.netology.sachko.data.DbHelper;
import ru.netology.sachko.page.BuyByCard;
import ru.netology.sachko.page.BuyByCredit;
import ru.netology.sachko.page.HomePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.sachko.data.DataHelper.*;
import static ru.netology.sachko.data.DbHelper.*;

import org.openqa.selenium.WebDriver;

public class TestCard {
    DataHelper.CardNumber approvedCard = DataHelper.approvedCardInfo();
    DataHelper.CardNumber declinedCard = DataHelper.declinedCardInfo();

    @BeforeEach
    public void cleanTables() {
        DbHelper.cleanData();
    }

    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());

    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setupClass() {
        open("http://localhost:8080");
    }

    @Test
    void shouldSwitchBetweenPages() {
        HomePage homePage = new HomePage();
        BuyByCredit buyByCredit = homePage.getPageCredit();
        BuyByCard buyByCard = buyByCredit.switchOnDebitCardForm();
        buyByCard.switchOnCreditCardForm();
    }
    @Test
    void shouldBuyByApprovedCard() {
        HomePage homePage = new HomePage();
        BuyByCard buyByCard = homePage.getPageByCard();
        buyByCard.enterCardData(getApprovedCardInfo(), getValidMonth(), getValidYear(), getValidOwner(), getValidCvc());
        buyByCard.checkSuccessMessage();
        assertEquals(approvedCard.getStatus(), payData().getStatus());
    }

    @Test
    void shouldNotBuyByDeclineCard() {
        var homePage = new HomePage();
        var buyByCard = homePage.getPageByCard();
        buyByCard.enterCardData(getDeclinedCardInfo(), getValidMonth(), getValidYear(), getValidOwner(), getValidCvc());
        buyByCard.checkErrorMessage();
        assertEquals(declinedCard.getStatus(), payData().getStatus());
        checkEmptyOrderEntity();
    }

    @Test
    void shouldNotSendFormWithInvalidCardNumberLetters() {
        HomePage homePage = new HomePage();
        BuyByCard buyByCard = homePage.getPageByCard();
        buyByCard.enterCardData(getInvalidCardNumberLetters(), getValidMonth(), getValidYear(), getValidOwner(), getValidCvc());
        buyByCard.checkFormatError();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }
    @Test
    void shouldNotSendFormWithInvalidCardNumber() {
        HomePage homePage = new HomePage();
        BuyByCard buyByCard = homePage.getPageByCard();
        buyByCard.enterCardData(getInvalidCardNumber(), getValidMonth(), getValidYear(), getValidOwner(), getValidCvc());
        buyByCard.checkErrorMessage();

    }

    @Test
    void shouldNotSendFormWithInvalidMonth1() {
        HomePage homePage = new HomePage();
        var buyByCard = homePage.getPageByCard();
        buyByCard.enterCardData(getApprovedCardInfo(), getInvalidMonthOneNumber(), getValidYear(), getValidOwner(), getValidCvc());
        buyByCard.checkFormatError();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }
    @Test
    void shouldNotSendFormWithInvalidMonth2() {
        HomePage homePage = new HomePage();
        var buyByCard = homePage.getPageByCard();
        buyByCard.enterCardData(getApprovedCardInfo(), getInvalidMonthTwoNumbers(), getValidYear(), getValidOwner(), getValidCvc());
        buyByCard.checkInvalidError();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }

    @Test
    void shouldNotSendFormWithNullMonth() {
        HomePage homePage = new HomePage();
        var buyByCard = homePage.getPageByCard();
        buyByCard.enterCardData(getApprovedCardInfo(), getZeroMonth(), getValidYear(), getValidOwner(), getValidCvc());
        buyByCard.checkFormatError();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }

    @Test
    void shouldNotSendFormWithNullYear() {
        HomePage homePage = new HomePage();
        BuyByCard buyByCard = homePage.getPageByCard();
        buyByCard.enterCardData(getApprovedCardInfo(), getValidMonth(), getZeroYear(), getValidOwner(), getValidCvc());
        buyByCard.checkExpiredError();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();

    }



}
