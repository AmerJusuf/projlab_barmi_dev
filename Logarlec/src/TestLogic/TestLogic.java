package TestLogic;

import Characters.Character;
import Characters.Cleaner;
import Characters.Instructor;
import Characters.Student;
import Game.Labyrinth;
import Items.*;
import Rooms.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class TestLogic {

    private static final Map<String, Pattern> commandPatterns = new HashMap<>();

    static {
        //JUSUF
        commandPatterns.put("loadMap", Pattern.compile("loadMap\\s+-file\\s+(\\S+)"));              // NINCS IMPLEMENTÁLVA
        commandPatterns.put("createLabyrinth", Pattern.compile("createLabyrinth\\s+-lab\\s+(\\S+)"));
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

        //ZOLI
        commandPatterns.put("createTransistor", Pattern.compile("createTransistor\\s+-it\\s+(\\S+)(\\s+-active)?(\\s+-paired)?"));
        commandPatterns.put("setPairTransistor", Pattern.compile("setPairTransistor\\s+-it\\s+(\\S+)\\s+-it\\s+(\\S+)"));
        commandPatterns.put("placeTransistor", Pattern.compile("placeTransistor\\s+-it\\s+(\\S+)"));
        commandPatterns.put("switchTransistor", Pattern.compile("switchTransistor\\s+-it\\s+(\\S+)"));
        //ZOLI VEGE


        //JUSUF
        commandPatterns.put("addNeighbour", Pattern.compile("addNeighbour\\s+-r\\s+(\\S+)\\s+-r\\s+(\\S+)"));
        commandPatterns.put("mergeRooms", Pattern.compile("mergeRooms\\s+-r\\s+(\\S+)\\s+-r\\s+(\\S+)"));
        commandPatterns.put("splitRoom", Pattern.compile("splitRoom\\s+-r\\s+(\\S+)"));
        commandPatterns.put("getNeighbours", Pattern.compile("getNeighbours\\s+-r\\s+(\\S+)"));
        commandPatterns.put("makeSticky", Pattern.compile("makeSticky\\s+-r\\s+(\\S+)"));
        //JUSUF VEGE


        //BOTI
        commandPatterns.put("list", Pattern.compile("list"));
        commandPatterns.put("roomStatus", Pattern.compile("roomStatus\\s+-r\\s+(\\S+)"));
        commandPatterns.put("characterStatus", Pattern.compile("characterStatus\\s+-ch\\s+(\\S+)"));
        commandPatterns.put("itemStatus", Pattern.compile("itemStatus\\s+-it\\s+(\\S+)"));
        //BOTI VEGE


        //GERI
        commandPatterns.put("gameStatus", Pattern.compile("gameStatus"));
        commandPatterns.put("nextRound", Pattern.compile("nextRound"));
        commandPatterns.put("skipTurn", Pattern.compile("skipTurn"));
        commandPatterns.put("startGame", Pattern.compile("startGame"));
        //GERI VEGE


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
    private static Map<String, Character> charactersMap = new HashMap<>();

    private static Map<String, IRoom> roomsMap = new HashMap<>();
    private static Map<String, Item> itemsMap = new HashMap<>();



    public static String processCommand(String inputCommand) {
        StringBuilder outputBuilder = new StringBuilder();
        final String success = "Successful";
        final String fail = "Fail";
        String result = success; //ez a default

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

                    case "createLabyrinth": {
                        labyrinthId = matcher.group(1);

                        labyrinth = new Labyrinth();

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Labyrinth: ").append(labyrinthId).append("\n");
                        outputBuilder.append("Result: Successful").append("\n");
                        break;
                    }

                    case "addCharacterToLabyrinth": {
                        labyrinthId = matcher.group(1);
                        characterId = matcher.group(2);

                        if( !charactersMap.containsKey(characterId)) {
                            result = fail;
                        } else {
                            result = success;
                            Character ch = charactersMap.get(characterId);
                            if (ch instanceof Student) {
                                labyrinth.addStudent((Student) ch);
                            } else if (ch instanceof Instructor) {
                                labyrinth.addInstructor((Instructor) ch);
                            } else if (ch instanceof Cleaner) {
                                labyrinth.addCleaner((Cleaner) ch);
                            } else {
                                throw new RuntimeException("Invalid character id");
                            }
                        }
                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Labyrinth: ").append(labyrinthId).append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");
                        break;
                    }

                    case "addItemToCharacter": {
                        itemId = matcher.group(1);
                        characterId = matcher.group(2);

                        if(!charactersMap.containsKey(characterId) || !itemsMap.containsKey(itemId)){
                            result = fail;
                        }else {
                            result = success;
                            Character character = charactersMap.get(characterId);
                            Item item = itemsMap.get(itemId);
                            character.addItem(item);
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");
                        break;
                    }

                    case "addItemToRoom": {
                        itemId = matcher.group(1);
                        roomId = matcher.group(2);

                        if(!itemsMap.containsKey(itemId) || !roomsMap.containsKey(roomId)) {
                            result = fail;
                        } else {
                            result = success;
                            Item itemToAdd = itemsMap.get(itemId);
                            IRoom room = roomsMap.get(roomId);
                            room.addItem(itemToAdd);
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Room: ").append(roomId).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");
                        break;
                    }

                    case "addCharacterToRoom": {
                        String charId = matcher.group(1);
                        String roomID = matcher.group(2);

                        if(!charactersMap.containsKey(charId) || !roomsMap.containsKey(roomID)) {
                            result = fail;
                        } else {
                            result = success;
                            Character characterToAdd = charactersMap.get(charId);
                            IRoom roomToAdd = roomsMap.get(roomID);
                            roomToAdd.addCharacter(characterToAdd);
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(charId).append("\n");
                        outputBuilder.append("Room: ").append(roomID).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");
                        break;
                    }

                    case "createRoom": {
                        roomId = matcher.group(1);
                        int capacity = Integer.parseInt(matcher.group(2));
                        String roomType;
                        if (matcher.group(4) == null) // group(4) is optional (-type RoomType)
                            roomType = "BasicRoom";
                        else
                            roomType = matcher.group(4);

                        if (roomsMap.containsKey(roomId)) {
                            result = fail;
                        } else {
                            result = success;
                            if (roomType.equalsIgnoreCase("PoisonedRoom")) {
                                roomsMap.put(roomId, new PoisonedRoomDecorator(new BasicRoom(capacity)));
                            } else if (roomType.equalsIgnoreCase("StickyRoom")) {
                                roomsMap.put(roomId, new StickyRoomDecorator(new BasicRoom(capacity)));
                            } else {
                                roomsMap.put(roomId, new BasicRoom(capacity));
                            }

                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Room: ").append(roomId).append("\n");
                        outputBuilder.append("Capacity: ").append(capacity).append("\n");
                        outputBuilder.append("Type: ").append(roomType).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");
                        break;
                    }

                    case "createCharacter": {
                        characterId = matcher.group(1);
                        String characterType;
                        if (matcher.group(3) == null) // group(3) is optional (-type CharacterType)
                            characterType = "Student";
                        else
                            characterType = matcher.group(3);

                        if (charactersMap.containsKey(characterId)) {
                            result = fail;
                        } else {
                            result = success;
                            if (characterType.equalsIgnoreCase("Instructor")) {
                                charactersMap.put(characterId, new Instructor());
                            } else if (characterType.equalsIgnoreCase("Cleaner")) {
                                charactersMap.put(characterId, new Cleaner());
                            } else {
                                charactersMap.put(characterId, new Student());
                            }

                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Type: ").append(characterType).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");

                        break;
                    }

                    case "addRoomToLabyrinth": {
                        labyrinthId = matcher.group(1);
                        roomId = matcher.group(2);

                        if(!roomsMap.containsKey(roomId)) {
                            result = fail;
                        } else {
                            result = success;
                            IRoom iroom = roomsMap.get(roomId);
                            labyrinth.addRoom(iroom);
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Labyrinth: ").append(labyrinthId).append("\n");
                        outputBuilder.append("Room: ").append(roomId).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");
                        break;
                    }

                    case "createItem": {
                        itemId = matcher.group(1);
                        String itemType = matcher.group(2);

                        boolean fake = false;
                        if (inputCommand.contains("-fake"))
                            fake = true;

                        int life = 0;
                        if (matcher.group(3) != null)
                            life = Integer.parseInt(matcher.group(3));


                        if (itemsMap.containsKey(itemId)) {
                            result = fail;
                        } else {
                            result = success;
                            if (itemType.equalsIgnoreCase("TVSZ")) {
                                itemsMap.put(itemId, new TVSZ(fake, life));
                            } else if (itemType.equalsIgnoreCase("AirFreshener")) {
                                itemsMap.put(itemId, new AirFreshener(fake));
                            } else if (itemType.equalsIgnoreCase("Camembert")) {
                                itemsMap.put(itemId, new Camembert(fake));
                            } else if (itemType.equalsIgnoreCase("Transistor")) {
                                itemsMap.put(itemId, new Transistor(fake));
                            } else if (itemType.equalsIgnoreCase("Logarlec")) {
                                itemsMap.put(itemId, new Logarlec(fake));
                            } else if (itemType.equalsIgnoreCase("FFP2")) {
                                itemsMap.put(itemId, new FFP2(fake));
                            } else if (itemType.equalsIgnoreCase("Beer")) {
                                itemsMap.put(itemId, new Beer(fake));
                            } else if (itemType.equalsIgnoreCase("Rag")) {
                                itemsMap.put(itemId, new Rag(fake));
                            } else {
                                throw new RuntimeException("Invalid item ID");
                            }
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Type: ").append(itemType).append("\n");
                        outputBuilder.append("Fake: ").append(fake).append("\n");
                        if (itemType.equals("TVSZ")) {
                            outputBuilder.append("Life: ").append(life).append("\n");
                        }
                        outputBuilder.append("Result: " + result).append("\n");
                        break;
                    }

                    case "moveCharacter": {
                        characterId = matcher.group(1);
                        String roomToMove = matcher.group(2);

                        Character characterToMove;
                        String sourceRoom = "";
                        if (!roomsMap.containsKey(roomToMove)) {
                            result = fail;
                        } else {
                            result = success;
                            characterToMove = charactersMap.get(characterId);
                            for (Map.Entry<String, IRoom> roomEntry : roomsMap.entrySet()) {
                                if (roomEntry.getValue().getCharacters().contains(characterToMove)) {
                                    sourceRoom = entry.getKey();
                                    roomEntry.getValue().removeCharacter(characterToMove);
                                    roomsMap.get(roomToMove).addCharacter(characterToMove);
                                    break;
                                }
                            }
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("SourceRoom: " + sourceRoom).append(roomToMove).append("\n");
                        outputBuilder.append("DestRoom: ").append(roomToMove).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");
                        break;
                    }

                    case "pickItem": {
                        characterId = matcher.group(1);
                        itemId = matcher.group(2);

                        if (!charactersMap.containsKey(characterId) || !itemsMap.containsKey(itemId)) {
                            result = fail;
                        } else {
                            result = success;
                            Character character = charactersMap.get(characterId);
                            Item item = itemsMap.get(itemId);
                            character.pickItem(item);
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");
                        break;
                    }

                    case "dropItem": {
                        characterId = matcher.group(1);
                        itemId = matcher.group(2);

                        if (!charactersMap.containsKey(characterId) || !itemsMap.containsKey(itemId)) {
                            result = fail;
                        } else {
                            result = success;
                            Character character = charactersMap.get(characterId);
                            Item item = itemsMap.get(itemId);
                            character.dropItem(item);
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");
                        break;
                    }

                    case "setPoisoned": {
                        characterId = matcher.group(1);

                        if(!charactersMap.containsKey(characterId)) {
                            result = fail;
                        } else {
                            result = success;
                            Character character = charactersMap.get(characterId);
                            character.setPoisoned(true);
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");
                        break;
                    }

                    case "disable": {
                        characterId = matcher.group(1);

                        if(!charactersMap.containsKey(characterId)) {
                            result = fail;
                        } else {
                            result = success;
                            Character character = charactersMap.get(characterId);
                            character.disable();
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");
                        break;
                    }

                    case "getCaught": {
                        characterId = matcher.group(1);

                        if (!charactersMap.containsKey(characterId)) {
                            result = fail;
                        } else {
                            result = success;
                            Character character = charactersMap.get(characterId);
                            character.getCaught();
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");

                        break;
                    }


                    //TODO ...
                    case "toxicate": {
                        characterId = matcher.group(1);

                        if(!charactersMap.containsKey(characterId)) {
                            result = fail;
                        } else {
                            result = success;
                            Character character = charactersMap.get(characterId);
                            character.disable();
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");

                        break;
                    }

                    case "stunInstructor": {
                        characterId = matcher.group(1);

                        if(!charactersMap.containsKey(characterId)) {
                            result = fail;
                        } else {
                            result = success;
                            Character character = charactersMap.get(characterId);
                            character.disableInstructor();
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");

                        break;
                    }

                    case "stepItem": {
                        itemId = matcher.group(1);

                        String isActive = "null";
                        if(!itemsMap.containsKey(itemId)) {
                            result = fail;
                        } else {
                            result = success;
                            Item item = itemsMap.get(itemId);
                            item.step();
                            if ( item.getisActive() ) {
                                isActive = "true";
                            } else {
                                isActive = "false";

                            }
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Active: " + isActive).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");

                        break;
                    }

                    case "openCamembert": {
                        itemId = matcher.group(1);

                        if(!itemsMap.containsKey(itemId)) {
                            result = fail;
                        } else {
                            result = success;
                            if(itemsMap.get(itemId) instanceof Camembert) {
                                Camembert cm = (Camembert) itemsMap.get(itemId);
                                cm.open();
                            }

                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");

                        break;
                    }

                    case "unToxicateRoomAirFreshener": {
                        itemId = matcher.group(1);

                        if ( !itemsMap.containsKey(itemId) ) {
                            result = fail;
                        } else {
                            result = success;
                            if ( itemsMap.get(itemId) instanceof AirFreshener ) {
                                AirFreshener af = (AirFreshener) itemsMap.get(itemId);
                                af.unToxicateRoom();
                            }
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Result: Successful/Fail").append("\n");

                        break;
                    }

                    case "activate": {
                        itemId = matcher.group(1);

                        if(!itemsMap.containsKey(itemId)) {
                            result = fail;
                        } else {
                            result = success;
                            Item item = itemsMap.get(itemId);
                            item.setIsActive(true);
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");

                        break;
                    }

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

                    case "addNeighbour": {
                        String room1 = matcher.group(1);
                        String room2 = matcher.group(2);

                        if(!roomsMap.containsKey(room1) || !roomsMap.containsKey(room2)) {
                            result = fail;
                        } else {
                            result = success;
                            IRoom r1 = roomsMap.get(room1);
                            IRoom r2 = roomsMap.get(room2);
                            r1.addNeighbour(r2);
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Room1: ").append(room1).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");
                        break;
                    }

                    case "mergeRooms": {
                        String room1 = matcher.group(1);
                        String room2 = matcher.group(2);
                        String mergedRoomID = "null";


                        if (!roomsMap.containsKey(room1) || !roomsMap.containsKey(room2)) {
                            result = fail;
                        } else {
                            result = success;
                            IRoom r1 = roomsMap.get(room1);
                            IRoom r2 = roomsMap.get(room2);
                            mergedRoomID = room1 + room2;
                            roomsMap.put(mergedRoomID, r1.mergeRooms(r2));
                            roomsMap.remove(room1);
                            roomsMap.remove(room2);
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Room1: ").append(room1).append("\n");
                        outputBuilder.append("Room2: ").append(room2).append("\n");
                        outputBuilder.append("MergedRoom: ").append(mergedRoomID).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");

                        break;
                    }

                    case "splitRoom": {
                        roomId = matcher.group(1);

                        String newRoomID1 = "null";
                        String newRoomID2 = "null";
                        if (!roomsMap.containsKey(roomId)) {
                            result = fail;
                        } else {
                            result = success;
                            IRoom room = roomsMap.get(roomId);
                            List<IRoom> newRooms = room.splitRoom();
                            newRoomID1 = roomId + "_split1";
                            newRoomID2 = roomId + "_split2";
                            roomsMap.remove(roomId);
                            roomsMap.put(newRoomID1, newRooms.get(0));
                            roomsMap.put(newRoomID2, newRooms.get(1));
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Room: ").append(roomId).append("\n");
                        outputBuilder.append("NewRoom1: ").append(newRoomID1).append("\n");
                        outputBuilder.append("NewRoom2: ").append(newRoomID2).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");
                        break;
                    }

                    case "getNeighbours": {
                        roomId = matcher.group(1);
                        String neighboursID = "";

                        IRoom room = roomsMap.get(roomId);
                        List<IRoom> neighbours = room.getNeighbours();
                        for (Map.Entry<String, IRoom> roomEntry : roomsMap.entrySet()) {
                            if (neighbours.contains(entry.getValue())) {
                                neighboursID += entry.getKey() + " ";
                            }
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Room: ").append(roomId).append("\n");
                        outputBuilder.append("Neighbours: ").append(neighboursID).append("\n");
                        break;
                    }
                    case "makeSticky": {
                        roomId = matcher.group(1);

                        if (!roomsMap.containsKey(roomId)) {
                            result = fail;
                        } else {
                            result = success;
                            IRoom room = roomsMap.get(roomId);
                            IRoom newRoom = room.makeSticky();
                            roomsMap.remove(roomId);
                            roomsMap.put(roomId, newRoom);
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Room: ").append(roomId).append("\n");
                        outputBuilder.append("Result: " + result).append("\n");

                        break;
                    }

                    case "list":
                        outputBuilder.append(commandName).append(":").append("\n");

                        // Room IDs
                        outputBuilder.append("Rooms: ");
                        for (Integer rooms : roomsMap.keySet()) {
                            outputBuilder.append(rooms).append(", ");
                        }
                        outputBuilder.append("\n");

                        // Character IDs
                        outputBuilder.append("Characters: ");
                        for (Integer characters : charactersMap.keySet()) {
                            outputBuilder.append(characters).append(", ");
                        }
                        outputBuilder.append("\n");

                        // Item IDs
                        outputBuilder.append("Items: ");
                        for (Integer items : itemsMap.keySet()) {
                            outputBuilder.append(items).append(", ");
                        }
                        outputBuilder.append("\n");


                    break;
                    case "roomStatus":
                        roomId = matcher.group(1);

                        // létezik-e a szoba
                        if (!roomsMap.containsKey(roomId)) {
                            outputBuilder.append("Room not found").append("\n");
                            break;
                        }

                        room = roomsMap.get(roomId);

                        // Szobatípus eldöntése - ha nem akarunk instanceof-ot, ez egy alternatív eldöntési módzser
                        if (roomId.startsWith("B")) {
                            roomType = "BasicRoom";
                        } else if (roomId.startsWith("C")) {
                            roomType = "CursedRoomDecorator";
                        } else if (roomId.startsWith("P")) {
                            roomType = "PoisonedRoomPoisonedRoomDecorator";
                        } else if (roomId.startsWith("S")) {
                            roomType = "StickyRoomDecorator";
                        } else {
                            roomType = "Unknown";
                        }

                        //Szoba karaktereiből kivesszük az ID-t
                        List<Character> charactersInRoom = room.getCharacters();
                        List<String> characterIds = new ArrayList<>();
                        for (Character c : charactersInRoom) {
                            // ID from the charactersMap
                            for (Map.Entry<Integer, Character> charEntry : charactersMap.entrySet()) {
                                if (charEntry.getValue().equals(c)) {
                                    characterIds.add(String.valueOf(charEntry.getKey()));
                                    break;  // Exit the loop after finding the ID
                                }
                            }
                        }
                        // Tárgyakból kivesszük az ID-t
                        List<Item> itemsInRoom = room.getItems();
                        List<String> itemIds = new ArrayList<>();
                        for (Item i : itemsInRoom) {
                            // item's ID from the itemsMap
                            for (Map.Entry<Integer, Item> itemEntry : itemsMap.entrySet()) {
                                if (itemEntry.getValue().equals(i)) {
                                    itemIds.add(String.valueOf(itemEntry.getKey()));
                                    break;  // Exit the loop after finding the ID
                                }
                            }
                        }

                        capacity = room.getCapacity();

                        outputBuilder.append("Room ID: ").append(roomId).append("\n");
                        outputBuilder.append("Room Type: ").append(roomType).append("\n");
                        outputBuilder.append("Characters: ").append(String.join(" ", characterIds)).append("\n");
                        outputBuilder.append("Items: ").append(String.join(" ", itemIds)).append("\n");
                        outputBuilder.append("Capacity: ").append(capacity).append("\n");
                        outputBuilder.append("Result: Successful").append("\n");
                        break;

                    case "characterStatus":
                        characterId = matcher.group(1);

                        // Check if the character exists in the charactersMap
                        if (!charactersMap.containsKey(characterId)) {
                            outputBuilder.append("Character not found").append("\n");
                            break;
                        }

                        character = charactersMap.get(characterId);

                        // karakter típus eldöntése
                        if (character instanceof Student) {
                            characterType = "Student";
                        } else if (character instanceof Instructor) {
                            characterType = "Instructor";
                        } else if (character instanceof Cleaner) {
                            characterType = "Cleaner";
                        } else {
                            characterType = "Unknown";
                        }

                        // Karakter tárgyai
                        List<Item> characterItemIds = character.getItems();
                        List<String> charitemIds = new ArrayList<>();
                        for (Item i : characterItemIds) {
                            // item's ID
                            for (Map.Entry<Integer, Item> itemEntry : itemsMap.entrySet()) {
                                if (itemEntry.getValue().equals(i)) {
                                    charitemIds.add(String.valueOf(itemEntry.getKey()));
                                    break;  // Exit the loop after finding the ID
                                }
                            }
                        }

                        String characterRoomId = "";
                        for (Map.Entry<Integer, IRoom> roomEntry : roomsMap.entrySet()) {
                                room = roomEntry.getValue();
                            if (room.getCharacters().contains(character)) {
                                characterRoomId = entry.getKey();
                                break;
                            }
                        }


                        boolean toxicated = character.getPoisoned();

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Character: ").append(characterId).append("\n");
                        outputBuilder.append("CharacterType: ").append(characterType).append("\n");
                        outputBuilder.append("Room: ").append(characterRoomId).append("\n");
                        outputBuilder.append("Items: ").append(String.join(", ", charitemIds)).append("\n");
                        outputBuilder.append("Toxicated: ").append(toxicated).append("\n");

                        break;
                    case "itemStatus":
                        itemId = matcher.group(1);

                        // Check if the item exists in the itemsMap
                        if (!itemsMap.containsKey(itemId)) {
                            outputBuilder.append("Item not found").append("\n");
                            break;
                        }

                        item = itemsMap.get(itemId);
                        if (item instanceof TVSZ) {
                            itemType = "TVSZ";
                        } else if (item instanceof AirFreshener) {
                            itemType = "AirFreshener";
                        } else if (item instanceof Camembert) {
                            itemType = "Camembert";
                        } else if (item instanceof Transistor) {
                            itemType = "Transistor";
                        } else if (item instanceof Logarlec) {
                            itemType = "Logarlec";
                        } else if (item instanceof FFP2) {
                            itemType = "FFP2";
                        } else if (item instanceof Beer) {
                            itemType = "Beer";
                        } else if (item instanceof Rag) {
                            itemType = "Rag";
                        } else {
                            throw new RuntimeException("Invalid item type");
                        }
                        String owner = "";
                        boolean isFake = item.isFake();

                        // A tárgy szobában vagy karakternél van (Id lesz kiírva stringként)
                        for (Map.Entry<Integer, Character> characterEntry : charactersMap.entrySet()) {
                            if (characterEntry.getValue().getItems().contains(item)) {
                                owner = String.valueOf(characterEntry.getKey()); // owned by a character
                                break;
                            }
                        }
                        for (Map.Entry<Integer, IRoom> roomEntry : roomsMap.entrySet()) {
                            if (roomEntry.getValue().getItems().contains(item)) {
                                owner = String.valueOf(roomEntry.getKey()); // placed in a room
                                break;
                            }
                        }

                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Item: ").append(itemId).append("\n");
                        outputBuilder.append("ItemType: ").append(itemType).append("\n");
                        outputBuilder.append("Owner/Room: ").append(owner).append("\n");
                        outputBuilder.append("Fake: ").append(isFake).append("\n");

                        break;



                    case "gameStatus":
                        outputBuilder.append(commandName).append(":").append("\n");
                        outputBuilder.append("Status: ").append(labyrinth.getGameState()).append("\n");

                        break;
                    case "nextRound":
                        labyrinth.nextRound();

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