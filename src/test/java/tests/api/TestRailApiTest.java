package tests.api;

import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import com.google.gson.GsonBuilder;
import endpoints.ProjectEndpoints;
import endpoints.UsersEndpoint;
import io.restassured.RestAssured;
import models.Project;
import models.ProjectTypes;
import models.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestRailApiTest extends BaseApiTest {
    int progectID;

    @Test
    public void getAllUsers() {
        String endpoint = "index.php?/api/v2/get_users";

        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getUserDetailsTest() {
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
    public void getAllUsersDetailsTest() {

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
    public void addProjectTest() {

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
    public void getProjectTest() {
        int projectID = 21;

        given()
                .when()
                .get(String.format(ProjectEndpoints.GET_PROJECT, projectID))
                .then()
                .log().body()
                .body("name", is("I can create projects too"))
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void getAllProjectTest() {


        given()
                .when()
                .get(String.format(ProjectEndpoints.GET_ALL_PROJECTS))
                .then()
                .log().body()
                .body("id", hasItem(21))
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void getAllProjectAdapterTest() {


        List<Project> projectsList = new ProjectsAdapter().get();

        System.out.println(projectsList.get(0));

    }

    @Test
    public void getProjectAdapterTest() {
        Project project = new ProjectsAdapter().get(21);

        System.out.println(project.getName());
    }

    @Test
    public void addProjectTest2() {

        Project project = Project.builder()
                .name("I can create projects too")
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .build();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", project.getName());
        jsonAsMap.put("suite_mode", project.getSuite_mode());


        given()
                .body(jsonAsMap)
                .when()
                .post(ProjectEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }


    @Test
    public void addProjectTest3() {

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

    @Test
    public void staticJsonValidationTest() throws IOException {
        Project expectedProject = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                .fromJson(new FileReader("src/test/resources/expectedProject.json"), Project.class);

        Project actualProject = new ProjectsAdapter().get(21);

        Assert.assertTrue(expectedProject.equals(actualProject));

    }


}
