package calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/**
 * Custom dynamic multi class type stack class. Implements all stack operations.
 *
 * @author Michal Ogrodniczak
 */
public class MyStack extends ArrayList {

    public MyStack() {
    }

    /**
     * standard stack push method, appends new object on the top of the stack.
     * @param object
     */
    public void push(Object object) {
        this.add(object);
    }

    /**
     * method removes and returns top object of the stack if such exits, otherwise a null object is returned.
     * @return
     */
    public Object pop() {
        if (this.size() > 0) {
            Object obj = this.get(this.size() - 1);
            this.remove(this.size() - 1);
            return obj;
        }
        return null;
    }

    /**
     * return top object of the stack without removing it.
     * @return
     */
    public Object peek() {
        if (this.size() > 0) {
            Object obj = this.get(this.size() - 1);
            return obj;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : this) {
            stringBuilder.append(o.toString() + ", ");
        }
        return stringBuilder.toString();
    }

}

class StackOverFlowException extends Exception {
    public StackOverFlowException(String string) {
        super(string);
    }

    StackOverFlowException() {
        super("Stack overflow exception");
    }
}