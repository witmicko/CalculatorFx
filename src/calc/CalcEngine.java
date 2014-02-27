package calc;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import org.omg.CORBA.*;

import java.lang.Object;
import java.util.*;
import java.util.List;

/**
 * Calculator engine, changes input infix expression into an postfix(RPN) and evaluates the RPN.
 *
 * @author Michal Ogrodniczak
 */
public class CalcEngine {
    private Stack<OPERATOR> operatorStack = new Stack<>();
    protected String lastAnswer = "";

    public enum OPERATOR {
        //@formatter:off
        ADD     ("+", "\\+", 2),
        SUB     ("-", "\\-", 2),
        MULT    ("*", "\\*", 3),
        DIV     ("/", "\\/", 3),
        OPEN_BR ("(", "\\(", 0),
        CLOSE_BR(")", "\\)", 0);
        //@formatter:on

        private final String op;
        private final String regex;
        private final String spaced;
        private final int precedence;

        private OPERATOR(String op, String regex, int precedence) {
            this.op = op;
            this.regex = regex;
            this.spaced = " " + op + " ";
            this.precedence = precedence;
        }

        public String toString() {
            return this.op;
        }
    }

    public CalcEngine() {
    }

    public String computeInput(String input) {
        operatorStack.clear();
        //@formatter:off
        //regex matching/replacing to correct the input syntax
        String text = input.replace   ("(-", "(0-")
                           .replace   ("(+", "(0+")
                           .replaceAll("^[\\-\\+]", "0" + input.charAt(0));
        //@formatter:on
        System.out.println("input: " + text);

        ArrayList<String> ops = split(text);
        MyStack postfix = convertToPostfix(ops);
        System.out.println("output stack " + postfix);
        double result = evaluatePostfix(postfix);
        System.out.println(result);

        this.lastAnswer = (result == (int) result) ? String.valueOf((int) result) : String.valueOf(result);
        return lastAnswer;
    }

    private MyStack convertToPostfix(ArrayList<String> ops) {
        MyStack output = new MyStack();
        for (String str : ops) {
            if (isNumber(str)) {
                output.push(Double.parseDouble(str));
            } else {
                try {
                    OPERATOR op = getOperator(str);
                    handleOperator(op, output);
                } catch (NotOperatorException e) {
                    e.printStackTrace();
                }
            }
        }
        while (!operatorStack.empty()) {
            output.push(operatorStack.pop());
        }
        return output;
    }

    private double evaluatePostfix(MyStack input) {
        MyStack output = new MyStack();
        for (Object o : input) {
            double temp = 0.0;
            output.push(o);
            System.out.println("in:  " + output.toString());
            if (output.peek() instanceof OPERATOR) {
                OPERATOR op = (OPERATOR) output.pop();
                double operand1 = (double) output.pop();
                double operand2 = (double) output.pop();

                switch (op) {
                    case ADD:
                        temp = (operand2 + operand1);
                        break;
                    case SUB:
                        temp = (operand2 - operand1);
                        break;
                    case MULT:
                        temp = (operand2 * operand1);
                        break;
                    case DIV:
                        if (operand1 == 0) temp = Double.NaN;
                        else temp = operand2 / operand1;
                        break;
                }
                output.push(temp);
                System.out.println("out: " + output);
            }
        }
        return (double) output.pop();
    }

    private void handleOperator(OPERATOR op, MyStack output) {
        if (operatorStack.isEmpty() || op == OPERATOR.OPEN_BR) operatorStack.push(op);

        else if (op == OPERATOR.CLOSE_BR) {
            while (operatorStack.peek() != OPERATOR.OPEN_BR) {
                output.push(operatorStack.pop());
            }//next one is OPEN_BRacket, so pop it
            operatorStack.pop();
        } else {
            while (!operatorStack.isEmpty() && operatorStack.peek().precedence >= op.precedence) {
                output.push(operatorStack.pop());
            }//current operator is less or equal in precedence to the top one on the stack, so it can be pushed
            operatorStack.push(op);
        }
    }

    public static ArrayList<String> split(String operation) {
        String str = operation;
        for (OPERATOR op : OPERATOR.values()) {
            str = str.replaceAll(op.regex, op.spaced);
        }

        String[] strings = (Iterables.toArray(Splitter.on(" ").split(str), String.class));
        ArrayList<String> ops = new ArrayList<>(Arrays.asList(strings));

        for (int i = ops.size() - 1; i >= 0; i--) {
            if (ops.get(i).equals("")) ops.remove(i);
        }
        return ops;
    }

    protected String getLastAnswer() {
        return this.lastAnswer;
    }


    public static void main(String[] args) {
        CalcEngine engine = new CalcEngine();
        engine.computeInput("(-3+8)-(3*(-9))");
    }

    private static boolean isNumber(String str) {
        return Character.isDigit(str.charAt(0));
    }

    private static OPERATOR getOperator(String str) throws NotOperatorException {
        for (OPERATOR op : OPERATOR.values()) {
            if (op.op.equals(str)) {
                return op;
            }
        }
        throw new NotOperatorException();
    }
}

class NotOperatorException extends Exception {
    public NotOperatorException() {
        super("Not an operator");
    }

    public NotOperatorException(String message) {
        super(message);
    }
}