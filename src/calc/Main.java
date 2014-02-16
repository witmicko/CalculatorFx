package calc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getResource("calculator.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = (Parent) fxmlLoader.load(location.openStream());
        Scene scene = new Scene(root);

        Font.loadFont(getClass().getResource("DigitaldreamFat.ttf").toExternalForm(),20);
        System.out.println(Font.loadFont(getClass().getResource("DigitaldreamFat.ttf").toExternalForm(),50));

        String stylesheet = getClass().getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        primaryStage.setScene(scene);
        primaryStage.setTitle("hello");
        primaryStage.show();


//        MainController mainController = fxmlLoader.getController();
//        mainController.setStage(primaryStage);
//        mainController.showStage("Calculator");


//        Parent root = FXMLLoader.load(getClass().getResource("calculator.fxml"));
//
//        MainController mainController = fxmlLoader.getController();
//
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
