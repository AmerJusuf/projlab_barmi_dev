package TestLogic;

import Characters.Character;
import Characters.Cleaner;
import Characters.Instructor;
import Characters.Student;
import Game.Labyrinth;
import Items.*;
import Rooms.BasicRoom;
import Rooms.IRoom;
import Rooms.PoisonedRoomDecorator;
import Rooms.StickyRoomDecorator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class TestLogic {

    private static final Map<String, Pattern> commandPatterns = new HashMap<>();

    static {
        //JUSUF
        commandPatterns.put("loadMap", Pattern.compile("loadMap\\s+-file\\s+(\\S+)"));              // NINCS IMPLEMENTÁLVA
        commandPatterns.put("createLabyrinth", Pattern.compile("createLabyrinth\\s+-id\\s+(\\S+)"));
        commandPatterns.put("addCharacterToLabyrinth", Pattern.compile("addCharacterToLabyrinth\\s+-lab\\s+(\\S+)\\s+-ch\\s+(\\S+)"));
        commandPatterns.put("addItemToCharacter", Pattern.compile("addItemToCharacter\\s+-it\\s+(\\S+)\\s+-ch\\s+(\\S+)"));
        commandPatterns.put("addItemToRoom", Pattern.compile("addItemToRoom\\s+-it\\s+(\\S+)\\s+-r\\s+(\\S+)"));
        commandPatterns.put("addCharacterToRoom", Pattern.compile("addCharacterToRoom\\s+-ch\\s+(\\S+)\\s+-r\\s+(\\S+)"));
        commandPatterns.put("createRoom", Pattern.compile("createRoom\\s+-r\\s+(\\S+)\\s+-capacity\\s+(\\d+)\\s*(-type\\s+(\\S+))?"));
        commandPatterns.put("createCharacter", Pattern.compile("createCharacter\\s+-ch\\s+(\\S+)\\s*(-type\\s+(\\S+))?"));
        commandPatterns.put("addRoomToLabyrinth", Pattern.compile("addRoomToLabyrinth\\s+-lab\\s+(\\S+)\\s+-r\\s+(\\S+)"));

        commandPatterns.put("createItem", Pattern.compile("createItem\\s+-it\\s+(\\S+)\\s+-type\\s+(\\S+)(?:\\s+-fake)?(?:\\s+-life\\s+(\\d+))?"));     // Ez elég érdekes

        commandPatterns.put("moveCharacter", Pattern.compile("moveCharacter\\s+-ch\\s+(\\S+)\\s+-r\\s+(\\S+)"));
        commandPatterns.put("pickItem", Pattern.compile("pickItem\\s+-ch\\s+(\\S+)\\s+-it\\s+(\\S+)"));
        commandPatterns.put("dropItem", Pattern.compile("dropItem\\s+-ch\\s+(\\S+)\\s+-it\\s+(\\S+)"));
        commandPatterns.put("setPoisoned", Pattern.compile("setPoisoned\\s+-ch\\s+(\\S+)"));
        commandPatterns.put("disable", Pattern.compile("disable\\s+-ch\\s+(\\S+)"));
        commandPatterns.put("getCaught", Pattern.compile("getCaught\\s+-ch\\s+(\\S+)"));
        commandPatterns.put("toxicate", Pattern.compile("toxicate\\s+-ch\\s+(\\S+)"));
        commandPatterns.put("stunInstructor", Pattern.compile("stunInstructor\\s+-ch\\s+(\\S+)"));
        commandPatterns.put("stepItem", Pattern.compile("stepItem\\s+-it\\s+(\\S+)"));
        commandPatterns.put("openCamembert", Pattern.compile("openCamembert\\s+-it\\s+(\\S+)"));
        commandPatterns.put("unToxicateRoomAirFreshener", Pattern.compile("unToxicateRoomAirFreshener\\s+-it\\s+(\\S+)"));
        commandPatterns.put("activate", Pattern.compile("activate\\s+-it\\s+(\\S+)"));
        //JUSUF VEGE

        //VIGYETEK
        commandPatterns.put("createTransistor", Pattern.compile("createTransistor\\s+-it\\s+(\\S+)(\\s+-active)?(\\s+-paired)?"));
        commandPatterns.put("setPairTransistor", Pattern.compile("setPairTransistor\\s+-it\\s+(\\S+)\\s+-it\\s+(\\S+)"));
        commandPatterns.put("placeTransistor", Pattern.compile("placeTransistor\\s+-it\\s+(\\S+)"));
        commandPatterns.put("switchTransistor", Pattern.compile("switchTransistor\\s+-it\\s+(\\S+)"));
        //VIGYETEK VEGE


        //JUSUF
        commandPatterns.put("addNeighbour", Pattern.compile("addNeighbour\\s+-r\\s+(\\S+)\\s+-r\\s+(\\S+)"));
        commandPatterns.put("mergeRooms", Pattern.compile("mergeRooms\\s+-r\\s+(\\S+)\\s+-r\\s+(\\S+)"));
        commandPatterns.put("splitRoom", Pattern.compile("splitRoom\\s+-r\\s+(\\S+)"));
        commandPatterns.put("getNeighbours", Pattern.compile("getNeighbours\\s+-r\\s+(\\S+)"));
        commandPatterns.put("makeSticky", Pattern.compile("makeSticky\\s+-r\\s+(\\S+)"));
        //JUSUF VEGE


        //VIGYETEK
        commandPatterns.put("list", Pattern.compile("list"));
        commandPatterns.put("roomStatus", Pattern.compile("roomStatus\\s+-r\\s+(\\S+)"));
        commandPatterns.put("characterStatus", Pattern.compile("characterStatus\\s+-ch\\s+(\\S+)"));
        commandPatterns.put("itemStatus", Pattern.compile("itemStatus\\s+-it\\s+(\\S+)"));


        commandPatterns.put("gameStatus", Pattern.compile("gameStatus"));
        commandPatterns.put("nextRound", Pattern.compile("nextRound"));
        commandPatterns.put("skipTurn", Pattern.compile("skipTurn"));
        commandPatterns.put("startGame", Pattern.compile("startGame"));
        //VIGYETEK VEGE


        // AZ ELEJÉN A              loadMap NINCS IMPLEMENTÁLVA

        // NINCS IMPLEMENTÁLVA:
                    // NEXTROUND
                    // SKIPTURN
                    // STARTGAME
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter commands or 'runScript <filename>' to process commands from a file, or 'exit' to quit:");
        String input = scanner.nextLine();

        while (!input.equals("exit")) {
            if (input.startsWith("runScript ")) {
                String filename = input.substring(10).trim();
                processCommandsFromFile(filename, true); // Process commands from file and write output to file
            } else {
                processCommandsFromFile(input, false); // Process command from console and write output to console
            }

            System.out.println("\nEnter commands or 'runScript <filename>' to process commands from a file, or 'exit' to quit:");
            input = scanner.nextLine();
        }

        scanner.close();
    }

    public static void processCommandsFromFile(String commandOrFileName, boolean writeToFile) {
        if (!writeToFile) {
            String output = processCommand(commandOrFileName);
            System.out.println(output); // Print output to console
        }
        else {
            String directory = System.getProperty("user.dir");

            String fileName = commandOrFileName;
            String fileInPath = directory + File.separator + "Files" + File.separator + "Act" + File.separator + fileName + ".txt";
            System.out.println(fileInPath);

            //String fileStartCharacter = fileName.substring(0, 1);
            String fileOutputPath = directory + File.separator + "Files" + File.separator + "Output" + File.separator + fileName + ".txt";
            System.out.println(fileOutputPath);

            try {
                File file = new File(fileInPath);
                if (!file.exists()) {
                    System.out.println("File not found");
                    throw new RuntimeException("File not found");
                }

                Scanner scanner = new Scanner(file);

                String fileContent = "";
                while (scanner.hasNextLine()) {
                    fileContent = fileContent.concat(processCommand(scanner.nextLine()) + "\n");
                }
                scanner.close();

                // Trim trailing newlines from the end of fileContent
                fileContent = TrimContent(fileContent);

                FileWriter writer = new FileWriter(fileOutputPath);
                writer.write(fileContent);
                writer.close();


                // Compare the two files
                String fileAssertPath = directory + File.separator + "Files" + File.separator + "Assert" + File.separator + fileName + ".txt";
                System.out.println(fileAssertPath);

                BufferedReader reader1 = new BufferedReader(new FileReader(fileOutputPath));
                BufferedReader reader2 = new BufferedReader(new FileReader(fileAssertPath));
                String line1 = reader1.readLine();
                String line2 = reader2.readLine();

                boolean areEqual = true;
                while (line1 != null || line2 != null) {
                    if (line1 == null || line2 == null) {
                        areEqual = false;
                        break;
                    } else if (!line1.equalsIgnoreCase(line2)) {
                        areEqual = false;
                        break;
                    }
                    line1 = reader1.readLine();
                    line2 = reader2.readLine();
                }

                if (areEqual) {
                    System.out.println("Two files have same content.");
                } else {
                    System.out.println("Two files have different content.");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static String TrimContent(String content) {
        int length = content.length();
        int end = length;

        // Find the position of the last non-newline character
        while (end > 0 && (content.charAt(end - 1) == '\n' || content.charAt(end - 1) == '\r')) {
            end--;
        }

        // Return the substring excluding trailing newline characters
        return content.substring(0, end);
    }


    //Celszeru masik osztalyba, de felolem maradhat (A.J)
    private static Labyrinth labyrinth;
    private static Map<Integer, Character> charactersMap = new HashMap<>();

    private static Map<Integer, IRoom> roomsMap = new HashMap<>();

    private static Map<Integer, Item> itemsMap = new HashMap<>();



    public static String processCommand(String inputCommand) {
        StringBuilder outputBuilder = new StringBuilder();

        // Try to match the input command with each defined pattern
        for (Map.Entry<String, Pattern> entry : commandPatterns.entrySet()) {
            String commandName = entry.getKey();
            Pattern pattern = entry.getValue();

            String labyrinthId;
            String characterId;
            String roomId;
            String itemId;

            Matcher matcher = pattern.matcher(inputCommand);
            if (matcher.matches()) {
                // Extract parameters based on matched pattern
                switch (commandName) {
                    case "loadMap":
                        String fileName = matcher.group(1);
                        //TODO
                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("file: ").append(fileName).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");
                        break;

                    case "createLabyrinth":
                        labyrinthId = matcher.group(1);

                        labyrinth = new Labyrinth();

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Labyrinth: ").append(labyrinthId).append("\n");
                        outputBuilder.append("Result: Successful").append("\n");
                        break;

                    case "addCharacterToLabyrinth":
                        labyrinthId = matcher.group(1);
                        characterId = matcher.group(2);

                        Character ch = charactersMap.get(Integer.parseInt(characterId));
                        if(ch instanceof Student) {
                            labyrinth.addStudent((Student) ch);
                        } else if( ch instanceof Instructor) {
                            labyrinth.addInstructor((Instructor) ch);
                        } else if( ch instanceof Cleaner) {
                            labyrinth.addCleaner((Cleaner) ch);
                        } else {
                            throw new RuntimeException("Invalid character id");
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Labyrinth: ").append(labyrinthId).append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: Successful").append("\n");
                        break;

                    case "addItemToCharacter":
                        itemId = matcher.group(1);
                        characterId = matcher.group(2);

                        Character character = charactersMap.get(Integer.parseInt(characterId));
                        Item item = itemsMap.get(Integer.parseInt(itemId));
                        character.addItem(item);
                        labyrinth.addItem(item); // Kell ha pl. nextRoundot is akarunk ellenőrizni (Ne kelljen +1 addItemToLab)

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: Successful").append("\n");
                        break;

                    case "addItemToRoom":
                        itemId = matcher.group(1);
                        roomId = matcher.group(2);

                        Item itemToAdd = itemsMap.get(Integer.parseInt(itemId));
                        IRoom room = roomsMap.get(Integer.parseInt(roomId));
                        room.addItem(itemToAdd);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Room: ").append(roomId).append("\n");
                        outputBuilder.append("Result: Successful").append("\n");
                        break;

                    case "addCharacterToRoom":
                        String charId = matcher.group(1);
                        String roomID = matcher.group(2);

                        Character characterToAdd = charactersMap.get(Integer.parseInt(charId));
                        IRoom roomToAdd = roomsMap.get(Integer.parseInt(roomID));
                        roomToAdd.addCharacter(characterToAdd);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(charId).append("\n");
                        outputBuilder.append("Room: ").append(roomID).append("\n");
                        outputBuilder.append("Result: Successful").append("\n");
                        break;

                    case "createRoom":
                        roomId = matcher.group(1);
                        int capacity = Integer.parseInt(matcher.group(2));
                        String roomType;
                        if (matcher.group(4) == null) // group(4) is optional (-type RoomType)
                            roomType = "BasicRoom";
                        else
                            roomType = matcher.group(4);

                        if(roomType.equalsIgnoreCase("PoisonedRoom")) {
                            roomsMap.put(Integer.parseInt(roomId), new PoisonedRoomDecorator(new BasicRoom(capacity)));
                        } else if(roomType.equalsIgnoreCase("StickyRoom")) {
                            roomsMap.put(Integer.parseInt(roomId), new StickyRoomDecorator(new BasicRoom(capacity)));
                        } else {
                            roomsMap.put(Integer.parseInt(roomId), new BasicRoom(capacity));
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Room: ").append(roomId).append("\n");
                        outputBuilder.append("Capacity: ").append(capacity).append("\n");
                        outputBuilder.append("Type: ").append(roomType).append("\n");
                        outputBuilder.append("Result: Successful").append("\n");
                        break;

                    case "createCharacter":
                        characterId = matcher.group(1);
                        String characterType;
                        if (matcher.group(3) == null) // group(3) is optional (-type CharacterType)
                            characterType = "Student";
                        else
                            characterType = matcher.group(3);

                       if(characterType.equalsIgnoreCase("Instructor")) {
                            charactersMap.put(Integer.parseInt(characterId), new Instructor());
                        } else if(characterType.equalsIgnoreCase("Cleaner")) {
                            charactersMap.put(Integer.parseInt(characterId), new Cleaner());
                        } else {
                            charactersMap.put(Integer.parseInt(characterId), new Student());
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Type: ").append(characterType).append("\n");
                        outputBuilder.append("Result: Successful").append("\n");

                        break;
                    case "addRoomToLabyrinth":
                        labyrinthId = matcher.group(1);
                        roomId = matcher.group(2);

                        IRoom iroom = roomsMap.get(Integer.parseInt(roomId));
                        labyrinth.addRoom(iroom);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Labyrinth: ").append(labyrinthId).append("\n");
                        outputBuilder.append("Room: ").append(roomId).append("\n");
                        outputBuilder.append("Result: Successful").append("\n");

                        break;
                    case "createItem":
                        itemId = matcher.group(1);
                        String itemType = matcher.group(2);

                        boolean fake = false;
                        if (inputCommand.contains("-fake"))
                            fake = true;

                        int life = 0;
                        if (matcher.group(3) != null)
                            life = Integer.parseInt(matcher.group(3));

                        if(itemType.equalsIgnoreCase("TVSZ")) {
                            itemsMap.put(Integer.parseInt(itemId), new TVSZ(fake, life));
                        } else if(itemType.equalsIgnoreCase("AirFreshener")) {
                            itemsMap.put(Integer.parseInt(itemId), new AirFreshener(fake));
                        } else if(itemType.equalsIgnoreCase("Camembert")) {
                            itemsMap.put(Integer.parseInt(itemId), new Camembert(fake));
                        } else if(itemType.equalsIgnoreCase("Transistor")) {
                            itemsMap.put(Integer.parseInt(itemId), new Transistor(fake));
                        } else if (itemType.equalsIgnoreCase("Logarlec")) {
                            itemsMap.put(Integer.parseInt(itemId), new Logarlec(fake));
                        } else if(itemType.equalsIgnoreCase("FFP2")){
                            itemsMap.put(Integer.parseInt(itemId), new FFP2(fake));
                        } else if(itemType.equalsIgnoreCase("Beer")){
                            itemsMap.put(Integer.parseInt(itemId), new Beer(fake));
                        } else if(itemType.equalsIgnoreCase("Rag")){
                            itemsMap.put(Integer.parseInt(itemId), new Rag(fake));
                        } else {
                            throw new RuntimeException("Invalid item ID");
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Type: ").append(itemType).append("\n");
                        outputBuilder.append("Fake: ").append(fake).append("\n");
                        if (itemType.equals("TVSZ")) {
                            outputBuilder.append("Life: ").append(life).append("\n");
                        }
                        outputBuilder.append("Result: Successful").append("\n");
                        //TODO: doksiba mashogy van idk mit beszéltetek azota (A.J)
                        break;
                    case "moveCharacter":
                        characterId = matcher.group(1);
                        String roomToMove = matcher.group(2);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("SourceRoom: IDE KELL AZ HOGY A KARAKTER MELYIK SZOBABAN VAN ").append(roomToMove).append("\n");
                        outputBuilder.append("DestRoom: ").append(roomToMove).append("\n");
                        outputBuilder.append("Result: Successful").append("\n");

                        break;
                    case "pickItem":
                        characterId = matcher.group(1);
                        itemId = matcher.group(2);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "dropItem":
                        characterId = matcher.group(1);
                        itemId = matcher.group(2);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "setPoisoned":
                        characterId = matcher.group(1);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "disable":
                        characterId = matcher.group(1);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "getCaught":
                        characterId = matcher.group(1);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "toxicate":
                        characterId = matcher.group(1);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "stunInstructor":
                        characterId = matcher.group(1);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "stepItem":
                        itemId = matcher.group(1);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Active: true/false EZT KELL MAJD KÖTNI AZ ADATHOZ").append("\n");

                        break;
                    case "openCamembert":
                        itemId = matcher.group(1);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "unToxicateRoomAirFreshener":
                        itemId = matcher.group(1);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "activate":
                        itemId = matcher.group(1);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "createTransistor":
                        itemId = matcher.group(1);

                        boolean active = false;
                        if (matcher.group(2) != null)
                            active = true;

                        boolean paired = false;
                        if (matcher.group(3) != null)
                            paired = true;



                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Active: ").append(active).append("\n");
                        outputBuilder.append("Paired: ").append(paired).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "setPairTransistor":
                        String tr1 = matcher.group(1);
                        String tr2 = matcher.group(2);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Pair1: ").append(tr1).append("\n");
                        outputBuilder.append("Pair2: ").append(tr2).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "placeTransistor":
                        itemId = matcher.group(1);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Location: ").append("A SZOBA ID_JA AHOVA LETESZEM").append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "switchTransistor":
                        itemId = matcher.group(1);

                        Transistor switchTransistor = (Transistor) itemsMap.get(Integer.parseInt(itemId));
                        switchTransistor.switchTransistor();

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Active: true/false EZT MAJD AZ ALAPAN HOGY MI").append("\n");

                        break;
                    case "addNeighbour":
                        String room1 = matcher.group(1);
                        //String room2 = matcher.group(2);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Room1: ").append(room1).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "mergeRooms":
                        String room3 = matcher.group(1);
                        String room4 = matcher.group(2);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Room1: ").append(room3).append("\n");
                        outputBuilder.append("Room2: ").append(room4).append("\n");
                        outputBuilder.append("MergedRoom: ").append("A KELETKEZŐ ROOMID / null ha fail").append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "splitRoom":
                        roomId = matcher.group(1);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Room: ").append(roomId).append("\n");
                        outputBuilder.append("NewRoom1: ").append("A KELETKEZŐ ROOMID / null ha fail").append("\n");
                        outputBuilder.append("NewRoom2: ").append("A KELETKEZŐ ROOMID / null ha fail").append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "getNeighbours":
                        roomId = matcher.group(1);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Room: ").append(roomId).append("\n");
                        outputBuilder.append("Neighbours: ").append("A SZOMSZÉDOK LISTÁJA").append("\n");

                        break;
                    case "makeSticky":
                        roomId = matcher.group(1);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Room: ").append(roomId).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    case "list":
                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("LabirynthId KELLENEK IDE ").append("\n");
                        outputBuilder.append("RoomId-k KELLENEK IDE ").append("\n");
                        outputBuilder.append("CharacterId-k KELLENEK IDE ").append("\n");
                        outputBuilder.append("ItemId-k KELLENEK IDE ").append("\n");

                        break;
                    case "roomStatus":
                        roomId = matcher.group(1);

                        outputBuilder.append("ID-s:").append("\n");
                        outputBuilder.append("Room: ").append(roomId).append("\n");
                        outputBuilder.append("RoomType: ").append("A SZOBA TÍPUSA").append("\n");
                        outputBuilder.append("Characters: ").append("A SZOBÁBAN LÉVŐ KARAKTEREK ID-ja").append("\n");
                        outputBuilder.append("Items: ").append("A SZOBÁBAN LÉVŐ ITEMEK ID-ja").append("\n");
                        outputBuilder.append("Capacity: ").append("A SZOBÁBA KAPACITÁSA").append("\n");

                        break;
                    case "characterStatus":
                        characterId = matcher.group(1);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("CharacterType: ").append("A KARAKTER TÍPUSA").append("\n");
                        outputBuilder.append("Room: ").append("A SZOBA ID-JA AHOL A KARAKTER VAN").append("\n");
                        outputBuilder.append("Items: ").append("A KARAKTER ITEMEINEK ID-JA").append("\n");
                        outputBuilder.append("Toxicated: ").append("TRUE / FALSE AZ ÁLLAPOTNAK MEGFELELŐEN").append("\n");

                        break;
                    case "itemStatus":
                        itemId = matcher.group(1);

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("ItemType: ").append("AZ ITEM TÍPUSA").append("\n");
                        outputBuilder.append("Owner/Room: ").append("A SZOBA / KARAKTER ID-JA AHOL AZ ITEM VAN").append("\n");
                        outputBuilder.append("Fake: ").append("TRUE / FALSE").append("\n");

                        break;



                    case "gameStatus":
                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Status: ").append("WIN / PLAYING / LOOSE").append("\n");

                        break;
                    case "nextRound":
                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("VALAMI KIÍRÁS XD").append("\n");

                        break;
                    case "skipTurn":
                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append("KARAKTER ID-JA").append("\n");

                        break;
                    case "startGame":
                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("VALAMI KIÍRÁS XD").append("\n");
                        break;



                        // AZÉRT AZ ALJÁN LÉVŐT HASZNÁLOM MERT KELL EGY VISSZATÉRÉS MINDENKÉPPEN
                        /*
                    default:
                        outputBuilder.append("Invalid command: ").append(inputCommand).append("\n");
                        break;
                         */

                }
                return outputBuilder.toString();
            }
        }

        // If no matching command pattern is found
        String res = outputBuilder.append("Invalid command: ").append(inputCommand).append("\n").toString();
        return res;
    }
}