package brickbreaker.common;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoadImage extends Application {
    
    private List<ImageView> images;

    @Override
    public void start(Stage primaryStage) {
        // Ottieni il ClassLoader corrente
        //ClassLoader classLoader = LoadImage.class.getClassLoader();
        Pane root = new Pane();
        
        // Carica il file immagine come risorsa utilizzando il ClassLoader
        Image image = new Image(ClassLoader.getSystemResourceAsStream("images/Title.png"));
        ImageView imageView = new ImageView(image);
        //Image image = new Image(classLoader.getResourceAsStream("images/Title.png"));
        
        // Crea un ImageView per visualizzare l'immagine
        // Crea un layout di base per posizionare l'immagine
        root.getChildren().add(imageView);
        image = new Image(ClassLoader.getSystemResourceAsStream("images/hard.png"));
        imageView = new ImageView(image);
        //Image image = new Image(classLoader.getResourceAsStream("images/Title.png"));
        
        // Crea un ImageView per visualizzare l'immagine
        // Crea un layout di base per posizionare l'immagine
        root.getChildren().add(imageView);

        // Crea la scena e imposta il layout di base
        Scene scene = new Scene(root, 1000, 800);
        scene.setFill(Color.BLACK);

        // Imposta il titolo della finestra
        primaryStage.setTitle("JavaFX Image Viewer");

        // Imposta la scena nella finestra principale
        primaryStage.setScene(scene);

        // Mostra la finestra principale
        primaryStage.show();
    }
}