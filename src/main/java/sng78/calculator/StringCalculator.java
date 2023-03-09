package sng78.calculator;

import java.util.List;

public class StringCalculator {
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
}
