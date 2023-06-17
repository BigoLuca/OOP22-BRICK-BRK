package brickbreaker.common;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JsonUtils {

    private static final String DEFAULT_DATA = "data/";

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

    public static <E> E loadData(final Type type, final String filePath) {

        try (FileReader fileReader = new FileReader(filePath)) {
            Gson gson = new Gson();
            return gson.fromJson(fileReader, type);
        } catch (IOException e) {
            return JsonUtils.load(type, DEFAULT_DATA + filePath);
        }
    }

    public static <E> void saveData(final List<E> data, final String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(data);

            fileWriter.write(json);
            System.out.println("Salvataggio");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
