package org.example.middle;

import java.util.Stack;

import org.example.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Design a stack that supports push, pop, top, and retrieving 
 * the minimum element in constant time.
 * 
 * Implement the MinStack class:
 * 
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
*/

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
public class MinStack {
    public static class MyMinStack {
        private Stack<Integer[]> stack;
        private Integer curMin;

        public MyMinStack() {
            this.stack = new Stack<>();
        }
        
        public void push(int val) {
            if (this.curMin == null)
                this.curMin = val;
            else this.curMin = Math.min(curMin, val);

            stack.push(new Integer[]{val, curMin});
        }
        
        public void pop() {
            if (!stack.isEmpty())
                stack.pop();
            if (!stack.isEmpty())
                this.curMin = stack.peek()[1];
            else this.curMin = null;
        }
        
        public int top() {
            return stack.peek()[0];
        }
        
        public int getMin() {
            return curMin;
        }
    }   

    @ParameterizedTest
    @CsvSource(value = {
        """
        '[push, push, push, getMin, pop, top, getMin]'
        '[-2,   0,    -3,   -3,     -3,  0,   -2]'
        """,
        """
        '[push,       push,       push,       top,        pop, getMin,     pop, getMin,     pop, push,       top,        getMin,     push,         top,         getMin,      pop, getMin]'
        '[2147483646, 2147483646, 2147483647, 2147483647, 0,   2147483646, 0,   2147483646, 0,   2147483647, 2147483647, 2147483647, -2147483648,  -2147483648, -2147483648, 0,   2147483647]'
        """
    }, delimiterString = "\n")
    void test(String commands, String data) {
        var cArr = ArrayUtils.toArray(commands, String.class);
        var dArr = ArrayUtils.toArray(data, Integer.class);
        var myStack = new MyMinStack();

        for (int i = 0; i < cArr.length; i++) {
            var command = cArr[i];
            if (command.equals("push"))
                myStack.push(dArr[i]);
            else if (command.equals("pop"))
                myStack.pop();
            else if (command.equals("top"))
                Assertions.assertEquals(dArr[i], myStack.top());
            else if (command.equals("getMin"))
                Assertions.assertEquals(dArr[i], myStack.getMin());
        }
    }
}
