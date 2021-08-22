package tests.api.home_work18;

import adapters.CasesAdapter;
import adapters.ProjectsAdapter;
import adapters.SectionAdapter;
import baseEntities.BaseApiTest;
import io.restassured.response.Response;
import models.Cases;
import models.Project;
import models.Section;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestRailApiCasesExecutionTest extends BaseApiTest {

    int numberOfCases = 3;
    int projectId;
    Section currentSection;
    List<Cases> actualCaseslist = new ArrayList<>();


    @Test
    public void addCasesTest() {
        Project project = Project.builder()
                .name("Hello, colleagues!")
                .announcement("Add Cases ...")
                .show_announcement(true)
                .suite_mode(1)
                .build();

        projectId = new ProjectsAdapter().add(project).jsonPath().get("id");

        Section section = Section.builder()
                .name("Я маленькая лошадка....")
                .description("И стою очень много денег...")
                .build();

        currentSection = new SectionAdapter().add(section, projectId);


        for (int titlenumber = 1; titlenumber <= numberOfCases; titlenumber++) {
            Cases cases = Cases.builder()
                    .title(String.format("Title 0%d", titlenumber))
                    .refs("Сим селя бим...")
                    .build();

            actualCaseslist.add(new CasesAdapter().add(cases, currentSection.getId()));
        }


    }

    @Test(dependsOnMethods = "addCasesTest")
    public void getCasesTest() {
        Cases actualCases = actualCaseslist.get(0);
        Cases cases = new CasesAdapter().get(actualCases);

        Assert.assertTrue(cases.getTitle().equals(actualCases.getTitle()));


    }

    @Test(dependsOnMethods = "getCasesTest")
    public void getAllCasesTest() {
        int suitId = new SectionAdapter().getSuitID(currentSection.getId());
        List<Cases> caseslist = new CasesAdapter().getAll(projectId,suitId);

        Assert.assertTrue(caseslist.containsAll(actualCaseslist));

       }

    @Test(dependsOnMethods = "getAllCasesTest")
    public void getHistoryForCases() {
        Cases actualCases = actualCaseslist.get(0);
        Response response = new CasesAdapter().getHistory(actualCases);
    }

    @Test(dependsOnMethods = "getHistoryForCases")
    public void copyCasesToSectionTest() {
        Section section = Section.builder()
                .name("Я маленькая лошадка....")
                .description("И стою очень много денег...")
                .build();

        Section newSection = new SectionAdapter().add(section, projectId);

        String case_ids = "";
        for (Cases item : actualCaseslist)
            case_ids = case_ids + ", " + item.getId();
        String caseIds = case_ids.substring(2);
        System.out.println(caseIds);

        new CasesAdapter().copy(newSection.getId(), caseIds);
    }

    @Test(dependsOnMethods = "copyCasesToSectionTest")
    public void updateCasesTest() {
        String case_ids = "";
        for (Cases item : actualCaseslist)
            case_ids = case_ids + ", " + item.getId();
        String caseIds = case_ids.substring(2);
        String caseIdsArray = String.format("[%s]", caseIds);

        String jsonBody = String.format("{\n" +
                "  \"case_ids\": %s,\n" +
                "  \"priority_id\": 1,\n" +
                "  \"estimate\": \"5m\"\n" +
                "}", caseIdsArray);

        int suitId = new SectionAdapter().getSuitID(currentSection.getId());

        Response response = new CasesAdapter().updateCases(suitId, jsonBody);
    }


    @Test(dependsOnMethods = "updateCasesTest")
    public void updateCaseTest() {
        Cases expected_cases = Cases.builder()
                .title("TITLE №00")
                .refs("Какое то обновленное поле...")
                .build();

        Cases actual_case = new CasesAdapter().updateCase(actualCaseslist.get(0).getId(), expected_cases);
        Assert.assertTrue(actual_case.getTitle().equals(expected_cases.getTitle()));
    }

    @Test(dependsOnMethods = "updateCaseTest")
    public void deleteCasesTest() {

        String case_ids = "";
        for (Cases item : actualCaseslist)
            case_ids = case_ids + ", " + item.getId();
        String caseIds = case_ids.substring(2);
        String caseIdsArray = String.format("[%s]", caseIds);

        String jsonBody = String.format("{\n" +
                "  \"case_ids\": %s\n" +
                "}", caseIdsArray);

        int suitId = new SectionAdapter().getSuitID(currentSection.getId());

        System.out.println(jsonBody);
        System.out.println(suitId);

        Response response = new CasesAdapter().deleteCases(projectId,suitId,jsonBody);
    }

}

