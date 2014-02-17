package calc;

import calc.transitions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.google.common.collect.Lists;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    //    @FXML GridPane keyboard;
    @FXML Button multBtn, divBtn, plusBtn, minusBtn, decimalBtn, delBtn, acBtn, ansBtn, equalsBtn;
    @FXML Button _0Btn, _1Btn, _2Btn, _3Btn, _4Btn, _5Btn, _6Btn, _7Btn, _8Btn, _9Btn, leftBrBtn, rightBrBtn;

    private final static String OPERANDS = "[-+/X]";
    private final static boolean DEBUG = true;
    @FXML Display display;
    CalcEngine calcEngine = new CalcEngine();

    @FXML
    public void btnHandler(ActionEvent event) {
        System.out.println(event.toString());
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
        List<Character> chars = Lists.charactersOf(display.getCharacters());
        calcEngine.prefixInput.addAll(chars);
        String answer = calcEngine.computeInput();
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
        System.out.println("code: " + event.getCode());
        KeyCode code = event.getCode();
        switch (code) {
            case MULTIPLY:
                multBtn.fire();
                break;
            case DIVIDE:
                divBtn.fire();
                break;
            case PLUS:
            case ADD:
                plusBtn.fire();
                break;
            case MINUS:
            case SUBTRACT:
                minusBtn.fire();
                break;
            case DECIMAL:
            case PERIOD:
                decimalBtn.fire();
                break;
            case BACK_SPACE:
            case DELETE:
                delBtn.fire();
                break;
            case ENTER:
            case EQUALS:
                equalsBtn.fire();
                break;
            case DIGIT0:
            case NUMPAD0:
                _0Btn.fire();
                break;
            case DIGIT1:
            case NUMPAD1:
                _1Btn.fire();
                break;
            case DIGIT2:
            case NUMPAD2:
                _2Btn.fire();
                break;
            case DIGIT3:
            case NUMPAD3:
                _3Btn.fire();
                break;
            case DIGIT4:
            case NUMPAD4:
                _4Btn.fire();
                break;
            case DIGIT5:
            case NUMPAD5:
                _5Btn.fire();
                break;
            case DIGIT6:
            case NUMPAD6:
                _6Btn.fire();
                break;
            case DIGIT7:
            case NUMPAD7:
                _7Btn.fire();
                break;
            case DIGIT8:
            case NUMPAD8:
                _8Btn.fire();
                break;
            case DIGIT9:
            case NUMPAD9:
                _9Btn.fire();
                break;
            case OPEN_BRACKET:
                leftBrBtn.fire();
                break;
            case CLOSE_BRACKET:
                rightBrBtn.fire();
                break;
        }
    }
}
