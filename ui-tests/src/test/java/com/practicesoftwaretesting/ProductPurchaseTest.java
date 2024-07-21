package com.practicesoftwaretesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.practicesoftwaretesting.pages.*;
import com.practicesoftwaretesting.user.UserSteps;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.practicesoftwaretesting.user.UserSteps.DEFAULT_PASSWORD;

public class ProductPurchaseTest {

    UserSteps userSteps = new UserSteps();
    LoginPage loginPage = new LoginPage();
    AccountPage accountPage = new AccountPage();
    HomePage homePage = new HomePage();
    ProductPage productPage = new ProductPage();
    Header header = new Header();
    CheckoutPage checkoutPage = new CheckoutPage();

    static {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
        RestAssured.config = RestAssuredConfig.config()
                .objectMapperConfig(new ObjectMapperConfig()
                        .jackson2ObjectMapperFactory((type, s) -> {
                                    ObjectMapper objectMapper = new ObjectMapper();
                                    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
                                    return objectMapper;
                                }
                        ));
    }

    @BeforeEach
    void setup() {
        var email = "blablabla@gmail.com";
        userSteps.registerUser(email, DEFAULT_PASSWORD);

        open("https://practicesoftwaretesting.com/auth/login");
        loginPage.login(email, DEFAULT_PASSWORD);
        accountPage.isLoaded();
        open("https://practicesoftwaretesting.com/#/");
    }

    @Test
    void addProductToCartAndPurchaseIt() {
        homePage.isLoaded()
                .clickOnTheFirstProduct();

        productPage.isLoaded()
                .addToCart();

        header.clickCartMenuItem();
        checkoutPage.isLoaded()
                .proceedToCheckout()
                .proceedToCheckoutSignedIn()
                .proceedToCheckoutBillingAddress()
                .chooseCashPaymentMethodAndConfirm()
                .assertThat()
                .successfulMessageIsDisplayed();
    }
}
