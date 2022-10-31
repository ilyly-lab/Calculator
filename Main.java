import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main{

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine().toUpperCase();   // Получаем строку и переводим в верхний регистр

        Pattern patternRoman = Pattern.compile("[IVX]");
        Pattern patternOperand = Pattern.compile("[*/+-]");
        Pattern patternArabic = Pattern.compile("\\d");

        Matcher matcherArabic = patternArabic.matcher(in);
        Matcher matcherOperand = patternOperand.matcher(in);
        Matcher matcherRoman = patternRoman.matcher(in);

        if (matcherArabic.find() && matcherRoman.find() || !matcherOperand.find()) {    // Проверка на корректность ввода
            throw new IOException("Incorrect input");
        }
        System.out.println(calc(in));
    }

    public static String calc(String input) throws IOException {
        String[] exp = input.split(" ");    // Разбиение на токены по пробелу, должно получиться 3
        if (exp.length != 3) throw new IOException("Incorrect input");    // Проверка на соответствие формату "число знак число"

        int[] expression = check(exp);    // Вызов метода для проверки системы исчисления
        String answer = "";

        switch (exp[1]) {   // Проверка знака и выполнение соответствующего действия
            case "+" -> answer = String.valueOf(expression[0] + expression[1]);
            case "-" -> answer = String.valueOf(expression[0] - expression[1]);
            case "*" -> answer = String.valueOf(expression[0] * expression[1]);
            case "/" -> answer = String.valueOf(expression[0] / expression[1]);
            default -> {
            }    // Оказывается, default нужен в любом случае, согласно Java Code Convention...
        }
        if (exp[0].matches("\\d+")) return answer;    // Проверка, чтобы выдать ответ в той системе исчисления, в которой было получено выражение
        else return Converter.convertToRoman(answer);
    }

    public static int[] check(String[] arr) throws IOException {
        if (arr[0].matches("\\d+")) {   // Проверка на систему исчисления. В любом случае вернем массив из двух int
            int num1 = Integer.parseInt(arr[0]);
            int num2 = Integer.parseInt(arr[2]);
            // Проверка на корректность самого числа
            if ((1 <= num1 && num1 <= 10) && (1 <= num2 && num2 <= 10)) return new int[] {num1, num2};
            else throw new IOException("Out of range [1, 10]");

        } else
            return new int[] {Converter.convertToArabic(arr[0]), Converter.convertToArabic(arr[2])};   // Но если числа римские, то сначала переведем
    }
}