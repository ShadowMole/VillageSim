import java.util.ArrayList;
import java.io.*;
public class Driver {

    private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    private static int counter;
    private static int[][] stats;

    public static void main(String[] args){
        boolean replay = true;
        do {
            System.out.println("Hello and welcome to the world simulator!");
            System.out.println("The world simulator is not very sophisticated.");
            printMainMenu();
            boolean next = true;
            int command = -1;
            do {
                try {
                    command = Integer.parseInt(stdin.readLine());
                } catch (IOException ioe) {
                    next = false;
                    System.out.println("Please enter a input.");
                }
            } while (!next);
            replay = chooseMainMenu(command);
        } while(replay);
    }

    public static boolean chooseMainMenu(int command){
        boolean replay;
        switch(command){
            case 1:
                stats = new int[][]{{0,0},{0,0,0},{0,0,0,0,0,0,0},{0,0,0},{0,0,0,0}};
                runControlledSim();
                replay = true;
                break;

            case 2:
                stats = new int[][]{{0,0},{0,0,0},{0,0,0,0,0,0,0},{0,0,0},{0,0,0,0}};
                runUncontrolledSim();
                replay = true;
                break;

            case 3:
                System.out.println("Thank you for playing. Have a good day.");
                replay = false;
                break;

            default:
                System.out.println("That is an invalid option, please try again.");
                replay = true;
                break;
        }
        return replay;
    }

    public static void runControlledSim(){
        System.out.println("Welcome to the controlled simulation!!! \nHere you can controlled how long the simulation runs for. " +
                "\nIn addition you can view detailed statistics on both people and events." +
                "\nEnjoy!!!!! :D");
        ArrayList<Person> people = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            people.add(PersonBuilder.createPerson());
        }
        counter = 0;
        boolean next = true;
        boolean continueSim = true;
        do {
            printControlMenu(people);
            int decision = -1;
            do {
                try {
                    decision = Integer.parseInt(stdin.readLine());
                } catch (IOException ioe) {
                    next = false;
                }
            } while (!next);
            continueSim = chooseControlSim(decision, people);
        }while(continueSim);
    }

    public static void runUncontrolledSim(){
        int days = 72000;
        System.out.println("Welcome to the uncontrolled simulation!!! \nYou are about to witness " + (days / 360) + " years of history." +
                "\nHopefully your computer can handle it because who knows what may happen.");
        ArrayList<Person> people = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            people.add(PersonBuilder.createPerson());
        }
        counter = 0;
        runSim(days, people);
        printPeople(people);
        printStatistics();
    }

    public static boolean chooseControlSim(int command, ArrayList<Person> people){
        boolean continueSim;
        switch (command){
            case 1:
                runSim(1, people);
                continueSim = true;
                break;

            case 2:
                runSim(7, people);
                continueSim = true;
                break;

            case 3:
                runSim(30, people);
                continueSim = true;
                break;

            case 4:
                runSim(360, people);
                continueSim = true;
                break;

            case 5:
                printPeople(people);
                continueSim = true;
                break;

            case 6:
                printStatistics();
                continueSim = true;
                break;

            case 7:
                continueSim = false;
                break;

            default:
                System.out.println("That is an invalid option, please try again.");
                continueSim = true;
                break;
        }
        return continueSim;
    }

    public static void runSim(int days, ArrayList<Person> people){
        for(int i = 0; i < days; i++){
            for(int j = 0; j < people.size(); j++){
                people.get(j).act(people, counter);
                if(!people.get(j).isAlive()){
                    people.remove(j);
                    j--;
                }
            }
            counter++;
        }
    }

    public static void printPeople(ArrayList<Person> people){
        System.out.println("Population: " + people.size());
        for (Person p : people) {
            System.out.println(p + "\n");
        }
    }

    public static void printStatistics(){
        System.out.println("Gender:\nMale: " + stats[0][0] + "\nFemale: " + stats[0][1]);
        System.out.println("\nSkin Color:\nWhite: " + stats[1][0] + "\nBlack: " + stats[1][1] + "\nTan: " + stats[1][2]);
        System.out.println("\nHair Color:\nBlack: " + stats[2][0] + "\nBrown: " + stats[2][1] + "\nBlond: " + stats[2][2] + "\nRed: " + stats[2][3] + "\nGinger: " + stats[2][4] + "\nLight Brown: " + stats[2][5] + "\nDirty Blond: " + stats[2][6]);
        System.out.println("\nHair Style:\nStraight: " + stats[3][0] + "\nCurly: " + stats[3][1] + "\nWavy: " + stats[3][2]);
        System.out.println("\nEye Color:\nBrown: " + stats[4][0] + "\nHazel: " + stats[4][1] + "\nBlue: " + stats[4][2] + "\nGreen: " + stats[4][3]);
    }

    public static void updateStatistics(String gender, String skin, String hairC, String hairS, String eye){
        switch (gender){
            case "Male":
                stats[0][0]++;
                break;

            case "Female":
                stats[0][1]++;
                break;
        }

        switch (skin){
            case "White":
                stats[1][0]++;
                break;

            case "Black":
                stats[1][1]++;
                break;

            case "Tan":
                stats[1][2]++;
                break;
        }

        switch (hairC){
            case "Black":
                stats[2][0]++;
                break;

            case "Brown":
                stats[2][1]++;
                break;

            case "Blond":
                stats[2][2]++;
                break;

            case "Red":
                stats[2][3]++;
                break;

            case "Ginger":
                stats[2][4]++;
                break;

            case "Light Brown":
                stats[2][5]++;
                break;

            case "Dirty Blond":
                stats[2][6]++;
                break;
        }

        switch (hairS){
            case "Straight":
                stats[3][0]++;
                break;

            case "Curly":
                stats[3][1]++;
                break;

            case "Wavy":
                stats[3][2]++;
                break;
        }

        switch (eye){
            case "Brown":
                stats[4][0]++;
                break;

            case "Hazel":
                stats[4][1]++;
                break;

            case "Blue":
                stats[4][2]++;
                break;

            case "Green":
                stats[4][3]++;
                break;
        }
    }

    public static void printMainMenu(){
        System.out.println("1. Run controlled simulation. \n2. Run uncontrolled simulation. \n3. Exit.");
    }

    public static void printControlMenu(ArrayList<Person> people){
        System.out.println("Year: " + (counter / 360) + "    Month: " + ((counter % 360) / 30) + "    Day: " + (counter % 30));
        System.out.println("Population: " + people.size());
        System.out.println("1. Run for 1 day.\n2. Run for 1 week.\n3. Run for 1 month.\n4. Run for 1 year.\n5. View people.\n6. View Statistics.\n7. End simulation.");
    }
}