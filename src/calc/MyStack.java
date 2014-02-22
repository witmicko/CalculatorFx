package calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by michal on 16/02/14.
 */
public class MyStack extends ArrayList{
    private final double RAM_PERCENTAGE_PER_STACK = 0.1;
    private int maxSize;

    public MyStack() {
        //1 int == 8 bytes
//        this.maxSize = (int) ((Runtime.getRuntime().freeMemory() / 8) * RAM_PERCENTAGE_PER_STACK);
    }

    public void push(Object object) {
//        try {
//            if (this.size() < this.maxSize)
                this.add(object);
//            else throw new StackOverFlowException("StackOverFlowException in push()" + this.toString());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
    public Object pop(){
        if(this.size()>0){
            Object obj = this.get(this.size()-1);
            this.remove(this.size()-1);
            System.out.println();
            return obj;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Object o: this){
            stringBuilder.append(o.toString()+", ");
        }
        return stringBuilder.toString();
    }

    public Object peek() {
        if(this.size()>0){
            Object obj = this.get(this.size()-1);
            return obj;
        }
        return null;
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