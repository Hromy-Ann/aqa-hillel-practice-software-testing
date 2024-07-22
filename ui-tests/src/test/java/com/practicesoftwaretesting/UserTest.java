package com.practicesoftwaretesting;

import com.practicesoftwaretesting.pages.Header;
import com.practicesoftwaretesting.pages.HomePage;
import com.practicesoftwaretesting.pages.LoginPage;
import com.practicesoftwaretesting.pages.RegisterPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class UserTest extends BaseTest {

    HomePage homePage = new HomePage();
    Header header = new Header();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();

    @Test
    void registerNewUserAndLogin() {
        homePage.open()
                .isLoaded();
        header.clickSignInMenuItem();
        loginPage.isLoaded()
                .clickRegisterYourAccount();
        registerPage.isLoaded()
                .assertThat()
                .hasCorrectInfo();
    }

    @AfterEach
    void cleanup() {
        var users = searchUsers("Hromy");
        users.getData().forEach(userToDelete -> deleteUser(userToDelete.getId()));
    }
}
