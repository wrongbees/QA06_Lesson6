package models;

public class CredentialsBuilder {
    private Credentials credentials;

    public CredentialsBuilder() {
        this.credentials = new Credentials();
    }

    public CredentialsBuilder setUserName(String userName) {
        credentials.setUserName(userName);
        return this;
    }

    public CredentialsBuilder setPassword(String password) {
        credentials.setPassword(password);
        return this;
    }

    public CredentialsBuilder setUserNameList(String... userName) {
        credentials.setUserNameList(userName);
        return this;
    }

    public Credentials build() {
        return this.credentials;
    }


}
