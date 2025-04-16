package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public void VerificationPage() {
        codeField.shouldBe(Condition.visible);
    }

    public DashboardPage validVerify(String VerificationCode) {
        Verify (VerificationCode);
        return new DashboardPage();
    }
    public void Verify(String VerificationCode) {
        codeField.setValue(VerificationCode);
        verifyButton.click();
    }}
