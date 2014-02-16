package calc;

import calc.transitions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.google.common.collect.Lists;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class MainController extends GridPane {
    private final static String OPERANDS = "[-+/X]";
    private final static boolean DEBUG = true;
    @FXML Display display;
    Stage thisStage;
    CalcEngine calcEngine = new CalcEngine();


    @FXML
    public void btnHandler(ActionEvent event){
        Button btn = (Button) event.getSource();
        new PulseTransition(btn).play();

        String id = btn.getId();
        String btnTxt = btn.getText();
        switch (id){
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
                if (display.getAll().length() > 0) display.removeLast();
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
}
