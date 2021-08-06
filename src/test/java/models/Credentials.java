package models;

import java.util.ArrayList;
import java.util.List;

public class Credentials {
    String userName;
    String password;
    List<String> userNameList = new ArrayList<>();
    List<String> passwordList = new ArrayList<>();

    public Credentials() {

    }

    public String getUserName() {
        return userName;
    }

    protected void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public List<String> getUserNameList() {
        return userNameList;
    }

    protected void setUserNameList(String... userName) {
        if (userName.length > 0) {
            for (String name : userName)
                userNameList.add(name);
        }
    }

    public List<String> getPasswordList() {
        return passwordList;
    }

    protected void setPasswordList(String... passwords) {
        if (passwords.length > 0) {
            for (String password : passwords)
                passwordList.add(password);
        }
    }
}
