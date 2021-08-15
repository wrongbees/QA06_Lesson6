package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {

    private String name;
    private String announcement;
    private boolean show_announcement;
    private boolean is_completed;
    private int suite_mode;
}
