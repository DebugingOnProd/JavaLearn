package org.lhq;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.net.URL;

public class AppBoot extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL resource = getClass().getClassLoader().getResource("assets/fxml/main.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        scene.getStylesheets().add(new URL("file:styles.css").toExternalForm());
        stage.setTitle("JavaFX and Gradle");
        stage.getIcons().add(new Image("assets/textures/img/msft.png"));
        stage.setScene(scene);
        stage.show();
    }

}
