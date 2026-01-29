package aaInfrastructure;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CMD {
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void good(String msg) {
        log(aaAppMSG.GOOD, msg);
    }

    public static void error(String msg) {
        log(aaAppMSG.ERROR, msg);
    }

    private static void log(String level, String msg) {
        String line = String.format("%s : %s | %s", level, msg, LocalDateTime.now().format(fmt));
        System.out.println(line);
        try {
            String tracerPath = aaAppConfig.getOrDefault("storage.exoTracer", "aaStorage/DataFiles/ExoTracer.txt");
            appendToFile(tracerPath, line);
        } catch (Exception ignored) {
            // si no está cargado config aún, al menos imprime por consola
        }
    }

    private static void appendToFile(String path, String line) throws IOException {
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(line + System.lineSeparator());
        }
    }
}
