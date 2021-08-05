package models;

public class UserBuilder {
    private User user;


    public UserBuilder() {
        user = new User();
    }

    public UserBuilder setFirstName(String name) {
        user.setFirstname(name);
        return this;

    }

    public UserBuilder setLastName(String lastName){
        user.setLastname(lastName);
        return this;
    }

    public UserBuilder setZip(String zip){
        user.setZip(zip);
        return this;
    }

    public User build(){
        return this.user;
    }

}

