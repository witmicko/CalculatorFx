package calc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.google.common.collect.Lists;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class MainController extends GridPane {
    private final static String OPERANDS = "[-+/*]";
    private final static boolean DEBUG = true;
    @FXML Display display;
    Stage thisStage;
    CalcEngine calcEngine = new CalcEngine();

    /**
     * Button click/action handler, amends displayBox with button value/operand.
     *
     * @param event
     */
    @FXML
    public void numberBtn(ActionEvent event) {
        display.appendText(getButtonText(event));
        id10tUserHandler(event);
    }

    /**
     * Decimal point handler, if "." is entered on its own a "0" is added.
     */
    @FXML
    public void decimalPtBtn() {
        //check for numbers using regex
        if (display.getLast().matches("\\d")) {
            display.appendText(".");
        } else display.appendText("0.");
    }

    /**
     * DEL button handler, removes last character in the displayBox
     */
    @FXML
    public void delBtn() {
        if (display.getAll().length() > 0) display.removeLast();
    }

    @FXML
    public void equalsBtn() {
        List<Character> chars = Lists.charactersOf(display.getCharacters());
        calcEngine.prefixInput.addAll(chars);
        String answer = calcEngine.computeInput();
        display.setDisplay(answer);
    }

    @FXML
    public void opperandBtn(ActionEvent event) {
        String btnTxt = getButtonText(event);
        if (display.getLast().matches(OPERANDS)) {
            display.removeLast();
            display.appendText(btnTxt);
        } else display.appendText(btnTxt);
    }

    @FXML
    public void answerBtn() {
        display.setDisplay(calcEngine.lastAnswer);
    }

    @FXML
    public void acBtn() {
        display.clear();
    }


    private void debugPrint(String prompt) {
        if (DEBUG) System.out.println(prompt);
    }

    private String getButtonText(ActionEvent event) {
        return ((Button) event.getSource()).getText();
    }


    private void id10tUserHandler(ActionEvent event) {
        String str = display.getAll();
        String btnTxt = getButtonText(event);
        if (str.equals("8008") && btnTxt.equals("5")) showStage("ಠ_ಠ");
        else if((str+btnTxt).contains("/0"))display.appendText(" nope");

    }

    public void setStage(Stage stage) {
        thisStage = stage;
    }

    public void showStage(String string) {
        thisStage.setTitle(string);
        thisStage.show();
    }
}
