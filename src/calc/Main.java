package calc;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Main application class, manages and loads the resources and initializes the controller.
 *
 * @author Michal Ogrodniczak
 */
public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {

        URL location = getClass().getResource("calculator.fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        final Parent root = (Parent) fxmlLoader.load(location.openStream());
        final Scene scene = new Scene(root);

        //custom font
        URL font = getClass().getResource("DigitaldreamFat.ttf");
        Font.loadFont(font.toExternalForm(), 50);

        //customized css stylesheet
        String stylesheet = getClass().getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        final MainController mainController = fxmlLoader.getController();

        //keyboard event handler, nuber keys fire directly whereas function keys need to be handled differently,
        //due to use of special characters
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.toString());
                Button btn= (Button) fxmlLoader.getNamespace().get(event.getText());
                if (btn != null) {
                    btn.fire();
                }else{
                    mainController.keyHandler(event);
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("postfix calc");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
