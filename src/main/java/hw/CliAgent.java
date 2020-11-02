package hw;

import hw.myqueue.Queue;
import hw.myqueue.QueueImpl;
import hw.mystack.Stack;
import hw.mystack.StackImpl;

import java.util.Scanner;

public class CliAgent {

    private static final Queue<String> queue;
    private static final Stack<String> stack;
    private static Scanner sc;

    static {
        sc = new Scanner(System.in);
        queue = new QueueImpl<>(10);
        stack = new StackImpl<>(10);
    }

    //1 Задание:
    public static void main(String[] args) {
        System.out.println("Hit enter if you ready");
        process(mainInputProcessor);
    }

    private static final Processor queueProcessor = x -> {
        System.out.println("enter command");
        if (x.startsWith("insert")) {
            String element = x.split("\\s")[1];
            queue.insert(element);
        } else if (x.startsWith("peekHead")) {
            System.out.println(queue.peekHead());
        } else if (x.startsWith("remove")) {
            queue.remove();
        } else if (x.startsWith("print")) {
            System.out.println(queue);
        } else System.out.println("command not found");
    };

    private static final Processor stackProcessor = x -> {
        System.out.println("enter command");
        if (x.startsWith("push")) {
            String element = x.split("\\s")[1];
            stack.push(element);
        } else if (x.startsWith("peek")) {
            System.out.println(stack.peek());
        } else if (x.startsWith("pop")) {
            System.out.println(stack.pop());
        } else if (x.startsWith("print")) {
            System.out.println(queue);
        } else System.out.println("command not found");
    };

    private static final Processor mainInputProcessor = x -> {
        System.out.println("What data structure you want to create?");
        System.out.println("1 - Queue");
        System.out.println("2 - Stack");
        System.out.print("enter number: ");
        String num = sc.nextLine();
        if (num.equals("1")) process(queueProcessor);
        else if (num.equals("2")) process(stackProcessor);
    };

    private static void process(Processor process) {
        String input;
        while (!(input = sc.nextLine()).equals("back")) {
            process.process(input);
        }
    }

    private interface Processor {
        void process(String sc);
    }

}
