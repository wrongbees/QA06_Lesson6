package core;

import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    private static ReadProperties currentReadProperty;
    protected Properties properties;


    private ReadProperties() {
        properties = new Properties();

        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ReadProperties getInstance(){
        if (currentReadProperty == null){return new ReadProperties();}
        return currentReadProperty;
    }

    public String getURL() { return properties.getProperty("url");}
    public String getTestRailURL() { return properties.getProperty("testrail_url");}
    public String getBrowser() { return properties.getProperty("browser");}
    public String getUsername() { return properties.getProperty("username");}
    public String getApiUsername() { return properties.getProperty("api_username");}
    public String getApiPassword() { return properties.getProperty("api_password");}
    public String getPassword() { return properties.getProperty("password");}
    public int getTimeout() { return Integer.parseInt(properties.getProperty("timeout"));}
    public String getFirstName() { return properties.getProperty("firstname");}
    public String getLastName() { return properties.getProperty("lastname");}
    public String getZip() { return properties.getProperty("zip");}
    public boolean getHeadless() {return Boolean.parseBoolean(properties.getProperty("headless"));}
}
