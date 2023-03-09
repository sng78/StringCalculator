package sng78;

import sng78.calculator.Calculator;

class Main {

    public static void main(String[] args) {
        String infix = "5 * ( 2,654 * 3,876 + 4.18 * 6 ) + -10";
        System.out.println("\n" + "Арифметическое выражение: " + infix);
        Calculator calc = new Calculator();
        String res = calc.calculate(infix);
        System.out.println("Результат вычисления: " + res);
    }
}
