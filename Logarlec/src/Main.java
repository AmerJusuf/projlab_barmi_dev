import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printTestCases();
        int num;
        while( (num = scanner.nextInt()) != 0){
            callTest(num);
            printTestCases();
        }
        System.out.println("Exiting test cases.");
        scanner.close();
    }
    
    public static void callTest(int num) {
        Test test = new Test();
        switch(num){
            case 1:
                test.studentMoves();
                break;
            case 2:
                test.studentCannotMoveFullCapacity();
                break;
            case 3:
                test.studentPicksItem();
                break;
            case 4:
                test.instructorPicksItem();
                break;
            case 5:
                test.studentCanNotPickItemFullInventory();
                break;
            case 6:
                test.instructorCanNotPickItemFullInventory();
                break;
            case 7:
                test.studentPicksLogarlec();
                break;
            case 8:
                test.instructorCanNotPickLogarlec();
                break;
            case 9:
                test.TVSZProtectsStudent();
                break;
            case 10:
                test.TVSZProtectsStudentThenExpires();
                break;
            case 11:
                test.camembertOpen();
                break;
            case 12:
                test.instructorKicksStudent();
                break;
            case 13:
                test.studentGetsCaughtButProtected();
                break;
            case 14:
                test.studentGetsCaught();
                break;
            case 15:
                test.instructorGetsCaught();
                break;
            case 16:
                test.studentGetsDisabledButProtected();
                break;
            case 17:
                test.studentGetsDisabled();
                break;
            case 18:
                test.onlyInstructorGetsDisabled();
                break;
            case 19:
                test.transistorSwitchOn();
                break;
            case 20:
                test.transistorSwitchOff();
                break;
            case 21:
                test.pairTransistorsBothNotPairedYet();
                break;
            case 22:
                test.canNotPairTransistorsBecauseFirstIsPaired();
                break;
            case 23:
                test.canNotPairTransistorsBecauseSecondIsPaired();
                break;
            case 24:
                test.placeSecondTransistorAndMove();
                break;
            case 25:
                test.placeFirstTransistorActiveAndPaired();
                break;
            case 26:
                test.canNotPlaceTransistorBecauseNotActive();
                break;
            case 27:
                test.canNotPlaceTransistorBecauseNotHaveAPair();
                break;
            case 28:
                test.placeSecondTransistorActiveAndCanNotMoveBecauseOfCapacity();
                break;
            case 29:
                test.poisonedRoomToxicatesCharacters();
                break;
            case 30:
                test.mergeRooms();
                break;
            case 31:
                test.splitRoom();
                break;
            case 32:
                test.cursedRoomManagesDoors();
                break;
            case 33:
                test.ragStunsInstructor();
                break;
            case 34:
                test.beerProtectsStudent();
                break;
            case 35:
                test.ragProtectsStudent();
                break;
            default:
                break;
        }
        System.out.println("\nPress Enter to see the test cases");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void printTestCases(){
        System.out.println("Adja meg a futtatni kívánt teszt sorszámát:");
        System.out.println("0# Exit Test Cases");
        System.out.println("1# Student Moves");
        System.out.println("2# Student Can Not Move - NextRoom Full Capacity");
        System.out.println("3# Student Picks Item");
        System.out.println("4# Instructor Picks Item");
        System.out.println("5# Student Can Not Pick Item - Full Inventory");
        System.out.println("6# Instructor Can Not Pick Item - Full Inventory");
        System.out.println("7# Student Picks Logarlec");
        System.out.println("8# Instructor Can Not Pick Logarlec");
        System.out.println("9# TVSZ Protects Student");
        System.out.println("10# TVSZ Protects Student Then Expires");
        System.out.println("11# Camembert Open");
        System.out.println("12# Instructor Kicks Student");
        System.out.println("13# Student Gets Caught But Protected");
        System.out.println("14# Student Gets Caught");
        System.out.println("15# Instructor Gets Caught");
        System.out.println("16# Student Gets Disabled (Toxicated), But Protected");
        System.out.println("17# Student Gets Disabled (Toxicated)");
        System.out.println("18# Only Instructor Gets Disabled (Toxicated)"); //Called on student character should not do anything
        System.out.println("19# Transistor Switch On");
        System.out.println("20# Transistor Switch Off");
        System.out.println("21# Pair Transistors, Both Transistors Are Not Paired Yet");
        System.out.println("22# Can Not Pair Transistors Because First Is Paired");
        System.out.println("23# Can Not Pair Transistors Because Second Is Paired");
        System.out.println("24# Place Second Transistor And Move");
        System.out.println("25# Place First Transistor (Active And Paired)");
        System.out.println("26# Can Not Place Transistor Because Not Active");
        System.out.println("27# Can Not Place Transistor Because Does Not Have A Pair");
        System.out.println("28# Place Second Transistor Active And Can Not Move Because Of Capacity");
        System.out.println("29# PoisonedRoom Toxicates Characters");
        System.out.println("30# Merge Rooms");
        System.out.println("31# Split Room");
        System.out.println("32# CursedRoom Manages Doors");
        System.out.println("33# Rag Stuns Instructor");
        System.out.println("34# Beer Protects Student");
        System.out.println("35# Rag Protects Student");
    }

}