package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.google.common.collect.Lists;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.List;

public class MainController extends GridPane {
    private final static boolean DEBUG = true;
    @FXML
    Display display;

    CalcEngine calcEngine = new CalcEngine();

    /**
     * Button click/action handler, amends displayBox with button value/operand.
     *
     * @param actionEvent
     */
    @FXML
    public void numberBtn(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        StringBuilder stringBuilder = new StringBuilder(display.getAll());
        display.setDisplayBox(stringBuilder.append(btn.getText()).toString());
    }

    /**
     * Decimal point handler, if "." is entered on its own a "0" is added.
     *
     * @param actionEvent
     */
    @FXML
    public void decimalPtBtn(ActionEvent actionEvent) {
        String str = display.getLast();
        if (str.matches("\\d")) {
            display.amendDisplay(".");
        } else display.amendDisplay("0.");
    }

    /**
     * DEL button handler, removes last character in the displayBox
     *
     * @param actionEvent
     */
    @FXML
    public void delBtn(ActionEvent actionEvent) {
        if (display.getAll().length() > 0) display.removeLast();
    }

    @FXML
    public void equalsBtn(ActionEvent actionEvent) {
        List<Character> chars = Lists.charactersOf(display.getCharacters());
        calcEngine.prefixInput.addAll(chars);
        String answer = calcEngine.computeInput();
        display.setDisplayBox(answer);
    }

    @FXML
    public void opperandBtn(ActionEvent actionEvent) {

    }


    private void debugPrint(String prompt) {
        if (DEBUG) System.out.println(prompt);
    }

}
