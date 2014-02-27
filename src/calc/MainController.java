package calc;

import calc.transitions.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Class responsible for handling UI events
 *
 * @author Michal Ogrodniczak
 */
public class MainController {
    @FXML Button multBtn, divBtn, plusBtn, minusBtn, decimalBtn, delBtn, equalsBtn, openBracket, closeBracket;

    private final static String OPERATORS = "[-+/*]";
    private final static boolean DEBUG = true;
    @FXML Display display;
    CalcEngine calcEngine = new CalcEngine();

    /**
     * handles button clicks,depending on id of the button calls appropriate methods
     *
     * @param event an ActionEvent created by javaFx.
     */
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
                operatorBtn(btnTxt);
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
                display.setText(calcEngine.lastAnswer);
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

    /**
     * Calls CalcEngine sending currently stored expression on the display,
     * CalcEngine returns mathematical solution(result) and it is displayed.
     * In case of invalid syntax an exception is caught and notifies user of the error after which display is set to
     * to last known state.
     */
    public void equalsBtn() {
        final String expr = display.getText();
        try {
            String answer = calcEngine.computeInput(display.getText());
            display.setText(answer);
        } catch (NullPointerException e) {
            display.setText("SYNTAX ERROR");
            BounceTransition bounce = new BounceTransition(display);
            bounce.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    display.setText(expr);
                }
            });
            bounce.play();
        }
    }

    /**
     * handles operator buttons [/*-+]. restricts to only one consecutive operator, in order to do an
     * operation on negative number an user must use <operator>(-<number>) syntax.
     * @param btnTxt
     */
    public void operatorBtn(String btnTxt) {
        if (display.getLast().matches(OPERATORS)) {
            display.removeLast();
            display.appendText(btnTxt);
        } else display.appendText(btnTxt);
    }

    /**
     * an utility method used for debugging.
     * @param prompt
     */
    private void debugPrint(String prompt) {
        if (DEBUG) System.out.println(prompt);
    }

    /**
     * keyboard handler, it is possible to operate this calculator using keyboard, but input methods
     * cannot be mixed as then only mouse works.
     * The method itself has a nasty switch statement but this was the only solution that worked.
     * @param event
     */
    @FXML
    public void keyHandler(KeyEvent event) {
        KeyCode code = event.getCode();
        debugPrint("code: " + code.toString());
        debugPrint("event"+ event.toString());

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
