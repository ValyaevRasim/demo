/*import java.util.Arrays;
import java.util.Scanner;*/
import java.util.regex.*;

public class JM {
    public static void main(String[] args) {
        String inputString = "IX*V";

        try {
            Object smbl = symbol(inputString);
            //param.symbol = "1";
            System.out.println("symbol " + smbl);

//            System.out.println(isNumeric(splitResult[0]));
//            System.out.println(isNumeric(splitResult[1]));
//
//            String[] splitResult = inputString.split(ptrn);
//            System.out.println(Arrays.toString(splitResult));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9]+");
    }

    public static Object symbol(String str) {

        Pattern pattern = Pattern.compile("(\\+|\\/|\\*|\\-)");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }
}

class param{
    char symbol; //   *-+/
    int param1;
    int param2;

    String getResult(){
        switch (symbol){
            case '*':
                return String.valueOf(param1 * param2);
            case '/':
                return String.valueOf(param1 / param2);
            case '+':
                return String.valueOf(param1 + param2);
            case '-':
                return String.valueOf(param1 - param2);
        }
        return null;
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
