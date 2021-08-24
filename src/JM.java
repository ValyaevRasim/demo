import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;


public class JM {
    public static void main1(String[] args) {
        // ввод данных
        Scanner param = new Scanner(System.in);
        String inputString = param.nextLine();

        try {
            // через regex определяем symbol - знак действия
            String smbl = symbol(inputString);
            System.out.println("symbol " + smbl);

            //создаем массив из данных. У split разделитель - symbol
            String[] splitResult = inputString.split(smbl);
            System.out.println(Arrays.toString(splitResult));

            //определяем тип введенных данных (римские или арабские)

            param data = new param();
            data.param1 = Integer.parseInt(splitResult[0]);
            data.param2 = Integer.parseInt(splitResult[1]);
            data.symbol = smbl;

            System.out.println(data.param1);
            System.out.println(data.param2);

            System.out.println(data.getResult());


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String symbol(String str) {

        Pattern pattern = Pattern.compile("(\\+|\\/|\\*|\\-)");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return String.valueOf(matcher.group());
        } else {
            return null;
        }
    }
}


class param{
    String symbol; //   *-+/
    String data1;
    String data2;
    int param1;
    int param2;
/*
    if isNumeric(data1){
        param1 = data1;
    }

    if isNumeric(data2){
        param2 = data2;
    }
*/
    String getResult(){
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
        return null;
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9]+");
    }


}

/*
public static void (){

        Scanner param = new Scanner(System.in);
        String str1 = param.nextLine();
        String str2 = param.nextLine();

        String[] r = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int total;
        int indx1 = Arrays.asList(r).indexOf(str1)+1;
        int indx2 = Arrays.asList(r).indexOf(str2)+1;
        int indexM = str1.indexOf("X");
        System.out.println(indexM);
        System.out.println(indx1);
        System.out.println(indx2);

        if (indx1 % indx2== 0) {
            total = indx1 / indx2;
            System.out.println(r[total-1]);
        } else {
            System.out.println("ERROR");
        }
    }
*/
