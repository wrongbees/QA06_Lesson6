package models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {

    private String firstname;
    private String lastname;
    private String zip;

}


