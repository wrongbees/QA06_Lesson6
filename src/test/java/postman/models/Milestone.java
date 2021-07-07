package postman.models;

import java.util.Date;
import java.util.List;

public class Milestone {
    public int id = 19;
    public String name = "MS_01";
    public String  description = null;
    public Date start_on = null;
    public Date started_on = new Date(1625650876);
    public boolean is_started = true;
    public Date due_on = null;
    public boolean is_completed = false;
    public Date completed_on = null;
    public int project_id = 24;
    public int parent_id = 0;
    public String refs = null;
    public String url = "https://aqa06onl01.testrail.io/index.php?/milestones/view/19";
    public List<Object> milestones;

    public Milestone(){}
}
