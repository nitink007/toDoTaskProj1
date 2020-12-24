package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static String readProperties(String key, String propFileName) {
        String value = null;
        InputStream inputStream = null;

        try {
            Properties prop = new Properties();
            inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            value = prop.getProperty(key);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            assert inputStream != null;
            try {
                inputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return value;
    }

    public static String getAppPropValues(String key) {
        return readProperties(key, "configs/app.properties");
    }


}
