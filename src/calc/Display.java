package calc;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;


/**
 * Custom class to represent display using TextField
 */
public class Display extends TextField {

    public Display() {
//        this.;
    }

    /**
     * Sets text on the displayBox (displayBox)
     *
     * @param string
     */
    @FXML
    public void setDisplay(String string) {
        this.setText(string);
    }

    /**
     * returns last character of the current operation
     *
     * @return
     */
    public String getLast() {
        return (getText().length() > 0) ? getText().substring(getText().length() - 1) : "";
    }

    public void removeLast() {
        if (getText().length() > 0) {
            setDisplay(getText().substring(0, getText().length() - 1));
        }
    }
}
