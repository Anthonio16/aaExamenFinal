package aaInfrastructure;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class aaAppConfig {
    private static Properties props;

    public static void load(String path) throws aaAppException {
        props = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
        } catch (IOException e) {
            throw new aaAppException("No se pudo leer app.properties: " + path, e);
        }
    }

    public static String get(String key) throws aaAppException {
        if (props == null) throw new aaAppException("AppConfig no ha sido cargado.");
        String v = props.getProperty(key);
        if (v == null) throw new aaAppException("Falta propiedad: " + key);
        return v.trim();
    }

    public static String getOrDefault(String key, String def) {
        if (props == null) return def;
        String v = props.getProperty(key);
        return v == null ? def : v.trim();
    }
}
