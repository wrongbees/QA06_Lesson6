package tests.api.home_work18;

import adapters.MilestoneAdapter;
import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import models.Milestone;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestRailApiMilestoneExecutionTest extends BaseApiTest {
    int numberOfMilestones = 3;
    int projectId;
    List<Milestone> milestonesList = new ArrayList();

    @Test
    public void addMilestoneTest() {
        Project project = Project.builder()
                .name("Hello, colleagues!")
                .announcement("Add Milestone Project")
                .show_announcement(true)
                .suite_mode(1)
                .build();

        projectId = new ProjectsAdapter().add(project).jsonPath().get("id");

        for (int i = 1; i <= numberOfMilestones; i++) {
            Milestone expectedMilestone = Milestone.builder()
                    .name(String.format("MST0%d", i))
                    .description("Какое то описание...")
                    .refs("Не знаю что это...")
                    .start_on(1629331200)
                    .due_on(1629740843)
                    .build();

            Milestone actualMilestone = new MilestoneAdapter().add(expectedMilestone, projectId);
            milestonesList.add(actualMilestone);

            Assert.assertTrue(actualMilestone.getName().equals(expectedMilestone.getName()));
        }

    }

    @Test(dependsOnMethods = "addMilestoneTest")
    public void getMilestoneTest() {

        Milestone expectedMilestone = milestonesList.get(milestonesList.size() - 1);
        Milestone actualMilestone = new MilestoneAdapter().get(expectedMilestone.getId());

        Assert.assertTrue(expectedMilestone.getName().equals(actualMilestone.getName()));

    }

    @Test(dependsOnMethods = "getMilestoneTest")
    public void getAllMilestonesTest() {

        List<Milestone> actualMilestoneList = new MilestoneAdapter().getAll(projectId);

        Assert.assertTrue(actualMilestoneList.containsAll(milestonesList));
    }

    @Test(dependsOnMethods = "getAllMilestonesTest")
    public void updateMilestonesTest() {
        List<Milestone> updateMilestoneList = new ArrayList<>();

        int variableEntity = 1;
        for (Milestone actualMilestone : milestonesList) {
            Milestone newMilestone = Milestone.builder()
                    .name(String.format("MILSTONE0%d", variableEntity++))
                    .description("это обнавленное описание...")
                    .refs("А это я по прежнему не знаю что за поле...")
                    .start_on(1629231100)
                    .due_on(1629840843)
                    .build();

            Milestone updateMilestone = new MilestoneAdapter().update(actualMilestone, newMilestone);
            updateMilestoneList.add(updateMilestone);

            Assert.assertTrue(updateMilestone.getName().equals(newMilestone.getName()));
        }
        milestonesList = updateMilestoneList;
    }

    @Test(dependsOnMethods = "updateMilestonesTest")
    public void deleteMilestoneTest() {

        for (Milestone removableMilestone : milestonesList)
            new MilestoneAdapter().delete(removableMilestone);

        new ProjectsAdapter().delete(projectId);
    }

}
