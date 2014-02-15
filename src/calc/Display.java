package calc;

import javafx.scene.control.TextField;

/**
 * Custom class to represent display using TextField
 */
public class Display extends TextField {

    /**
     * Sets text on the displayBox (displayBox)
     *
     * @param string
     */
    public void setDisplay(String string) {
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

    /**
     * returns last character of the current operation
     * @return
     */
    public String getLast() {
        return (getAll().length() > 0) ? getAll().substring(getAll().length() - 1) : "";
    }

    public void removeLast() {
        setDisplay(getAll().substring(0, getAll().length() - 1));
    }
}