import java.util.ArrayList;
import java.util.List;

public class statgen {

    public List<Integer> statscreate1 () {
        List<Integer> userstatlist = new ArrayList<>();
        for (int i = 0; i < 6; i++) {

            int num1 = (int) (Math.random() * 20 + 1);
            int num2 = (int) (Math.random() * 20 + 1);
            int num3 = (int) (Math.random() * 20 + 1);
            int num4 = (int) (Math.random() * 20 + 1);
            int numfinal = (num1 + num2 + num3 + num4) / 3;

            userstatlist.add(numfinal);
        }
        return (userstatlist);
    }

    public List<String> statscreate2(){
        List<String> standlist = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            String letters = "ABCDE";
            int randomnumber = (int) (Math.random() * 5);
            standlist.add(letters.substring(randomnumber,randomnumber+1));
        }
        return(standlist);
    }

    public int modcheck(int numfinal){
        int mod = 0;
        if (numfinal == 1) {
            mod = -5;
        } else if (numfinal < 4) {
            mod = -4;
        } else if (numfinal < 6) {
            mod = -3;
        } else if (numfinal < 8){
            mod = -2;
        }else if(numfinal < 10){
            mod = -1;
        }else if(numfinal < 12){
            mod = 0;
        }else if(numfinal < 14){
            mod = 1;
        }else if(numfinal < 16){
            mod = 2;
        }else if(numfinal < 18){
            mod = 3;
        }else if(numfinal < 20){
            mod = 4;
        }else if(numfinal < 22){
            mod = 5;
        }else if(numfinal < 24){
            mod = 6;
        }else if(numfinal < 26){
            mod = 7;
        }else
        {
            mod = 8;
        }
        return(mod);
    }

    public int modcheck2(String input, String input2){
        int mod = 0;

        if (input2.equalsIgnoreCase("range")){
            switch (input.toUpperCase()){
                case "A" -> mod = 100;
                case "B" -> mod = 50;
                case "C" -> mod = 10;
                case "D" -> mod = 5;
                case "E" -> mod = 2;
            }
        }else {
            switch (input.toUpperCase()){
                case "A" -> mod = 2;
                case "B" -> mod = 1;
                case "C" -> mod = 0;
                case "D" -> mod = -1;
                case "E" -> mod = -2;
            }
    }
        return(mod);
    }}
