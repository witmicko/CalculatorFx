package calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by michal on 16/02/14.
 */
public class MyStack extends ArrayList {
    private final double RAM_PERCENTAGE_PER_STACK = 0.1;
    private int maxSize;

    public MyStack() {
        //1 int == 8 bytes
        this.maxSize = (int) ((Runtime.getRuntime().freeMemory() / 8) * RAM_PERCENTAGE_PER_STACK);
    }

    public void push(Object object) throws StackOverFlowException {
        try {
            if (this.size() < this.maxSize) this.add(object);
            else throw new StackOverFlowException("StackOverFlowException in push()" + this.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Stack{" +
                "RAM_PERCENTAGE_PER_STACK=" + RAM_PERCENTAGE_PER_STACK +
                ", maxSize=" + maxSize +
                '}';
    }


    //    private int[] numbers;
//    private int stackSize;
//    private int topIndex;

//    public Stack(int size) {
//        this.numbers = new int[size];
//        this.stackSize = 0;
//        this.topIndex = -1;
//    }
//
//    public void push(int num) throws StackOverFlowException {
//        if (topIndex < stackSize) this.numbers[topIndex + 1] = num;
//        else throw new StackOverFlowException("on push\n" + this.toString());
//    }
//
//    public int pop() {
//        if (topIndex >= 0) {
//            int i = numbers[topIndex];
//            numbers[topIndex] = 0;
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "Stack{" +
//                "numbers=" + Arrays.toString(numbers) +
//                ", stackSize=" + stackSize +
//                ", topIndex=" + topIndex +
//                '}';
//    }
}

