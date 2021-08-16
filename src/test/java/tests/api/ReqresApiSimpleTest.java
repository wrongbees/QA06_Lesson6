package tests.api;

import baseEntities.BaseApiReqresApiTest;
import body.RequestBodies;
import body.ResponseBodies;
import endpoints.ReqresEndpoints;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class ReqresApiSimpleTest extends BaseApiReqresApiTest {

    @Test
            public void test1(){

        Response response = given().request(Method.GET,ReqresEndpoints.TEST_1);

        String responseBody = response.getBody().asString();
        int statusCode = response.statusCode();

        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test2(){

        Response response = given().request(Method.GET,ReqresEndpoints.TEST_2);

        String responseBody = response.getBody().asString();
        int statusCode = response.statusCode();

        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test3(){

        Response response = given().request(Method.GET,ReqresEndpoints.TEST_3);

        String responseBody = response.getBody().asString();
        int statusCode = response.statusCode();

        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 404);
    }

    @Test
    public void test4(){

        Response response = given().request(Method.GET,ReqresEndpoints.TEST_4);

        String responseBody = response.getBody().asString();
        int statusCode = response.statusCode();

        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test5(){

        Response response = given().request(Method.GET,ReqresEndpoints.TEST_5);

        String responseBody = response.getBody().asString();
        int statusCode = response.statusCode();

        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test6(){

        Response response = given().request(Method.GET,ReqresEndpoints.TEST_6);

        String responseBody = response.getBody().asString();
        int statusCode = response.statusCode();

        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 404);
    }

    @Test
    public void test7(){

        Response response = given().body(RequestBodies.TEST_7).request(Method.POST,ReqresEndpoints.TEST_7);

        String responseBody = response.getBody().asString();
        int statusCode = response.statusCode();

        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 201);
    }

    @Test
    public void test8(){


        Response response = given().body(RequestBodies.TEST_8).request(Method.PUT,ReqresEndpoints.TEST_8);

        String responseBody = response.getBody().asString();
        int statusCode = response.statusCode();

        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test9(){

        Response response = given()
                .body(RequestBodies.TEST_9)
                .request(Method.PATCH,ReqresEndpoints.TEST_9);

        String responseBody = response.getBody().asString();
        int statusCode = response.statusCode();

        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test10(){

        Response response = given()
                .request(Method.DELETE,ReqresEndpoints.TEST_10);

        String responseBody = response.getBody().asString();
        int statusCode = response.statusCode();

        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 204);
    }

    @Test
    public void test11(){

        Response response = given()
                .body(RequestBodies.TEST_11)
                .request(Method.POST,ReqresEndpoints.TEST_11);

        String responseBody = response.getBody().asString();
        int statusCode = response.statusCode();

        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test12(){

        Response response = given()
                .body(RequestBodies.TEST_12)
                .request(Method.POST,ReqresEndpoints.TEST_12);

        String responseBody = response.getBody().asString();
        int statusCode = response.statusCode();

        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 400);
    }

    @Test
    public void test13(){

        Response response = given()
                .body(RequestBodies.TEST_13)
                .request(Method.POST,ReqresEndpoints.TEST_13);

        String responseBody = response.getBody().asString();
        int statusCode = response.statusCode();

        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test14(){

        Response response = given()
                .body(RequestBodies.TEST_14)
                //.post(ReqresEndpoints.TEST_14);
                .request(Method.POST,ReqresEndpoints.TEST_14);

        String responseBody = response.getBody().asString();
        int statusCode = response.statusCode();

        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 400);
    }

    @Test
    public void test15(){

        Response response = given()
                .request(Method.GET,ReqresEndpoints.TEST_15);

        String responseBody = response.getBody().asString();
        int statusCode = response.statusCode();

        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 200);
    }

}
