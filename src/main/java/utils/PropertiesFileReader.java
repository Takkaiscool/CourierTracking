package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFileReader {
    private static Properties properties;
    private File file;

    public PropertiesFileReader(String filename) {
        try {
            file = new File(getClass().getClassLoader().getResource(filename).toURI());
            properties = new Properties();
            properties.load(new FileInputStream(file));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Properties getData() {
        return properties;
    }
}
