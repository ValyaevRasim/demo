import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JM {

    public static void main(String[] args) {
        try {
            Scanner param = new Scanner(System.in);
            String inputString = param.nextLine();
            // определяем символ
            String smbl = type_symbol.symbol(inputString);

            //создаем массив из данных. split разделитель - smbl
            if (smbl == null) {
                smbl = "\\" + smbl;
//                System.out.println("Нет оператора");
                System.exit(0);
            }
            String[] splitResult = inputString.split(smbl);
//            System.out.println(Arrays.toString(splitResult));

            parameter data = new parameter();
            data.symbol = smbl.replace("\\", "");
            String result;

            // определяем тип цифр (арабские(int) или римские(String))
            if (isNumeric(splitResult[0]) && isNumeric(splitResult[1])) {
                // арабские
                data.param1 = Integer.parseInt(splitResult[0]);
                data.param2 = Integer.parseInt(splitResult[1]);
                result = data.getResult();
                System.out.println(result);
            } else {
                // переводим римские в арабские
                data.param1 = roman_to_arab.transform_roman_to_arabic(splitResult[0]);
                data.param2 = roman_to_arab.transform_roman_to_arabic(splitResult[1]);
                String sum = data.getResult();

                // переводим арабские в римские
                if (sum != null) {
                    int number_input = Integer.parseInt(sum);
                    result = arab_to_roman.transform_arab_to_roman(number_input);
                    System.out.println(result);
                }

            }
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
        }
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.matches("[0-9][\\.,][0-9]")) {
            System.out.println("Введите цело число(арабскими) или римскими");
            System.exit(0);
            return false;
        } else if (str.matches("[0-9]")) {
            return true;
        }
        return false;
    }
}


class parameter{
    String symbol; //   *-+/
    int param1;
    int param2;

    String getResult(){

//        System.out.println(param1.getClass().getSimpleName());

        if (param1 > 10 || param2 > 10){
            System.out.println("числа должны быть в интервале от 1 до 10");
        } else {
            switch (String.valueOf(symbol)){
                case "*":
                    return String.valueOf(param1 * param2);
                case "/":
                    return String.valueOf(param1 / param2);
                case "+":
                    return String.valueOf(param1 + param2);
                case "-":
                    return String.valueOf(param1 - param2);
            }
        }
        return null;
    }
}


class type_symbol{

    public static String symbol(String str) {
        Pattern pattern = Pattern.compile("(\\+|\\*|/|-)");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
//            System.out.println(String.valueOf(matcher.group(0)));
            return matcher.group(0);
        } else {
            System.out.println(String.valueOf("допускаемые арифметические операции: + * / -"));
            return null;
        }
    }

}


class arab_to_roman{
    public static String transform_arab_to_roman(int number) {
        //https://coding.tools/ru/numbers-to-roman-numerals
        int[] roman_value_list = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman_char_list = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < roman_value_list.length; i += 1) {
            while (number >= roman_value_list[i]){
                number -= roman_value_list[i];
                res.append(roman_char_list[i]);
            }
        }
        return res.toString();
    }
}


class roman_to_arab {
//    https://coderoad.ru/26488606/Java-преобразование-римских-цифр
    static int value(char a) {
        if (a=='m') return 1000;
        else if (a=='d') return 500;
        else if (a=='c') return 100;
        else if (a=='l') return 50;
        else if (a=='x') return 10;
        else if (a=='v') return 5;
        else if (a=='i') return 1;
        else return 0;
    }

    public static int transform_roman_to_arabic(String roman) {
        roman=roman.toLowerCase();
        int val;
        int val_next;
        int temp;
        int result=0;

        for (int i=0;i<roman.length();i++) {
            try{
                val=value(roman.charAt(i));
                if (i<roman.length()-1) {
                    val_next=value(roman.charAt(i+1));
                } else val_next=0;

                if (val_next==0) {
                    temp=val;
                } else {
                    if (val>val_next) temp=val;
                    else if (val<val_next) {
                        temp=val_next-val;
                        i++;
//                    } else if (val==val_next) temp=val;
                    } else temp=val;
                }
                result+=temp;
            }
            catch(Exception e) {
                System.out.println("ERROR:transform_roman_to_arabic "  + e);
            }

        }
//        System.out.println("Result is: " + result);
        return result;
    }
}