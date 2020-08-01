package br.com.tspaschoal.components.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {

    public static Properties loadProperties() {
        final String basePath = getFullPath();
        final Properties bundle = new Properties();
        try {
            bundle.load(new FileInputStream(basePath + "application.properties"));
            return bundle;
        } catch (IOException e) {
            throw new RuntimeException("File can't be processed");
        }
    }

    public static String getFullPath() {
        return Paths.get("").toAbsolutePath().toString() + "/src/test/resources/";
    }

}
