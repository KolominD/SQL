package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelp;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement error = $("[data-test-id=error-notification]");

    public DashboardPage verify(DataHelp.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }

    public void invalidVerification() {
        error.shouldBe(text("Ошибка! Неверно указан код! Попробуйте ещё раз."));
    }
}
