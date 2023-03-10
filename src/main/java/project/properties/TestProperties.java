package project.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    private final Properties properties = new Properties();

    private static TestProperties instance = null;

    private TestProperties(){
        try {
            properties.load(new FileInputStream("src/environment.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static TestProperties getInstance() {
        if (instance == null) {
            instance = new TestProperties();
        }
        return instance;
    }
    public Properties getProperties() {return properties;}

}
