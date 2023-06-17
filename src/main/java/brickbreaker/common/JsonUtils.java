package brickbreaker.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JsonUtils{

    public static <E> E load(Type type, String filepath) {
        // Carica il file JSON come stream di input
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(filepath);
        if (inputStream != null) {
            // Crea un InputStreamReader per leggere il contenuto del file JSON
            InputStreamReader reader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            return gson.fromJson(reader, type);

        } else {
            System.out.println("File not found");
            return null;
        }
    }

    public static <E> void save(List<E> list, String filepath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);

        URL resourceUrl = ClassLoader.getSystemResource(filepath);
        if (resourceUrl != null) {
            try {
                Path outputPath = Path.of(resourceUrl.toURI());
                Files.writeString(outputPath, json, StandardCharsets.UTF_8);
            } catch (Exception e) {
                System.out.println("Failed to save the file");
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found");
        }
    }

    public static void extractJsonFromJar(String sourcePath, String destinationPath) throws IOException {
        try (
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(sourcePath);
            OutputStream outputStream = new FileOutputStream(destinationPath)
        ) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}
