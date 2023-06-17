package brickbreaker.common;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import brickbreaker.model.rank.Rank;

import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public static List<Rank> loadScores(final String filePath) {
        List<Rank> rank = new ArrayList<>();

        try (FileReader fileReader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Rank[] playerScoreArray = gson.fromJson(fileReader, Rank[].class);
            if (playerScoreArray != null) {
                for (Rank playerScore : playerScoreArray) {
                    rank.add(playerScore);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rank;
    }

    public static void saveScores(final List<Rank> rank, final String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            // Serializza l'oggetto Rank in formato JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(rank);

            // Scrivi il JSON nel file esterno
            fileWriter.write(json);
            System.out.println("Salvataggio");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
