package calc;

import calc.transitions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.google.common.collect.Lists;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.List;

public class MainController {
    @FXML Button multBtn, divBtn, plusBtn, minusBtn, decimalBtn, delBtn, equalsBtn,openBracket, closeBracket;

    private final static String OPERANDS = "[-+/\\*]";
    private final static boolean DEBUG = true;
    @FXML Display display;
    CalcEngine calcEngine = new CalcEngine();

    @FXML
    public void btnHandler(ActionEvent event) {
        Button btn = (Button) event.getSource();
        new PulseTransition(btn).play();

        String id = btn.getId();
        String btnTxt = btn.getText();
        switch (id) {
            case "num":
                display.appendText(btnTxt);
                break;
            case "operand":
                opperandBtn(btnTxt);
                break;
            case "equalsBtn":
                equalsBtn();
                break;
            case "decimalBtn":
                decimalPtBtn();
                break;
            case "delBtn":
                display.removeLast();
                break;
            case "acBtn":
                display.clear();
                break;
            case "answerBtn":
                display.setDisplay(calcEngine.lastAnswer);
                break;
            default:
                System.out.println("should not have done that");
        }
    }

    /**
     * Decimal point handler, if "." is entered on its own a "0" is added.
     */
    public void decimalPtBtn() {
        //check for numbers using regex
        if (display.getLast().matches("\\d")) {
            display.appendText(".");
        } else display.appendText("0.");
    }


    public void equalsBtn() {
        String answer = calcEngine.computeInput(display.getText());
        display.setDisplay(answer);
    }

    public void opperandBtn(String btnTxt) {
        if (display.getLast().matches(OPERANDS)) {
            display.removeLast();
            display.appendText(btnTxt);
        } else display.appendText(btnTxt);
    }

    private void debugPrint(String prompt) {
        if (DEBUG) System.out.println(prompt);
    }

    @FXML
    public void keyHandler(KeyEvent event) {
        KeyCode code = event.getCode();
        System.out.println("code: " + code.toString());
        System.out.println("event"+ event.toString());

        switch (event.getText()) {
            case "(":
                openBracket.fire();
                break;
            case ")":
                closeBracket.fire();
                break;
            case ".":
                decimalBtn.fire();
                break;
            case "+":
                plusBtn.fire();
                break;
            case "-":
                minusBtn.fire();
                break;
            case "*":
                multBtn.fire();
                break;
            case "/":
                divBtn.fire();
                break;
        }
        switch (code) {
            case BACK_SPACE:
            case DELETE:
                delBtn.fire();
                break;
            case ENTER:
            case EQUALS:
                equalsBtn.fire();
                break;
        }
    }
}
