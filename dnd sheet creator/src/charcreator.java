import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class charcreator {
    static String[] thestats = {"Strength", "Constitution", "Dexterity", "Intelligence", "Wisdom", "Charisma"};
    static ArrayList<String> Expertise = new ArrayList<>();
    static ArrayList<Integer> tempstore = new ArrayList<>(1);

    static List<Object>StandCalinfo = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);
    static boolean standstatsset = false;
    public void charactersheet(String name, Integer[]statlists, Integer[]statmods, String[] proficiencies, String userfeats) {

        Integer[] Statsfull = statlists;
        Integer[] Modsfull = statmods;
        String[] modifier0 = {"Athletics"};
        String[] modifier1 = {"Acrobatics", "Sleight of Hand", "Stealth"};
        String[] modifier2 = {"Investigation", "Aura detection"};
        String[] modifier3 = {"Insight", "Medicine", "Perception", "Survival"};
        String[] modifier4 = {"Deception", "Intimidation", "Performance", "Persuasion"};

        int armour = 10 + statmods[2];

        if(standstatsset && tempstore.get(0) != null){
            statmods[0] = tempstore.get(0);
        }

        System.out.println("Stand user: " + name);
        System.out.println("User hp: " + (statlists[1] + 15));
        System.out.println("User AC: " + (armour));
        System.out.println();
        System.out.println("User Stats: ");

        forlooparray(modifier0, proficiencies, Statsfull, Modsfull, 0);
        System.out.println(thestats[1] + ": " + Statsfull[1]);
        forlooparray(modifier1, proficiencies, Statsfull, Modsfull, 2);
        forlooparray(modifier2, proficiencies, Statsfull, Modsfull, 3);
        forlooparray(modifier3, proficiencies, Statsfull, Modsfull, 4);
        forlooparray(modifier4, proficiencies, Statsfull, Modsfull, 5);
        System.out.println("User feats: " + userfeats);
    }

    public void standcharsheet(String name, String[] standstats, Integer[] standmods, String standfeats, String standability) {
        String[] standthingies = {"Destructive power: ", "Speed: ", "Range: ", "Durability: ", "Precision: ", "Developmental Potential: "};

        String standtype = "";
        int standhp = 0;
        int standac = 0;
        String symbol = "";
        int barragestats = 0;

            for (int i = 0; i < 6; i++) {
                standmods[i] = (Integer) StandCalinfo.get(i);
            }
            standhp = (Integer) StandCalinfo.get(6);
            standac = (Integer) StandCalinfo.get(7);
            barragestats = (Integer) StandCalinfo.get(8);
            standtype = StandCalinfo.get(9).toString();



            System.out.println("""

                    """);
            System.out.println("Stand name: " + name);
            System.out.println("Stand Type: " + standtype);
            System.out.println("Stand Ability: " + standability + "\n");
            System.out.println("Stand HP: " + (standhp));
            System.out.println("Stand AC: " + standac);
            System.out.println("Stand Stats: ");

            for (int i = 0; i < standstats.length; i++) {
                String thing = "";
                if (standmods[i] >= 0) {
                    thing = "+";
                }else{
                    thing = "";
                }
                System.out.println("   " + standthingies[i] + standstats[i].toUpperCase() + " [" + thing + standmods[i] + "]");
            }

        if (barragestats >= 0){
            symbol = "+";
        }

            System.out.println("\n" + "Barrage Stats: [" + symbol + barragestats + "].");
            System.out.println("Stand feats: " + standfeats);

    }

    //Prints character stats and adds proficiencies accordingly.
    public static void forlooparray(String[] array, String[] proficiencies, Integer[] numbers, Integer[] numbers2, Integer index) {
        //The array parameter represents whatever modifier list i'm running through.
        // The proficiencies parameter represents the player's selected proficiencies.
        // numbers parameter represents all the stats.
        // Numbers2 parameter represents all the modifiers for the stats.
        //Index represents what stat modifier we're running through. Specifically we do this because constitution has no modifiers to add for certain rolls.
        String symbol = "";
        System.out.println(thestats[index] + " " + numbers[index]);

        //Checking to see if given modifier is positive or 0. Otherwise it does nothing.
        if (numbers2[index] >= 0) {
            symbol = "+";
        }

        //So it's going through a list i know that
        //S represents the content of array's(modifier list) index
        //Each proficiency index represents the selected proficiencies of the user. It checks to see if the user has proficiencies and marks/increases them accordingly
        for (String s : array) {
            int addedmod = 2;
            if ((s.toLowerCase().equals(proficiencies[0])) || (s.toLowerCase().equals(proficiencies[1])) || (s.toLowerCase().equals(proficiencies[2])) || (s.toLowerCase().equals(proficiencies[3]))) {
                if (numbers2[index] >= 0) {
                    symbol = "+";
                } // checking to see if modifier is still negative or if it becomes positive after proficiency is added
                System.out.println("  *" + s + " [" + symbol + (numbers2[index] + addedmod) + "]");
            } else {
                System.out.println("   " + s + "  [" + symbol + numbers2[index] + "]");
            }
        }
    }

    public List<Object> standtypecal (String standtype, int standhp, int standac, int barrage, Integer[]StandModifiers, Integer[]statmods){
    List <Object> RedoneMods = new ArrayList<>();

        switch(standtype.toLowerCase()){
            case "close ranged":
                StandModifiers[0] += 4;
                StandModifiers[5] += 4;
                standac += 2;
                barrage += 2;

                System.out.println("Would you like your stand to be a phenomenon?");
                System.out.println("(Please note that expertise will not be documented here. You will have to add that yourself.)");
                String ans = scan.nextLine();

                if (ans.contains("y")){
                    standtype += "-Phenomenon";
                    standac+=1;
                    tempstore.add(StandModifiers[0]);
                    barrage+=1;
                    standhp/=2;
                }else{
                    System.out.println("Your stand is not a phenomenon.");
                }
                break;

            case "long ranged": {
                StandModifiers[4] += 4;
                StandModifiers[5] += 4;
                StandModifiers[2] += 20;

                System.out.println("Would you like your stand to be a phenomenon?");
                String ans1 = scan.nextLine();

                if (ans1.contains("y")){
                    standtype += "-Phenomenon";
                    StandModifiers[0] += statmods[4];
                    StandModifiers[1] += statmods[4];
                    StandModifiers[2] += statmods[4] + 10;
                    StandModifiers[3] += statmods[4];
                    StandModifiers[4] += statmods[4];
                    StandModifiers[5] += statmods[4];

                }else{
                    System.out.println("Your stand is not a phenomenon.");
                }
                break;
            }
            case "automatic": {
                StandModifiers[5] += 6;
                standhp /= 3;

                StandModifiers[0] += 3;
                StandModifiers[1] += 3;
                StandModifiers[2] += 3;
                StandModifiers[3] += 3;
                StandModifiers[4] += 3;
                break;
            }
            case "colony": {
                String special = "";
                String special2 = "";

                String[]SpecialsList = {"Disastrous", "Well-Coordinated","Skittish","Conductive","Abundant"};

                System.out.println("List of specializations: ");
                for (String i : SpecialsList){
                    System.out.println(i);
                }
                System.out.println("\n");

                for (int i = 1; i <= 2; i++){
                    System.out.println("Please input specialization " + i + ": ");
                    if(i == 1){
                        special = scan.nextLine();
                        for (int x = 0; x < SpecialsList.length; x++){
                            if(x == SpecialsList.length-1 && !special.equalsIgnoreCase(SpecialsList[x])){
                                System.out.println("Please input a valid specialization.");
                                i-=1;
                            }else if (special.equalsIgnoreCase(SpecialsList[x])){
                                System.out.println("Your first colony specialization is now: " + special);
                                standtype += "(Specializations: " + SpecialsList[x];
                                break;
                            }
                        }
                    } else if (i == 2){
                        special2 = scan.nextLine();
                        if (special2.equalsIgnoreCase(special)){
                            System.out.println("You cannot have the same specialization as your first.");
                            i-=1;
                        }

                        for (int x = 0; x < SpecialsList.length; x++){
                            if(x == SpecialsList.length-1 && !special2.equalsIgnoreCase(SpecialsList[x])){
                                System.out.println("Please input a valid specialization.");
                                i-=1;
                            }else if(!special2.equalsIgnoreCase(special) && special2.equalsIgnoreCase(SpecialsList[x])){
                                System.out.println("Your second colony specialization is now: " + special2);
                                standtype += ", " + SpecialsList[x];
                                break;
                            }
                        }
                    }

                }
                colcalc(special, special2, StandModifiers, standhp, standac);
                break;
            }case "bound":
                standtype += " (Note: You have the ability to make targeted attacks towards certain body parts. Your rolls to hit are increased by 2x the modifier for Power, Precision, or Speed.)";
            break;
        }

        for(int x : StandModifiers){
            RedoneMods.add(x);
        }
        RedoneMods.add(standhp);
        RedoneMods.add(standac);
        RedoneMods.add(barrage);
        RedoneMods.add(standtype);

        return RedoneMods;
    }
    public static List<Integer> colcalc(String special, String special2, Integer[]Statmods, int ac, int hp){

        ac -= 2;


        switch (special.toLowerCase()) {
            case "disastrous" :
                Statmods[0] += 2;
                break;
            case "well-coordinated":
                Statmods[4] += 2;
                break;
            case "skittish":
                Statmods[1] += 3;
                break;
            case "conductive":
                Statmods[5] += 2;
                break;
            case "abundant":
                hp += 15;
                break;
        }
        switch (special2.toLowerCase()) {
            case "disastrous" -> Statmods[0] += 2;
            case "well-coordinated" -> Statmods[4] += 2;
            case "skittish" -> Statmods[1] += 3;
            case "conductive" -> Statmods[5] += 2;
            case "abundant" -> hp += 15;
        }
        List<Integer> redonemods = new ArrayList<>();
        for (int x : Statmods){
            redonemods.add(x);
        }
        redonemods.add(ac);
        redonemods.add(hp);
        return (redonemods);
    }

    public void standGenStats (Integer[] Standstats, Integer[]StandMods, String Standtype, Integer[]StatMods){
        int standhp = 0;
        int standac = (12 + StandMods[1] + StandMods[3]);
        int barragestats = StandMods[0] + StandMods[1] + StandMods[5];

        //Calculating stand HP.
        if (StandMods[3] >= 2) {
            standhp = (Standstats[1] + 15) * 2;
        } else if (StandMods[3] > 0) {
            standhp = (StandMods[1] + 15) + ((StandMods[1] + 15) / 2);
        } else if (StandMods[3] > -1) {
            standhp = StandMods[1] + 15;
        } else if (StandMods[3] > -2) {
            standhp = (StandMods[1] + 15) - ((StandMods[1] + 15) / 4);
        } else {
            standhp = (StandMods[1] + 15) - ((StandMods[1] + 15) / 2);
        }

        StandCalinfo = standtypecal(Standtype,standhp,standac,barragestats,StandMods,StatMods);
    }
}


