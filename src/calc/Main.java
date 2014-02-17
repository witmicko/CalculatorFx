package calc;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
import java.util.List;

public class Main extends Application {
//    @FXML Button multBtn, divBtn, plusBtn, minusBtn, decimalBtn, delBtn, equalsBtn,openBracket, closeBracket;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        URL location = getClass().getResource("res\\calculator.fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        final Parent root = (Parent) fxmlLoader.load(location.openStream());
        final Scene scene = new Scene(root);

        URL font = getClass().getResource("DigitaldreamFat.ttf");
        Font.loadFont(font.toExternalForm(), 50);

        String stylesheet = getClass().getResource("res\\stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        final MainController mainController = fxmlLoader.getController();

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

    /**
     * ref: StackOverflow
     *
     * @param parent
     * @param id
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T lookup(Node parent, String id, Class<T> clazz) {
        for (Node node : parent.lookupAll(id)) {
            if (node.getClass().isAssignableFrom(clazz)) {
                return (T) node;
            }
        }
        throw new IllegalArgumentException("Parent " + parent + " doesn't contain node with id " + id);
    }
}
