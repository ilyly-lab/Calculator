import java.util.HashMap;

class Converter{

    public static int convertToArabic(String number) {
        int result = 0;

        HashMap<Character,Integer> map = new HashMap<>();   // Мне нужен был аналог словарей из Python; оказалось, что есть
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        // алгоритм перевода в арабские
        for (int i = 0; i < number.length(); i++) {   // Если символ последний или он меньше следующего -- прибавляем арабский эквивалент этого символа
            int val = map.get(number.charAt(i));
            if (i == number.length() - 1 || map.get(number.charAt(i+1)) <= map.get(number.charAt(i))) result += val;
            else result -= val;   // иначе -- отнимаем. Например, в случае с IV мы отнимем 1 (I)
        }
        return result;
    }

    public static String convertToRoman(String num) throws IndexOutOfBoundsException {

        int number = Integer.parseInt(num);   // Для реализации обратного алгоритма мне понадобилось перевести входящую строку в число
        if (number < 1) throw new IndexOutOfBoundsException("The answer is not in range [1, 100]"); // Проверка на корректность ответа

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");   // Все "контрольные точки"
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");

        int[] keys = {100, 90, 50, 40, 10, 9, 5, 4, 1};   // Можно было сам HashMap перевести в Array или List, но не стал грузить программу
        // А здесь стал... почему-то обратный перевод дался мне труднее
        StringBuilder result = new StringBuilder();
        for (int i : keys) {    // Для каждого ключа в Мар
            while (number >= i) {   // Пока число больше данной "Контрольной точки"
                number -= i;            // Отнимаем от него ключ
                result.append(map.get(i));   // А к результату присоединяем полученное по данному ключу значение
            }
        }
        return result.toString();
    }
}