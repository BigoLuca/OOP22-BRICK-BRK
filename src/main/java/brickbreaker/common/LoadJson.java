package brickbreaker.common;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class LoadJson{

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

    public static void write(Object type, String filepath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter(filepath)) {
            gson.toJson(type, writer);
            System.out.println("Object written to JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
