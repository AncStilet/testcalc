package testcalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class calc {
    public static int romanToInt(String s) {                                       // римские на арабские
        Map<Character, Integer> romanValues = new HashMap<>();
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);

        int result = 0;
        int prevValue = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = romanValues.get(s.charAt(i));
            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            prevValue = currentValue;
        }

        return result;
    }

    public static void main(String[] args) {                        // разделение строку на отдельные символы
        try {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            String[] saved = {"", "", ""};
            int j = 0;                                                  // счетчик
            for (int i = 0; i < input.length(); i++) {
                char symbol = input.charAt(i);
                if (symbol != ' ') {
                    saved[j] = saved[j] + symbol;
                } else {
                    j++;
                }
            }

            char c1 = saved[0].charAt(0);
            char c2 = saved[2].charAt(0);

            int number_1;
            int number_2;
            char symbol;
            if (c1 >= '0' && c1 <= '9' && c2 >= '0' && c2 <= '9') {
                number_1 = Integer.parseInt(saved[0]);
                number_2 = Integer.parseInt(saved[2]);
                symbol = saved[1].charAt(0);
            } else {
                number_1 = romanToInt(saved[0]);                          // римское число
                number_2 = romanToInt(saved[2]);
                symbol = saved[1].charAt(0);
            }

            int sum;
            if (symbol != '+' && symbol != '-' && symbol != '*' && symbol != '/') throw new Exception();
            if (number_1 > 10 || number_2 > 10 || number_1 <= 0 || number_2 <= 0) throw new Exception();
            if (symbol == '+') {                                          // калькулятор
                sum = number_1 + number_2;
            } else if (symbol == '-') {
                sum = number_1 - number_2;
            } else if (symbol == '*') {
                sum = number_1 * number_2;
            } else {
                sum = number_1 / number_2;
            }

            if (c1 >= '0' && c1 <= '9' && c2 >= '0' && c2 <= '9') {        //результат
                System.out.println(sum);
            } else {
                if (sum <= 0)
                    throw new Exception();
                int number = sum;
                System.out.println(intToRoman(number));


            }
        }catch (Exception e){
            System.out.println("Исключение");
        }
    }

    public static String intToRoman(int num){                         // арабские в римские
        int[] values = {
                1000, 900, 500, 400,
                100, 90, 50, 40,
                10, 9, 5, 4, 1
        };
        String[] symbol = {
                "M", "CM", "D", "CD",
                "C", "XC", "L", "XL",
                "X", "IX", "V", "IV",
                "I"
        };

        StringBuilder romanNumeral = new StringBuilder();

        for (int i = 0; i < values.length; i++){
            while (num >= values[i]){
                num = num - values[i];
                romanNumeral.append(symbol[i]);
            }
        }
        return romanNumeral.toString();
    }
}