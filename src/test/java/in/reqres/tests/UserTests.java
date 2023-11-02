package in.reqres.tests;

import in.reqres.models.UserResponseModel;
import org.junit.jupiter.api.Test;


import static in.reqres.specs.UserListSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTests {

    @Test
    void successUserListTest() {

        UserResponseModel response = step("Make user list request", () ->
                given(userListRequestSpec)
                        .get("/users?page=2")
                        .then()
                        .spec(userListResponseSpec)
                        .extract().as(UserResponseModel.class));

        step("Verify response", () ->
                assertEquals(2, response.getPage()));

    }

}
