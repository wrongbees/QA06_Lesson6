package endpoints;

public interface ProjectEndpoints {
    String ADD_PROJECT ="/index.php?/api/v2/add_project";
    String GET_PROJECT ="index.php?/api/v2/get_project/%d";
    String GET_ALL_PROJECTS ="index.php?/api/v2/get_projects";
    String UPDATE_PROJECTS ="index.php?/api/v2/update_project/%d";
    String DELETE_PROJECTS ="index.php?/api/v2/delete_project/%d";
}
