package sng78.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {

    public String calculate(String infix) {
        if (infix == null || infix.length() == 0) {
            return "Ошибка! Пустой запрос!";
        }
        List<String> postfix;

        try {
            postfix = Calculator.infixToPostfix(infix);
        } catch (Exception EmptyStackException) {
            return "Ошибка! Неверный формат входных данных / недопустимые символы!";
        }

        return Calculator.calcFromPostfix(postfix);
    }

    protected static List<String> infixToPostfix(String infix) {
        Stack<String> stack = new Stack<>();
        List<String> postfix = new ArrayList<>();

        infix = infix.replaceAll(",", ".");
        String[] partsOfInfix = infix.split(" ");

        for (String part : partsOfInfix) {
            if (part.equals("(")) {
                stack.add(part);
            } else if (part.equals(")")) {
                while (!stack.peek().equals("(")) {
                    postfix.add(String.valueOf(stack.pop()));
                }
                stack.pop();
            } else if (isNumeric(part)) {
                part = String.valueOf
                        (Math.round(Double.parseDouble(part) * 100.0) / 100.0);
                postfix.add((part));
            } else {
                while (!stack.isEmpty() && priority(part) >= priority(stack.peek())) {
                    postfix.add(String.valueOf(stack.pop()));
                }
                stack.add(part);
            }
        }

        while (!stack.isEmpty()) {
            postfix.add(String.valueOf(stack.pop()));
        }

        return postfix;
    }

    protected static String calcFromPostfix(List<String> rpn) {
        Stack<Double> stack = new Stack<>();
        double a1, a2, res;

        for (String s : rpn) {
            if (isNumeric(s)) {
                stack.push(Double.parseDouble(s));
            } else {
                a2 = stack.pop();
                a1 = stack.pop();
                if (a2 == 0 && s.equals("/")) {
                    return "Ошибка! Деление на 0!";
                }
                switch (s) {
                    case "^":
                        res = Math.pow(a1, a2);
                        break;
                    case "+":
                        res = a1 + a2;
                        break;
                    case "-":
                        res = a1 - a2;
                        break;
                    case "*":
                        res = a1 * a2;
                        break;
                    case "/":
                        res = a1 / a2;
                        break;
                    default:
                        return "Ошибка! Неверный формат входных данных / " +
                                "недопустимые символы!";
                }
                stack.push(res);
            }
        }

        return String.valueOf(Math.round(stack.pop() * 100.0) / 100.0);
    }

    protected static int priority(String s) {
        if (s.equals("^")) {
            return 1;
        }
        if (s.equals("*") || s.equals("/")) {
            return 2;
        }
        if (s.equals("+") || s.equals("-")) {
            return 3;
        }
        return Integer.MAX_VALUE;            // для открывающей скобки (
    }

    protected static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
