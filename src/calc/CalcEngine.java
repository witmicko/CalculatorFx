package calc;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 14/02/14.
 */
public class CalcEngine {
    protected String lastAnswer = "";
    protected List<Character> prefixInput = new ArrayList<Character>();
    protected List<Character> postfixInput = new ArrayList<Character>();


    public CalcEngine() {}

    public String computeInput() {
        Joiner joiner = Joiner.on(", ");
        String answer = joiner.join(prefixInput).replaceAll(", ", "");
        prefixInput.clear();
        this.lastAnswer = answer;
        return answer;
    }

    protected String getLastAnswer() {
        return this.lastAnswer;
    }
}
