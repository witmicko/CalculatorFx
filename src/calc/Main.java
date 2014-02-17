package calc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.Mnemonic;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        URL location = getClass().getResource("res\\calculator.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        final Parent root = (Parent) fxmlLoader.load(location.openStream());
        final Scene scene = new Scene(root);

        URL font = getClass().getResource("DigitaldreamFat.ttf");
        Font.loadFont(font.toExternalForm(),50);

        String stylesheet = getClass().getResource("res\\stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        final MainController mainController = fxmlLoader.getController();


        final List list = root.getChildrenUnmodifiable();
        System.out.println(root.lookup("#keyboard"));

        scene.addEventHandler(KeyEvent.KEY_PRESSED,new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                mainController.keyHandler(event);
            }
        });


        primaryStage.setScene(scene);
        primaryStage.setTitle("hello");
        primaryStage.show();


//        MainController mainController = fxmlLoader.getController();
//        mainController.




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
