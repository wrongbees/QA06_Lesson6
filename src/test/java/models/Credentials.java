package models;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class Credentials {
    String userName;
    String password;
    List<String> userNameList = new ArrayList<>();
    List<String> passwordList = new ArrayList<>();


    protected void setUserNameList(String... userName) {
        if (userName.length > 0) {
            for (String name : userName)
                userNameList.add(name);
        }
    }

    protected void setPasswordList(String... passwords) {
        if (passwords.length > 0) {
            for (String password : passwords)
                passwordList.add(password);
        }
    }
}
