package ru.netology.test;

import lombok.val;
import ru.netology.data.DataHelp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class AuthTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldBeLogin() throws SQLException {
        DataHelp.cleaning();
        val loginPage = new LoginPage();
        val authInfo = DataHelp.getAuthInfo();
        val verificationPage = loginPage.login(authInfo);
        val verificationCode = DataHelp.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.verify(verificationCode);
        dashboardPage.visibleCheck();
    }

    @Test
    void shouldNotLoginWithInvalidVerification() throws SQLException {
        DataHelp.cleaning();
        val loginPage = new LoginPage();
        val authInfo = DataHelp.getAuthInfo();
        val verificationPage = loginPage.login(authInfo);
        val verificationCodeInvalid = DataHelp.getInvalidCode();
        verificationPage.verify(verificationCodeInvalid);
        verificationPage.invalidVerification();
    }

    @Test
    void shouldNotLoginWithInvalidUser() {
        val loginPage = new LoginPage();
        val authInfo = DataHelp.getAuthInfoInvalid();
        loginPage.login(authInfo);
        loginPage.checkError();
    }

    @Test
    void shouldNotLoginWithInvalidPassword() {
        val loginPage = new LoginPage();
        val authInfo = DataHelp.getAuthInfoVasyaWithInvalidPassword();
        loginPage.login(authInfo);
        loginPage.checkError();
    }
}
