package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by michal on 15/02/14.
 */
public class Display extends TextField {

//    public Display(){
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("displayBox.fxml"));
//        fxmlLoader.setRoot(this);
//        fxmlLoader.setController(this);
//        try {
//            fxmlLoader.load();
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }
//
//    }


    /**
     * Sets text on the displayBox (displayBox)
     *
     * @param string
     */
    public void setDisplayBox(String string) {
        this.setText(string);
    }

    /**
     * adds string at the end of the displayBox
     *
     * @param string
     */
    public void amendDisplay(String string) {
        this.setText(getAll() + string);
    }

    /**
     * Returns text currently displayed
     *
     * @return
     */
    public String getAll() {
        return this.getText();
    }

    public String getLast() {
        return (getAll().length() > 0) ? getAll().substring(getAll().length() - 1) : "";
    }

    public void removeLast() {
        setDisplayBox(getAll().substring(0, getAll().length() - 1));
    }
}
