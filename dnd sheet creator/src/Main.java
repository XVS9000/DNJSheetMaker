import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Referencing all thn/ different classes and creating objects accordingly
        statgen usermod = new statgen();
        Scanner scan = new Scanner(System.in);
        charcreator sheet = new charcreator();

        List<Integer> statsstuff = new ArrayList<>();
        List<Integer> statsstuffreflect = new ArrayList<>();
        List<String> Standstatsstuff = new ArrayList<>();
        List<String> Standstatsstuffreflect = new ArrayList<>();

        //character information
        String name = "";
        String standname = "";
        String standability = "";
        String standtype = "";
        String userfeats = "";
        String standfeats = "";

        //Proficiencies and stand type information.
        String[] ListOfProficiencies = {"Athletics", "Acrobatics", "Sleight of Hand", "Stealth", "Investigation", "Aura detection", "Insight", "Medicine", "Perception", "Survival", "Deception", "Intimidation", "Performance", "Persuasion"};
        String[] Proficiencies = {"", "", "", ""};
        String[] standtypelist = {"Close ranged", "Long ranged", "Automatic", "Colony", "Bound"};
        String[] standtypedesc = {"+ 4 to rolls involving Destructive Power or Developmental Potential, +2 to Stand AC, +2 to Barrage damage, You may add half of your Destructive Power modifier to Developmental Potential rolls, or vice-versa (all numbers rounded down). Cannot be more than a C in range.",
                "+4 to rolls involving Precision or Developmental Potential, +20 meters of range, !Ability to see through and remotely control your Stand. !You may add half of your Precision modifier to Developmental Potential rolls, or vice-versa (all numbers rounded down) Stand takes twice as much damage if hit while stand is out of your line of sight, or at 75% of its max range.",
                "+ 6 to rolls involving Developmental Potential, +3 to all other Stand Stats, Damage done to Stand does not reflect to the user (unless stated otherwise), If the Stand is destroyed, it can return in 4 turns as long as the trigger is activated, Stand has 1/3rd of the health it would have otherwise, User cannot directly control Stand, teammates can be affected, Ability MUST have incredibly specific activation circumstances (up to DM)",
                "+ Can attack twice by default, When damage is taken by your stand, it deals a fraction of your health per unit destroyed,-2 to Stand AC,Damage die lowered by a stage (A in power goes from d8 to d6, etc. Minimum is a d4, Cannot use the Stand Defend action",
                "+ You are proficient with the item your Bound Stand forms. Add 2 times your modifier in Power/Precision/Speed to your rolls made to hit with your Bound Stand, with a max of +6. When rolling to use a stand ability, you can choose to use Power or Precision instead of Developmental Potential. You can pick one extra feat during character creation. Cannot use the Stand Rush action. Some Bound stands cannot take the Stand Defense option, depending on the item."};


        boolean statsetrepeat = true; //The repeater for certain parts of the program/loops
        boolean statsetrepeat2 = true;

        String[] UserStatNames = {"Strength", "Constitution", "Dexterity", "Intelligence", "Wisdom", "Charisma"};
        String[] StandStatNames = {"Destructive Power", "Speed", "Range", "Durability", "Precision", "Developmental Potential"};

        //Character stats/character mods
        Integer[] Statlist = {0, 0, 0, 0, 0, 0};
        Integer[] Statmodlist = {0, 0, 0, 0, 0, 0};
        String[] StandStatList = {"", "", "", "", "", ""};
        Integer[] StandStatModList = {0, 0, 0, 0, 0, 0};

        String ans1 = "";
        String CharacterSheetCreation = "";
        int rollcount = 0;
        String[] ValidInps = {"yes", "y", "no", "n"};
        int rollinitial = 0;
        int index = 0;


        //Starting the actual program
        print("What parts of the character sheet would you like to create? Respond with [Stand, User, or Both]. If a valid input is not detected, your answer is defaulted to 'both'.");
        CharacterSheetCreation = scan.nextLine().toLowerCase();
        if (CharacterSheetCreation.indexOf("stand") == -1 && CharacterSheetCreation.indexOf("both") == -1 && CharacterSheetCreation.indexOf("user") == -1) {
            print("Valid input not detected. Program will set both the user sheet and the stand sheet.");
            CharacterSheetCreation = "both";
        }

        if (CharacterSheetCreation.equals("user") || CharacterSheetCreation.equals("both")) {
            print("Please input user name.");
            String ans = scan.nextLine();
            name = inputCheck(ans, ValidInps, false, "N/A");
            print("Would you like to roll from scratch or use pre-existing rolls? (Defaulted to 'from scratch' if answer is not valid. Input 'pre-existing' or 'pe' to use an existing roll.)");
            String ans4 = scan.nextLine();
            switch (ans4) {
                case "pre-existing":
                    statsetrepeat = false;
                    rollinitial = 3;
                    break;
                case "pe":
                    rollinitial = 3;
                    statsetrepeat = false;
                    break;
            }

            if (statsetrepeat) {
                print("Your answer has been defaulted to 'scratch'.");
                print("How many rolls are you allowed to have by your DM?");
                String ans5 = scan.nextLine();
                if (isNum(ans5)) {
                    if (toNum(ans5) <= 0){
                        while (toNum(ans5) <= 0) {
                            print("You can't have 0 rolls idiot. Do it again.");
                            ans5 = scan.nextLine();
                        }
                    }
                    rollcount = toNum(ans5);
                    rollinitial = toNum(ans5);
                } else {
                    while (!isNum(ans5)) {
                        print("Compiler exception occurred. Please enter a valid number.");
                        ans5 = scan.nextLine();
                    }
                    if (toNum(ans5) <= 0){
                        while (toNum(ans5) <= 0) {
                            print("You can't have 0 rolls idiot. Do it again.");
                            ans5 = scan.nextLine();
                        }
                    }
                    rollcount = toNum(ans5);
                    rollinitial = toNum(ans5);
                }
                QualityOfLifeMethods[] alluserrolls = new QualityOfLifeMethods[rollcount];
                //Creating a loop for rolls.
                statsstuff = roller(alluserrolls, "User", index, rollcount, statsetrepeat, statsstuff);
            } else {
                for (int i = 0; i < Statlist.length; i++) {
                    print("What was your previous roll? (Please input one number at a time. Current numbers listed are: " + statsstuff + ").");
                    String ans5 = scan.nextLine();
                    if (isNum(ans5)) {
                        statsstuff.add(toNum(ans5));
                        print("Number added.");
                    } else {
                        while (!isNum(ans5)) {
                            print("Compiler exception occurred. Please enter a valid number.");
                            ans5 = scan.nextLine();
                        }
                        statsstuff.add(toNum(ans5));
                        print("Number added");
                    }
                }
                rollcount = 1;
            }


            //Setting all of the character stats now.
            statsetrepeat = true;
            while (statsetrepeat) {
                for (int i = 0; i < statsstuff.size(); i++) {
                    statsstuffreflect.add(statsstuff.get(i)); //This resets statsstuffreflect.
                }

                List previousstat = new ArrayList();//List of previous stats selected.
                int prevselectedstatindex = 0;

                for (int i = 0; i < 6; i++) {
                    print("Set " + UserStatNames[i] + " stat: (You have these rolls left to use: " + statsstuffreflect);
                    print("If you'd like to go back and undo a stat, say 'back'.");
                    String statprompt = scan.nextLine();

                    if (statprompt.equalsIgnoreCase("back") && i == 0) {
                        //Checking to see if when you say back you aren't already at the beginning of your stat list
                        print("You cannot go back any farther in the list.");
                        i -= 1;
                    } else if (statprompt.equalsIgnoreCase("back")) {
                        //Going back to the stat before so that it can be reset.
                        i -= 2;
                        System.out.println(previousstat);
                        //Inserts the previous roll into the list of selectable rolls at its original index.
                        statsstuffreflect.add(prevselectedstatindex, (Integer) previousstat.get(previousstat.size() - 1));
                        previousstat.remove(previousstat.size() - 1);
                    } else {
                        if (isNum(statprompt)) {

                        } else {
                            while (!isNum(statprompt)) {
                                print("Compiler exception occurred. Please enter a valid number.");
                                statprompt = scan.nextLine();
                            }
                        }
                        //Getting both the previous roll index and the new stat from the statsetter command
                        int[] UserRollerCheckerInfo = UserRollChecker(Statlist[i], UserStatNames[i], statsstuffreflect, statprompt);
                        if (UserRollerCheckerInfo[1] == -1) {
                            i -= 1;
                        } else {
                            prevselectedstatindex = UserRollerCheckerInfo[0];
                            Statlist[i] = UserRollerCheckerInfo[1];
                            previousstat.add(Statlist[i]); // Adding this to the previous stats list so that you can go back if need be.
                            Statmodlist[i] = usermod.modcheck(Statlist[i]); // Modifier list being calculated.
                        }
                    }
                }
                print("\n");
                System.out.println("Your stats are now set accordingly. ");
                for (int i = 0; i < Statlist.length; i++) {
                    print(UserStatNames[i] + ": [" + Statlist[i] + "]");
                }

                System.out.println("Are you happy with this? Saying no will restart the process of setting your stats. Your roll stays the same.");
                String happy = scan.nextLine();
                statsetrepeat = false;

                switch (happy.toLowerCase().indexOf("y")) {
                    case -1:
                        statsetrepeat = true;
                        break;
                }
            }

            statsetrepeat = true;
            while (statsetrepeat) {
                System.out.println("List of possible proficiencies: ");
                for (String listOfProficiency : ListOfProficiencies) {
                    System.out.println("   " + listOfProficiency);
                }

                for (int i = 1; i <= 4; i++) {
                    print("Input proficiency " + i + ":");
                    String ans3 = scan.nextLine().toLowerCase();

                    for (int x = 0; x < ListOfProficiencies.length; x++) {
                        if (ans3.equalsIgnoreCase(ListOfProficiencies[x])) {
                            print("Proficiency set.");
                            Proficiencies[i - 1] = ans3;
                            break;
                        } else if (x == ListOfProficiencies.length - 1 && !(ans3.equalsIgnoreCase(ListOfProficiencies[x]))) {
                            print("Please input a valid proficiency.");
                            i -= 1;
                        }
                    }
                }

                print("Please input user feats. (This will not be calculated here and is simply for documentational purposes. If you would like to set user feats later or are unsure what this means, say N/A.)");
                userfeats = scan.nextLine();

                sheet.charactersheet(name, Statlist, Statmodlist, Proficiencies, userfeats);
                System.out.println("Are you happy with this? Saying no will allow you to go back and change each of your proficiencies. ");
                String happy = scan.nextLine();

                statsetrepeat = false;

                switch (happy.toLowerCase().indexOf("y")) {
                    case -1:
                        statsetrepeat = true;
                        break;
                }
            }
        }

        if(CharacterSheetCreation.equals("stand") || CharacterSheetCreation.equals("both")) {

            //Switch over to stand setting information.
            System.out.println("Input stand name: ");
            standname = scan.nextLine();
            standname = inputCheck(standname, ValidInps, false, "N/A");

            System.out.println("Describe your stand's ability.");
            standability = scan.nextLine();
            standability = inputCheck(standability, ValidInps, false, "farting");


            print("Would you like to roll from scratch or use pre-existing rolls? (Defaulted to 'from scratch' if answer is not valid. Input 'pre-existing' or 'pe' to use an existing roll.)");
            String ans237 = scan.nextLine();
            statsetrepeat = true;
            switch (ans237) {
                case "pre-existing":
                    statsetrepeat = false;
                    break;
                case "pe":
                    statsetrepeat = false;
                    break;
            }

            if (statsetrepeat) {
                print("Your answer has been defaulted to 'scratch'.");
                if(rollinitial == 0){
                    print("How many rolls are you allowed to have by your DM?");
                    String ans5 = scan.nextLine();
                    if (isNum(ans5)) {
                        if (toNum(ans5) <= 0){
                            while (toNum(ans5) <= 0) {
                                print("You can't have 0 rolls idiot. Do it again.");
                                ans5 = scan.nextLine();
                            }
                        }
                        System.out.println(isNum(ans5));
                        rollcount = toNum(ans5);
                        rollinitial = toNum(ans5);
                    } else {
                        while (!isNum(ans5)) {
                            print("Compiler exception occurred. Please enter a valid number.");
                            ans5 = scan.nextLine();
                        }
                        if (toNum(ans5) <= 0){
                            while (toNum(ans5) <= 0) {
                                print("You can't have 0 rolls idiot. Do it again.");
                                ans5 = scan.nextLine();
                            }
                        }
                        rollcount = toNum(ans5);
                        rollinitial = toNum(ans5);
                    }
                }else {
                    rollcount = rollinitial;
                }
                statsetrepeat = true;
                QualityOfLifeMethods[] allstandrolls = new QualityOfLifeMethods[rollcount];
                index = 0;
                Standstatsstuff = roller(allstandrolls, "Stand", index, rollcount, statsetrepeat, Standstatsstuff);
            } else {
                for (int i = 0; i < Statlist.length; i++) {
                    print("What was your previous roll? (Please input one letter at a time. Current letters listed are: " + Standstatsstuff + ").");
                    String ans5 = scan.nextLine();
                    if (isLetter(ans5)) {
                        Standstatsstuff.add(ans5.toUpperCase());
                        print("Letter added.");
                    } else {
                        while (!isLetter(ans5)) {
                            print("Compiler exception occurred. Please enter a valid letter.");
                            ans5 = scan.nextLine();
                        }
                        Standstatsstuff.add(ans5);
                        print("Letter added.");
                    }
                }
            }

            //Stand stat roller

            statsetrepeat = true;
            boolean statsetrep3 = true;
            //Stand stat setter
            while (statsetrepeat) {
                while (statsetrepeat2) {
                    for (int i = 0; i < Standstatsstuff.toArray().length; i++) {
                        Standstatsstuffreflect.add(Standstatsstuff.get(i));
                    }

                    List previousstat = new ArrayList();//List of previous stats selected.
                    int prevselectedstatindex = 0;

                    for (int i = 0; i < 6; i++) {
                        print("Set " + StandStatNames[i] + " stat: (You have these rolls left to use: " + Standstatsstuffreflect);
                        print("If you'd like to go back and undo a stat, say 'back'.");
                        String statprompt = scan.nextLine();

                        if (statprompt.equalsIgnoreCase("back") && i == 0) {
                            //Checking to see if when you say back you aren't already at the beginning of your stat list
                            print("You cannot go back any farther in the list.");
                            i -= 1;
                        } else if (statprompt.equalsIgnoreCase("back")) {
                            //Going back to the stat before so that it can be reset.
                            i -= 2;
                            System.out.println(previousstat);
                            //Inserts the previous roll into the list of selectable rolls at its original index.
                            Standstatsstuffreflect.add(prevselectedstatindex, previousstat.get(previousstat.size() - 1).toString());
                            previousstat.remove(previousstat.size() - 1);
                        } else {
                            if (isLetter(statprompt)) {

                            } else {
                                while (!isLetter(statprompt)) {
                                    print("Compiler exception occurred. Please enter a valid letter.");
                                    statprompt = scan.nextLine();
                                }
                            }
                            //Getting both the previous roll index and the new stat from the statsetter command
                            List StandRollerCheckerInfo = StandRollChecker(StandStatList[i], StandStatNames[i], Standstatsstuffreflect, statprompt);
                            if (StandRollerCheckerInfo.get(1).equals("")) {
                                i -= 1;
                            } else {
                                prevselectedstatindex = (Integer) StandRollerCheckerInfo.get(0);
                                StandStatList[i] = StandRollerCheckerInfo.get(1).toString();
                                previousstat.add(StandStatList[i]); // Adding this to the previous stats list so that you can go back if need be.
                                StandStatModList[i] = usermod.modcheck2(StandStatList[i],StandStatNames[i]); // Modifier list being calculated.
                            }
                        }
                    }
                    print("\n");
                    System.out.println("Your stats are now set accordingly. ");
                    for (int i = 0; i < Statlist.length; i++) {
                        print(StandStatNames[i] + ": [" + StandStatList[i] + "]");
                    }
                    System.out.println("Are you happy with this?");
                    String happy = scan.nextLine();

                    statsetrepeat2 = !happy.contains("y");
                    statsetrep3 = true;
                }

                //Stand extra info setter
                while (statsetrep3) {
                    print("List of stand types (for more information about a certain stand type, say '?[standtypename])'.");
                    for (String listcycle : standtypelist) {
                        print(listcycle);
                    }

                    print("Input stand type: (Information on how stats are changed according to stand type can be found by saying '?[standtypename]').");
                    standtype = scan.nextLine().toLowerCase();

                    if (standtype.contains("?")) {
                        for (int i = 0; i < standtypelist.length; i++) {
                            if (standtype.contains(standtypelist[i].toLowerCase())) {
                                print(standtypelist[i].toUpperCase() + ":" + "\n" + standtypedesc[i]);
                                break;
                            }else{
                                print("Stand type not found.");
                            }
                        }
                    } else {
                        for(int i = 0; i < standtypelist.length; i++){
                            if ((standtype.equalsIgnoreCase("close ranged")) && ((StandStatList[2].equalsIgnoreCase("A")) || (StandStatList[2].equalsIgnoreCase("B")))) {
                                print("I'm sorry. You cannot have a close-ranged stand with an a/b in range, as they can only have a C or lower. You can either change your stats or change your stand type. Say 1 to change your stats, and say 2 to change your type.");
                                String ans = scan.nextLine();
                                if (ans.equalsIgnoreCase("1")){
                                    statsetrepeat2 = true;
                                    statsetrep3 = false;
                                    break;
                                }else if(ans.equalsIgnoreCase(
                                        "2")){
                                    statsetrepeat2 = false;
                                    statsetrep3 = true;
                                    break;
                                }else{
                                    print("Please enter a valid response.");
                                }
                            } else if (i == standtypelist.length-1 && !(standtype.equalsIgnoreCase(standtypelist[i]))){
                                print("Please input a valid response.");
                                statsetrep3 = true;
                                statsetrepeat = true;
                                break;
                            }else if (standtype.equalsIgnoreCase(standtypelist[i])){
                                standtype = standtypelist[i];
                                statsetrep3 = false;
                                statsetrepeat2 = false;
                                statsetrepeat = false;
                                break;
                            }

                        }
                    }
                }
            }

            print("Please input stand feats. (This will not be calculated here and is simply for documentational purposes. If you would like to set stand feats independently or are unsure what this means, say N/A.)");
            standfeats = scan.nextLine();
            if (!CharacterSheetCreation.equals("both")){
                sheet.standGenStats(Statlist,StandStatModList,standtype,Statmodlist);
                sheet.standcharsheet(standname,StandStatList,StandStatModList, standfeats,standability);
            }
        }


        //proficiencies, finalizing character sheet

        if(CharacterSheetCreation.equals("both")){
            sheet.standGenStats(Statlist,StandStatModList,standtype,Statmodlist);
            sheet.charactersheet(name, Statlist,Statmodlist, Proficiencies, userfeats);
            sheet.standcharsheet(standname,StandStatList,StandStatModList, standfeats,standability);
        }
    }

    public static void print(String inp){
        System.out.println(inp);
    }
    //Checking to see if the number is an actual number.
    public static boolean isNum(String ans){
        boolean isNum;
        try{
            Integer.parseInt(ans);
            isNum = true;
        }catch(Exception e){
            isNum = false;
        }
        return (isNum);
    }
    public static boolean isLetter(String ans){
        boolean isLet = false;
        String[] Alphabet = {"A","B","C","D","E"};
        for(int i = 0; i < Alphabet.length; i++){
            if(ans.equalsIgnoreCase(Alphabet[i])){
                isLet = true;
                break;
            }
        }
        return (isLet);
    }
    public static int[] UserRollChecker (int stat, String statstr, List<Integer> l2, String ans) {
        /* stat = the stat being changed
        * statstr = the name of the stat (strength, const, etc.)
        * l2 = list of selectable rolls
        * ans = user input
        */
        int prevstatindex = 0; // Index of previous stat
        int statselect = toNum(ans); //User input for roll.
        //Looks through all selectable rolls in l2.
        for (int i = 0; i < l2.toArray().length; i++){
            if (i == l2.toArray().length-1 && (statselect != l2.get(i))){
                //Remove this.
                print("You have either already used this roll or it does not exist. Please enter a valid number.");
                stat = -1;
                break;
            }else if (statselect == (Integer)l2.toArray()[i]){
                //Grabs current roll index (For where it's going to be reinserted when going back) and changing the stat.
                prevstatindex = i;
                stat = statselect;
                l2.remove(i);
                print(statstr + " is now: " + stat);
                break;
            }
        }
        //This returns both the previous stat index and the new stat; the program will determine which one it wants to use.
        int[] list = {prevstatindex, stat};
        return list;
    }
    public static List StandRollChecker (String stat, String statstr, List<String> l2, String ans) {
        /* stat = the stat being changed
         * statstr = the name of the stat (strength, const, etc.)
         * l2 = list of selectable rolls
         * ans = user input
         */
        int prevstatindex = 0; // Index of previous stat
        String statselect = ans.toUpperCase(); //User input for roll.
        //Looks through all selectable rolls in l2.
        for (int i = 0; i < l2.toArray().length; i++){
            if (i == l2.toArray().length-1 && (!statselect.equalsIgnoreCase(l2.get(i)))){
                //Remove this.
                print("You have either already used this roll or it does not exist. Please enter a valid stat.");
                stat = "";
                break;
            }else if (statselect.equalsIgnoreCase(l2.toArray()[i].toString())){
                //Grabs current roll index (For where it's going to be reinserted when going back) and changing the stat.
                prevstatindex = i;
                stat = statselect;
                l2.remove(i);
                print(statstr + " is now: " + statselect);
                break;
            }
        }
        //This returns both the previous stat index and the new stat; the program will determine which one it wants to use.
        List list = new ArrayList();
        list.add(prevstatindex);
        list.add(stat);
        return list;
    }

    public static String Standstatsetter (String stat, String statstr, List<String> l2, String ans) {
        /*stat = Letter for Stand Stat
        statstr = What stat is being set
        l2 = List of selectable rolls.
        ans = User input.
        */

        String statselect = ans.toUpperCase();
        for (int i = 0; i < l2.toArray().length; i++){
            //Checking to see if the roll actually exists.
            if (i == l2.toArray().length-1 && !(statselect.equals(l2.get(i)))){
                print("You have either already used this roll or it does not exist. For now, the stat will be set to nothing. You can go back and change it at the end of this loop.");
                stat = "";
            }

            //This removes the selected roll from the list of selectable rolls.
            if (statselect.equals(l2.get(i))){
                stat = statselect;
                l2.remove(i);
                break;
            }
        }
        print(statstr + " is now: " + stat);
        return stat;
    }
    public static int toNum (String inp){
        return(Integer.parseInt(inp));
    }
    public static List roller(QualityOfLifeMethods[] poopy, String UorS, int index, int rollcount, boolean statrep, List stattranslate
    ){
        Scanner scan = new Scanner(System.in);

            while (statrep && rollcount > 0) {
                QualityOfLifeMethods m = new QualityOfLifeMethods(UorS);
                poopy[index] = m;
                System.out.println(poopy[index].statobj);

                print("reroll?");
                String answer = scan.nextLine();
                rollcount -= 1;
                index++;

                statrep = !answer.equalsIgnoreCase("no");
            }
            if ((rollcount == 0) && (statrep)){
                print("You have run out of rolls. Moving on to stat setting.");

                print("Select which roll you want (From 1 to " + poopy.length + ").");
                for (int i = 0; i < poopy.length; i++){
                    if (poopy[i] == null){
                        break;
                    }
                    print("Roll " + (i + 1) + ": ");
                    System.out.println(poopy[i].statobj);
                }
                String inp = scan.nextLine();
                    if ((isNum(inp)) && (toNum(inp) <= index) && (toNum(inp) > 0)){
                        index = toNum(inp)-1;
                    }else{
                        while (!isNum(inp) || toNum(inp) > index || toNum(inp) <= 0){
                            print("Please input a valid number in between the amount of rolls you have.");
                            inp = scan.nextLine();

                        }
                        index = toNum(inp)-1;
                    }
                stattranslate = poopy[index].statobj;

            }else if (!statrep){
                print("Select which roll you want (From 1 to " + index + ").");
                for (int i = 0; i < poopy.length; i++){
                    if (poopy[i] == null){
                        break;
                    }
                    print("Roll " + (i + 1) + ": ");
                    System.out.println(poopy[i].statobj);
                }

                String inp = scan.nextLine();
                if (isNum(inp) && toNum(inp) <= index && toNum(inp) > 0){
                    index = (toNum(inp))-1;
                }else{
                    while (!isNum(inp) || toNum(inp) > index || toNum(inp) <= 0){
                        print("Please input a valid number in between the amount of rolls you have.");
                        inp = scan.nextLine();
                    }
                    index = (toNum(inp))-1;
                }

                stattranslate = poopy[index].statobj;
            }
            return(stattranslate);
    }
    public static String inputCheck (String inp, String[] validInputs, boolean NeedsValidInputs, String defaultVal){
        String response = inp;
        for(int i = 0; i < validInputs.length; i++){
            if (NeedsValidInputs && !inp.equals("") && validInputs[i].indexOf(response) != -1){
                response = inp;
                break;
            }else if (!NeedsValidInputs && !(response.equals(""))){
                response = inp;
                break;
            }else if (i == validInputs.length-1){
                response = defaultVal;
                System.out.println("No valid input was detected. Setting has been defaulted to " + response + ".");
                return (response);
            }
        }
        System.out.println("Setting had been set to '" + response + "'.");
        return (response);
    }
}