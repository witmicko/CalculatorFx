package calc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        URL location = getClass().getResource("calculator.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = (Parent) fxmlLoader.load(location.openStream());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        MainController mainController = fxmlLoader.getController();
        mainController.setStage(primaryStage);
        mainController.showStage("Calculator");



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
