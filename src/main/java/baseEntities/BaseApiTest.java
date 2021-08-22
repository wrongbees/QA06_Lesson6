package baseEntities;


import core.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BaseApiTest {


    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://aqa06onl06.testrail.io/";

        RestAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)

                .auth().preemptive().basic(
                        ReadProperties.getInstance().getApiUsername(),
                        ReadProperties.getInstance().getApiPassword());


    }

}

