package calc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader.load(getClass().getResource("calculator.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("calculator.fxml"));
//
//        MainController mainController = new MainController();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
