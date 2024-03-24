package Rooms;

import Characters.Character;
import Game.Labyrinth;
import Items.Item;

import java.util.ArrayList;
import java.util.List;

public class BasicRoom implements IRoom{
    /**
     * The capacity of a room.
     */
    private int capacity;
    /**
     * The labyrinth object, where the rooms is located.
     */
    private Labyrinth labyrinth;
    /**
     * The adjacent rooms to the current room.
     */
    protected List<IRoom> neighbours;
    /**
     * The characters, that are currently inside the room.
     */
    protected List<Character> characters;

    /**
     * The items, which can be found inside the room.
     */
    protected List<Item> items;

    //Teszteléshez kell ures konstruktor, vagy tesztben mindenhol inicializaljuk rendesen a BasicRoomot

    /**
     * Creates a new BasicRoom object.
     */
    public BasicRoom(){
        labyrinth = new Labyrinth();
        characters = new ArrayList<>();
        this.capacity = 10;
    }

    /**
     * Creates a new, empty BasicRoom object
     * @param capacity Sets the capacity of the room.
     */
    public BasicRoom(int capacity){
        this.capacity = capacity;
    }
    public BasicRoom(int capacity, Labyrinth labyrinth){
        this.capacity = capacity;
        this.labyrinth = labyrinth;
    }

    /**
     * Checks, whether a room is adjacent to the current room.
     * @param nextRoom The checked room.
     * @return True, if the checked room is adjacent to the current room. False, if not.
     */
    public boolean isNeighbour(IRoom nextRoom){
        if(this.neighbours.contains(nextRoom)){
        System.out.println("NextRoom is neighbour: true | BasicRoom: isNeighbour");
        return true;}
        else {
            System.out.println("NextRoom is neighbour: false | BasicRoom: isNeighbour");
            return false;
        }
    }

    /**
     * Checks, whether a character can enter to the room.
     * @param character The character, willing to step inside the room.
     * @return True, if the characters can step into the room. False, if the rooms is already full.
     */
    public boolean acceptCharacter(Character character) {
        if(this.getCapacity()>=this.getNumberOfCharacters()) {
            System.out.println("Character accepted for nextRoom | BasicRoom: acceptCharacter()");
            characters.add(character);
            return true;
        }
        else {
            System.out.println("Character is not accepted because capacity is full | BasicRoom: acceptCharacter()");
            return false;
        }
    }

    /**
     * Removes a character from the room.
     * @param character The character to be removed.
     */
    public void removeCharacter(Character character) {
        System.out.println("Character is removed from currentRoom | BasicRoom: removeCharacter");
        characters.remove(character);
    }

    /**
     * Merges the current room with an adjacent IRoom object.
     * If neither of the two rooms contain characters, then the merge will be successful. The capacity of the merged room will be
     * the bigger rooms capacity and the items and neighbours are passed to it.
     * @param room The room, which will be merged with the current room.
     */
    @Override
    public void mergeRooms(IRoom room) {
        //id tagokat adnék a sima, mérgező és átkozott szobáknak, hogy ott is kivitelezhető legyen
        if(this.getCharacters().isEmpty() && room.getCharacters().isEmpty()){
            //egymás szomszédjai kölcsönösen
            if(this.getNeighbours().contains(room) && room.getNeighbours().contains(this)){
                System.out.println("Successfully merged");
                //a nagyobb kapacitás lesz beállítva az összevont szobának
                room.setCapacity(room.getCapacity()>this.getCapacity()?room.getCapacity():this.getCapacity());
                //tárgyak átadása
                for(int i=0; i<this.getItems().size(); i++){
                    room.addItem(this.getItems().get(i));
                    this.removeItem(this.getItems().get(i));
                }
                //szomszédok átadása
                for(int i = 0; i<this.getNeighbours().size(); i++){
                    room.addNeighbour(this.getNeighbours().get(i));
                    this.removeNeighbour(this.getNeighbours().get(i));
                }
                this.getLabyrinth().removeRoom(this);
            }
            System.out.println("Rooms can not be merged, because they are not neighbours | BasicRoom: mergeRooms");
        }
        else{
            System.out.println("Rooms can not be merged, because they contain characters | BasicRoom: mergeRooms");
        }
    }

