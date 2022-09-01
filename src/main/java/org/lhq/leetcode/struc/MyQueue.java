package org.lhq.leetcode.struc;


import java.util.Stack;

public class MyQueue {
    private Stack<Integer> stackOutput ;
    private Stack<Integer> stackInput ;
    public MyQueue() {
        stackOutput = new Stack<>();
        stackInput = new Stack<>();
    }

    public void push(int x) {
        stackInput.push(x);
    }

    public int pop() {
        while (!stackInput.isEmpty()){
            stackOutput.push(stackInput.pop());
        }
       return stackOutput.pop();
    }

    public int peek() {
        while (!stackInput.isEmpty()){
            stackOutput.push(stackInput.pop());
        }
        return stackOutput.peek();
    }

    public boolean empty() {
        return stackInput.isEmpty() && stackOutput.isEmpty();
    }
}
