package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
    public void readProperties() throws IOException {
        Properties properties = new Properties();
        String namePropertiesFile = "dev.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(namePropertiesFile);
        if(inputStream!=null)
            properties.load(inputStream);

        ConfigEnv.host=properties.getProperty("host");
        ConfigEnv.user=properties.getProperty("user");
        ConfigEnv.password=properties.getProperty("password");

        inputStream.close();

    }
}
