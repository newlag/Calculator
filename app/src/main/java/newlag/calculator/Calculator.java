package newlag.calculator;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private List<Double> numbersList = new ArrayList<>();
    private List<Character> symbolsList = new ArrayList<>();

    public double calculate(String input) throws ZeroDivisionException {
        input = input.replaceAll("[,]",".");

        sort(input);

        for (int i = 0; i < symbolsList.size(); i++) {
            double result;
            if (symbolsList.get(i).equals('*') || symbolsList.get(i).equals('÷')) {
                result = solution(symbolsList.get(i), i);
                updateLists(i, result);
                i--;
            }
        }

        for (int i = 0; i < symbolsList.size(); i++) {
            double result;
            if (symbolsList.get(i).equals('+') || symbolsList.get(i).equals('-')) {
                result = solution(symbolsList.get(i), i);
                updateLists(i, result);
                i--;
            }
        }
        return numbersList.get(0);
    }

    private double solution(Character symbol, int i) throws ZeroDivisionException {
        switch (symbol) {
            case '+':
                return numbersList.get(i) + numbersList.get(i + 1);
            case '-':
                return numbersList.get(i) - numbersList.get(i + 1);
            case '*':
                return numbersList.get(i) * numbersList.get(i + 1);
            case '÷':
                if (numbersList.get(i + 1) == 0.0F) {
                    throw new ZeroDivisionException("Попытка деления на ноль.");
                }
                return numbersList.get(i) / numbersList.get(i + 1);
        }
        return 0.0F;
    }

    private void updateLists(int i, double result) {
        symbolsList.remove(i);
        numbersList.remove(i + 1);
        numbersList.set(i, result);
    }

    private boolean isNumber(char c) {
        try {
            Integer.parseInt(String.valueOf(c));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private void sort(String input) { // Раскидывает цифры и символы операций по разным листам
        StringBuilder number = new StringBuilder();
        number.append(input.charAt(0));
        boolean lastCharIsSymbol = false;

        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isNumber(c) || c == '.') {
                number.append(c);
                lastCharIsSymbol = false;
            } else {
                if (number.length() > 0) {
                    numbersList.add(Double.valueOf(number.toString()));
                    number.setLength(0);
                }
                if (lastCharIsSymbol) {
                    number.append(c);
                } else {
                    if(i != (input.length() - 1)) symbolsList.add(c);
                }
                lastCharIsSymbol = true;
            }
        }
        if (number.length() > 0) numbersList.add(Double.valueOf(number.toString()));
    }
}
