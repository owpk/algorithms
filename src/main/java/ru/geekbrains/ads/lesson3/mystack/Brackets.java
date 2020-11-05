package ru.geekbrains.ads.lesson3.mystack;

import java.util.HashMap;
import java.util.Map;

public class Brackets {

    private static final String TEXT = """
                public void push(E value) {
                    data[size++] = value;
                }
            """;

    private static final Map<Character, Character> BRACKETS = new HashMap<>() {{
        put('{', '}');
        put('[', ']');
        put('(', ')');
    }};

    public static void main(String[] args) {
        new Brackets(TEXT).check();
    }


    private final String textToCheck;

    public Brackets(String textToCheck) {
        this.textToCheck = textToCheck;
    }

    public void check() {
        Stack<Character> stack = new StackImpl<>(textToCheck.length());
        for (int index = 0; index < textToCheck.length(); index++) {
            checkBracket(stack, index);
        }
        if (!stack.isEmpty()) {
            System.err.println("Error: missing right delimiter!");
        }
    }

    private void checkBracket(Stack<Character> stack, int index) {
        char currentChar = textToCheck.charAt(index);
        switch (currentChar) {
            case '{':
            case '[':
            case '(':
                stack.push(currentChar);
                break;
            case '}':
            case ']':
            case ')':
                if (stack.isEmpty()) {
                    System.err.println("Error: " + currentChar + " at " + index);
                    break;
                }

                Character lastOpenedBracket = stack.pop();
                if (currentChar != BRACKETS.get(lastOpenedBracket)) {
                    System.err.println("Error: " + currentChar + " at " + index);
                }
                break;
            default:
                break;
        }
    }
}
