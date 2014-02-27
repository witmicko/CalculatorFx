package calc;

import javafx.scene.control.TextField;

/**
 * Custom class to represent display using TextField, doing this allowed addition of extra utility methods for handling
 * the display.
 *
 * @author Michal Ogrodniczak
 */
public class Display extends TextField {

    public Display() {
    }

    /**
     * returns last character of the current operation.
     *
     * @return
     */
    public String getLast() {
        return (getText().length() > 0) ? getText().substring(getText().length() - 1) : "";
    }

    /**
     * removes very last character on the display.
     */
    public void removeLast() {
        if (getText().length() > 0) {
            setText(getText().substring(0, getText().length() - 1));
        }
    }
}
