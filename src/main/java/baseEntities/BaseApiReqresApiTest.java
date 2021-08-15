package baseEntities;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

public class BaseApiReqresApiTest {

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://reqres.in";

    }
}
