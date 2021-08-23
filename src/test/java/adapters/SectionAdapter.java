package adapters;

import endpoints.SectionEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Section;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;


public class SectionAdapter extends BaseAdapter {

    public Section add(Section section, int projectID) {
        Response response = given()
                .body(section, ObjectMapperType.GSON)
                .when()
                .post(String.format(SectionEndpoints.ADD_SECTION, projectID))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Section.class);
    }

    public int getSuitID(int id) {
        return given()
                .when()
                .get(String.format(SectionEndpoints.GET_SECTION, id))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("suite_id");

    }
}
