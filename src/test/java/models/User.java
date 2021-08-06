package models;

public class User {

    private String firstname;
    private String lastname;
    private String zip;

    public User() {
    }


    public String getFirstname() {
        return firstname;
    }

    protected void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    protected void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getZip() {
        return zip;
    }

    protected void setZip(String zip) {
        this.zip = zip;
    }

}


