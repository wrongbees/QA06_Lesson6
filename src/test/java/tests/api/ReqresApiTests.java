package tests.api;

import baseEntities.BaseApiReqresApiTest;
import body.RequestBodies;
import endpoints.ReqresEndpoints;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class ReqresApiTests extends BaseApiReqresApiTest {

    @Test
    public void test1() {
        given()
                .when()
                .get(ReqresEndpoints.TEST_1)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }

    @Test
    public void test2() {
        given()
                .when()
                .get(ReqresEndpoints.TEST_2)
                .then()
                .body("data.id", is(2))
                .body("support.url", is("https://reqres.in/#support-heading"))
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }

    @Test
    public void test3() {
        given()
                .when()
                .get(ReqresEndpoints.TEST_3)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .log().body();
    }

    @Test
    public void test4() {
        given()
                .when()
                .get(ReqresEndpoints.TEST_4)
                .then()
                .log().status()
                .body("data.id", hasItems(1, 2, 3, 4, 5, 6))
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }

    @Test
    public void test5() {
        given()
                .when()
                .get(ReqresEndpoints.TEST_5)
                .then()
                .body("data.id", is(2))
                .body("support.url", is("https://reqres.in/#support-heading"))
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }

    @Test
    public void test6() {
        given()
                .when()
                .get(ReqresEndpoints.TEST_6)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .log().status()
                .log().body();
    }


    @Test
    public void test7() {
        given()
                .when()
                .body(RequestBodies.TEST_7)
                .post(ReqresEndpoints.TEST_7)
                .then()
                .log().status()
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .statusCode(HttpStatus.SC_CREATED)
                .log().body();
    }


    @Test
    public void test8() {
        given()
                .when()
                .body(RequestBodies.TEST_8)
                .put(ReqresEndpoints.TEST_8)
                .then()
                .log().status()
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }


    @Test
    public void test9() {
        given()
                .when()
                .body(RequestBodies.TEST_9)
                .patch(ReqresEndpoints.TEST_9)
                .then()
                .log().status()
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }

    @Test
    public void test10() {
        given()
                .when()
                .delete(ReqresEndpoints.TEST_10)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .log().body();
    }

    @Test
    public void test11() {
        given()
                .when()
                .body(RequestBodies.TEST_11)
                .post(ReqresEndpoints.TEST_11)
                .then()
                .log().status()
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }


    @Test
    public void test12() {
        given()
                .when()
                .body(RequestBodies.TEST_12)
                .post(ReqresEndpoints.TEST_12)
                .then()
                .log().status()
                .body("error", is("Missing password"))
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().body();
    }

    @Test
    public void test13() {
        given()
                .when()
                .body(RequestBodies.TEST_13)
                .post(ReqresEndpoints.TEST_13)
                .then()
                .log().status()
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }

    @Test
    public void test14() {
        given()
                .when()
                .body(RequestBodies.TEST_14)
                .post(ReqresEndpoints.TEST_14)
                .then()
                .log().status()
                .body("error", is("Missing password"))
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().body();
    }

    @Test
    public void test15() {
        given()
                .when()
                .get(ReqresEndpoints.TEST_15)
                .then()
                .log().status()
                .body("data.id", hasItems(1, 2, 3, 4, 5, 6))
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }
}
