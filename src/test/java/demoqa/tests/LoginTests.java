package demoqa.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static demoqa.tests.TestData.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class LoginTests extends TestBase{

    @Test
    void successfulLoginWithUiTest() {
        open("https://demoqa.com/login");
        sleep(30000);
        $("#userName").setValue(LOGIN);
        $("#password").setValue(PASSWORD);
        $("#login").click();
        $("#userName-value").shouldHave(text(LOGIN));
    }

    @Test
    void successfulLoginWithApiTest() {
        String authData = "{\"userName\":\"test123456\",\"password\":\"Test123456@\"}"; // BAD PRACTICE

        Response authResponse = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.path("expires")));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.path("token")));

        open("/profile");
        $("#userName-value").shouldHave(text(LOGIN));
    }
}
