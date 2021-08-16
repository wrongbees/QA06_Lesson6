package tests.api;

import baseEntities.BaseApiTest;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import endpoints.ProjectEndpoints;
import endpoints.UsersEndpoint;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import models.Project;
import models.ProjectTypes;
import models.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.ws.Response;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestRailApiTest extends BaseApiTest {
    int progectID;

    @Test
    public void getAllUsers(){
        String endpoint =  "index.php?/api/v2/get_users";

        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getUserDetailsTest(){
        int userID = 1;
        String endpoint = "/index.php?/api/v2/get_user/%s";

        given()
                .when()
                .get(String.format(endpoint, userID))
                .then()
                .log().body()
                .body("name", is("Alex Tros"))
                .body("email", equalTo("atrostyanko+0606@gmail.com"))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getAllUsersDetailsTest(){

        User user = User.builder()
                .name("Alex Tros")
                .email("atrostyanko+0606@gmail.com")
                .build();

        RestAssured.given()
                .when()
                .get(UsersEndpoint.GET_USERS)
                .then()
                .log().body()
                .body("get(0).name", is(user.getName()))
                .body("get(0).email", equalTo(user.getEmail()))
                .statusCode(HttpStatus.SC_OK);
    }
@Test
    public void addProjectTest(){

        Project project = Project.builder()
                .name("I can create projects too")
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .build();

        given()
                .body(String.format("{\n" +
                        "  \"name\": \"%s\",\n" +
                        "  \"announcement\": \"%d\"\n" +
                        "  \n" +
                        "}", project.getName(), project.getSuite_mode()))
                .when()
                .post(ProjectEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);

    }
    @Test
    public void getProjectTest(){
    int projectID = 21;

        given()
                .when()
                .get(String.format(ProjectEndpoints.GET_PROJECT,projectID))
                .then()
                .log().body()
                .body("name",is("I can create projects too"))
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void getAllProjectTest(){


        given()
                .when()
                .get(String.format(ProjectEndpoints.GET_ALL_PROJECTS))
                .then()
                .log().body()
                .body("id",hasItem(21))
                .statusCode(HttpStatus.SC_OK);

    }
//    @Test
//    public void addProjectTest2(){
//
//        Project project = Project.builder()
//                .name("I can create projects too")
//                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
//                .build();
//
//        Map<String,Object> jsonAsMap =new
//        given()
//                .body())
//                .when()
//                .post(ProjectEndpoints.ADD_PROJECT)
//                .then()
//                .log().body()
//                .statusCode(HttpStatus.SC_OK);
//

    @Test
    public void addProjectTest3(){

        Project project = Project.builder()
                .name("I can create projects too")
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .build();

        given()
                .body(project)
                .when()
                .post(ProjectEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);

    }

}
