package ru.netology.sachko.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
public class HomePage {

    private SelenideElement heading = $("h2.heading");
    private SelenideElement buttonCard = $$("[class=button__content]").get(0);
    private SelenideElement buttonCredit = $$("[class=button__content]").get(1);

    public HomePage() {
        heading.shouldBe(Condition.visible);
    }


    public BuyByCard getPageByCard() {
        buttonCard.click();
        return new BuyByCard();
    }

    public BuyByCredit getPageCredit() {
        buttonCredit.click();
        return new BuyByCredit();
    }
}
