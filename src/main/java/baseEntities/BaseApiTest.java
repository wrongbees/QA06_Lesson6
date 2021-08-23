package baseEntities;



import core.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import services.DataBaseServices;

import static io.restassured.RestAssured.given;

public class BaseApiTest {

    public static Logger logger = LoggerFactory.getLogger(BaseApiTest.class);
    public DataBaseServices dbService;

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://aqa06onl06.testrail.io/";

        RestAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)

                .auth().preemptive().basic(
                        ReadProperties.getInstance().getApiUsername(),
                        ReadProperties.getInstance().getApiPassword());

        dbService = new DataBaseServices();
    }
    @AfterTest
    public void tearDown(){
        dbService.closeConnection();
    }
}
