package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanAuthCodes;
import static ru.netology.data.SQLHelper.cleanDatabase;


public class LoginTest {
    LoginPage loginPage;
    DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();

    @AfterAll
    static void tearDawnAll() {
        cleanAuthCodes();
    }
    @BeforeEach
    void setUp(){
            loginPage = open("http://localhost:9999", LoginPage.class);}
    @AfterEach
    void teardawn() {
        cleanDatabase();
    }
    @Test
    void shouldSuccessfulLogin() {
        var VerificationPage = loginPage.ValidLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        VerificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldGetErrorNotificationIfUserIsNotExistInBase() {
        var authInfo = DataHelper.generateRandomUser();
        var verificationPage = loginPage.ValidLogin(authInfo);
        loginPage.verifyErrorNotification();
    }
}