    /**
     * Splits a room into two similar rooms. (If it does not contain any characters.) The items and neighbours are equally split.
     * The capacity remains the same and a new room is added to the labyrinth in case of a successful split.
     */
    public void splitRoom(){
        if(this.getCharacters().isEmpty()){

        BasicRoom newRoom = new BasicRoom();
        this.addNeighbour(newRoom);
        newRoom.addNeighbour(this);
        newRoom.setCapacity(this.getCapacity());

        //Szomszédok felének átadása az új szobának
        int halftheNeighbours = this.getNeighbours().size()/2;
        for(int i=0; i < halftheNeighbours; i ++){
            newRoom.addNeighbour(this.getNeighbours().get(i));
            this.removeNeighbour(this.getNeighbours().get(i));
        }
        //Itemek felének átadása az új szobának
        int halftheItems = this.getItems().size()/2;
        for(int i=0; i<halftheItems; i++){
            newRoom.addItem(this.getItems().get(i));
            this.removeItem(this.getItems().get(i));
        }
        this.getLabyrinth().addRoom(newRoom);

            System.out.println("Room splitted succesfully | BasicRoom: splitRoom");
        }
        else{
            System.out.println("Can not split room, because it contains characters | BasicRoom: splitRoom");
        }
    }

    /**
     * Returns the capacity of the current room.
     * @return The capacity.
     */
    public int getCapacity(){
        System.out.println("Capacity is queryed | BasicRoom: getCapacity");
        return capacity;
    }

    /**
     * Counts the characters in the current room.
     * @return The number of the characters.
     */
    public int getNumberOfCharacters(){
        System.out.println("Number of characters in the room is queryed! | BasicRoom: getNumberOfCharacters");
        return characters.size();
    }

    /**
     * Checks, whether a room has place available for characters.
     * @return True, if it does have place. False, if it does not.
     */
    public boolean hasPlace(){
        if(getCapacity()>getNumberOfCharacters()) {
            System.out.println("The room has free space | BasicRoom: hasPlace");
            return true;
        }
        else{
            System.out.println("The room is full | BasicRoom: hasPlace");
            return false;
        }
    }

    /**
     * Returns the characters in the room.
     * @return The characters in the room.
     */
    public List<Character> getCharacters(){
        return characters;
    }

    /**
     * Returns the adjacent rooms to the current room.
     * @return The adjacent rooms.
     */
    public List<IRoom> getNeighbours(){
        return this.neighbours;
    }

    /**
     * Returns the labyrinth, where the room exists.
     * @return The labyrinth object.
     */
    public Labyrinth getLabyrinth(){
        return this.labyrinth;
    }

    /**
     * Adds a new item to the items in the current room.
     * @param it The item to be added.
     */
    public void addItem(Item it){
        System.out.println("Added new item | BasicRoom: addItem");
        this.items.add(it);
    }

    /**
     * Removes an item from the current room
     * @param it The item to be removed.
     */
    public void removeItem(Item it){
        System.out.println("Removed item | BasicRoom: removeItem");
        this.items.remove(it);
    }

    /**
     * Sets the capacity of the current room.
     * @param i The new capacity.
     */
    public void setCapacity(int i){
        System.out.println("Capacity modified | BasicRoom: setCapacity");
        capacity = i;

    }

    /**
     * Adds a new neighbour to the current room.
     * @param room The room, which will be added as neighbour to the current room.
     */
    public void addNeighbour(IRoom room){
        System.out.println("Neighbour added | BasicRoom: addNeighbour");
        this.neighbours.add(room);
    }

    /**
     * A function to set neighbours to rooms.
     * @param room The list of rooms, where the current room will be added.
     */
    public void setNeighbours(List<IRoom> room){
        System.out.println("Neighbour set | BasicRoom: setNeighbour");
        room.add(this); //TODO rossz
    }

    /**
     * Removes a room from the adjacent rooms list.
     * @param room
     */
    public void removeNeighbour(IRoom room){
        System.out.println("Neighbour removed | BasicRoom: removeNeighbour");
        this.neighbours.remove(room);
    }

    /**
     * Adds a character to a room.
     * @param character
     */
    public void addCharacter(Character character){
        System.out.println("Character added | BasicRoom: addCharacter");
        characters.add(character);
    }
}
