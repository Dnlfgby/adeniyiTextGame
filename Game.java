/*
* Daniel Fagbuyi
* Max Weller
* 8am
*
* Project 12 - Adventure Game Project, CSc 210, Spring 2017
* Essentially, reads in a config fill and builds a entire adventure gamebased on this config file.
*/


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Game {

    private TreeMap<String, Items> itemsDefined = new TreeMap<String,Items>(String.CASE_INSENSITIVE_ORDER);
    private static TreeMap<String,Rooms> roomsTreeMap = new TreeMap<String, Rooms>(String.CASE_INSENSITIVE_ORDER);
    private static TreeMap<String, Mobs> mobsDefined = new TreeMap<String, Mobs>(String.CASE_INSENSITIVE_ORDER);
    private int roomCount = 0;
    private Mobs newMob;
    private Items newTreasure;
    private Weapons newWeapon;
    private Rooms newRoom;
     private static String spawnRoomName;
    public static void main(String args[]) {
        //creates object to handle functions
        Functions fun = new Functions();
        //input scanner to read input
        Classes player = new Classes(0, 20, 0);
        Scanner input = new Scanner(System.in);
        Game driver = new Game();
        driver.readInGameFile(args[0]);
        System.out.println("Welcome to the adventure!\nWhat class would you like to play as (Warrior, Dwarf, or Ranger)?");
        boolean classSelected = false;
        boolean REPL = false;
        while (!classSelected) {
            String classSelection = input.nextLine().toUpperCase();
            switch (classSelection) {
                case "WARRIOR":
                    classSelection = "WAR";
                    player.powerLevel += 20;
                    classSelected = true;
                    break;
                case "DWARF":
                    classSelection = "DWA";
                    player.maxWeight += 10;
                    classSelected = true;
                    break;
                case "RANGE":
                    classSelection = "RAN";
                    classSelected = true;
                    player.isRanger=true;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

        }
        if(roomsTreeMap.containsKey(spawnRoomName.trim())){
            //spawns player in the first location;
            player.startLocation= roomsTreeMap.get(spawnRoomName.trim());
            player.currentLocation = roomsTreeMap.get(spawnRoomName.trim());
            System.out.println("Starting the adventure...");
        }
        REPL = true;
        // begin REPL - seperates string by various thing
        while(REPL){
            String UserInput = input.nextLine().toUpperCase();
            String [] commands = UserInput.split("\\s+");
            switch(commands[0]) {
                case "LOOK":
                    player.arrowCount++;
                    for(int i = 0; i < player.arrowCount;i++){
                        System.out.print("> ");
                    }
                    fun.look(player);
                    player.arrowCount=0;
                    break;
                case "EXAMINE":
                    player.arrowCount++;
                    for(int i = 0; i < player.arrowCount;i++){
                        System.out.print("> ");
                    }
                    player.arrowCount=0;
                    if (commands.length > 2) {
                        if (UserInput.contains("MOB")) {
                            String[] UI = UserInput.split("MOB");
                            fun.examine(player, commands[1].trim(), UI[1].trim());
                        } else if (UserInput.contains("ITEM")) {
                            String[] UI = UserInput.split("ITEM");
                            fun.examine(player, commands[1].trim(), UI[1].trim());
                        } else {
                            System.out.println("Invalid command.");
                        }
                        break;
                    }
                    break;
                case "MOVE":
                    player.arrowCount++;
                    if (commands.length > 1) {
                        if (UserInput.contains("EAST")) {
                            fun.move(player, commands[1]);
                            break;
                        }
                        if (UserInput.contains("WEST")) {
                            fun.move(player, commands[1]);
                            break;
                        }
                        if (UserInput.contains("SOUTH")) {
                            fun.move(player, commands[1]);
                            break;

                        }
                        if (UserInput.contains("NORTH")) {
                            fun.move(player, commands[1]);
                            break;
                        }
                    }
                    break;
                case "PICKUP":
                    player.arrowCount++;
                    if (commands.length > 1) {
                        if (UserInput.contains("PICKUP")) {
                            String[] UI = UserInput.split("PICKUP");
                            fun.pickup(player, UI[1].trim());
                        } else {
                            for(int i = 0; i < player.arrowCount;i++){
                                System.out.print("> ");
                            }
                            player.arrowCount=0;
                            System.out.println("Invalid command.");
                        }
                    }
                    break;
                case "DROP":
                    player.arrowCount++;
                   if(commands.length>1) {
                    if(UserInput.contains("DROP")){
                        String[] UI = UserInput.split("DROP");
                        fun.dropItem(player, UI[1].trim());
                    }else{
                        for(int i = 0; i < player.arrowCount;i++){
                            System.out.print("> ");
                        }
                        player.arrowCount=0;
                        System.out.println("Invalid command.");
                    }

                   }
                   break;
                case "STASH":
                   player.arrowCount++;
                    if(commands.length>1){
                        if(UserInput.contains("STASH") && player.currentLocation.spawnRoomOrNot){
                            String[] UI = UserInput.split("STASH");
                            fun.stash(player, UI[1].trim());
                        }else{
                            for(int i = 0; i < player.arrowCount;i++){
                                System.out.print("> ");
                            }
                            player.arrowCount=0;
                            System.out.println("Invalid command.");
                        }
                    }else{
                        for(int i = 0; i < player.arrowCount;i++){
                            System.out.print("> ");
                        }
                        player.arrowCount=0;
                        System.out.println("Invalid command.");
                    }
                    break;
                case "FIGHT":
                    player.arrowCount++;
                    for(int i = 0; i < player.arrowCount;i++){
                        System.out.print("> ");
                    }
                    player.arrowCount=0;
                    if(commands.length>1){
                        if(UserInput.contains("FIGHT")){
                            if(player.currentLocation.mobsInRoom.size()>0) {
                                String[] UI = UserInput.split("FIGHT");
                                fun.fight(player, UI[1].trim());
                            }
                        }else{
                            for(int i = 0; i < player.arrowCount;i++){
                                System.out.print("> ");
                            }
                            player.arrowCount=0;
                            System.out.println("Invalid command.");
                        }
                    }
                    break;
                case "INVENTORY":
                    player.arrowCount++;
                    if(player.Inventory.size()>0) {
                        fun.inventory(player.Inventory, player);
                    }
                    break;
                case "QUIT":
                    player.arrowCount++;
                    for(int i = 0; i < player.arrowCount;i++){
                        System.out.print("> ");
                    }
                    player.arrowCount=0;
                    fun.calculate(player);
                    System.exit(0);
                    REPL=false;
                    break;
                default:
                    player.arrowCount++;
                    for(int i = 0; i < player.arrowCount;i++){
                        System.out.print("> ");
                    }
                    player.arrowCount=0;
                    System.out.println("Invalid command.");
            }






















        }
        // player selected character;

    }
    /*
* readInGameFile(String)
* Parses game file from standard in
*
* Arguments:
* String Textfile- name of textfile you wish to read in
* return void , parses directly into hashmaps
*/

    private void readInGameFile(String textFile){
        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            String line;
            String type="END";
            String[] items;
            String[] Mobs;
            String[] directions;
             while ((line = br.readLine()) != null) {
                //define grabs the type name in a array;
                //values grabs the values in array;
                String[] define = line.split("\\s+");
                String[] values = line.split(":");
                if(define[0].equals("define")){
                    type = define[1].toUpperCase();
                }
                if(type.equals("MOB")){
                    if(values[0].contains("name")){
                        newMob = new Mobs();
                        newMob.setName(values[1].trim());
                        //  add name to the mob
                    }
                    if(values[0].contains("description")){
                        newMob.setDescription(values[1]);
                        // add description
                    }
                    if(values[0].contains("power")){
                        newMob.setPowerLevel(Integer.parseInt(values[1].replaceAll("\\s+","")));
                        // add power
                        if(mobsDefined.containsKey(newMob.name)){
                            System.err.println("Duplicate define, exiting with ERROR STATUS OF 1");
                            System.exit(1);
                        }
                        newMob.name = newMob.name.trim();
                        mobsDefined.put(newMob.name,newMob);
                    }

                }
                if(type.equals("TREASURE")){
                    if(values[0].contains("name")){
                        newTreasure = new Items();
                        newTreasure.setWeapon(false);
                        newTreasure.setItemName(values[1]);
                        //  add name to the mob
                    }
                    if(values[0].contains("weight")){
                        newTreasure.setItemWeight(Float.parseFloat(values[1].replaceAll("\\s+","")));
                        // add description
                    }
                    if(values[0].contains("value")){
                        newTreasure.setValue(Integer.parseInt(values[1].replaceAll("\\s+","")));
                        // add power
                        newTreasure.setWeapon(false);
                        if(itemsDefined.containsKey(newTreasure.itemName)){
                            System.err.println("Duplicate define, exiting with ERROR STATUS OF 1");
                            System.exit(1);
                        }
                        newTreasure.itemName = newTreasure.itemName.trim();
                        itemsDefined.put(newTreasure.itemName,newTreasure);
                    }

                }
                if(type.equals("ROOM")){
                    if(values[0].contains("name")){
                        //  add name to the room
                        newRoom = new Rooms();
                        newRoom.setName(values[1]);
                        if(roomCount==0){
                            newRoom.spawnRoomOrNot = true;
                            spawnRoomName = newRoom.getName().trim();
                            roomCount++;
                        }else{
                            newRoom.spawnRoomOrNot = false;
                        }

                    }
                    if(values[0].contains("description")){
                        newRoom.setDescription(values[1]);
                        // add description
                    }
                    if(values[0].contains("items")){
                     items = values[1].split(",");
                        for(int i = 0; i < items.length; i++){
                            if(itemsDefined.containsKey(items[i].trim())){
                                newRoom.addItems(itemsDefined.get(items[i].trim()));
                            }
                        }
                        //handle items
                    }
                    if(values[0].contains("mob")){
                            if(mobsDefined.containsKey(values[1].trim())){
                                newRoom.addMobs(mobsDefined.get(values[1].trim()));
                            }
                        newRoom.name = newRoom.name.trim();
                        roomsTreeMap.put(newRoom.name, newRoom);
                        // add power
                    }
                }
                if(type.equals("WEAPON")){
                    if(values[0].contains("name")){
                        newWeapon = new Weapons();
                        newWeapon.setWeapon(true);
                        newWeapon.setItemName(values[1]);
                        //  add name to the room
                    }
                    if(values[0].contains("weight")){
                        newWeapon.setItemWeight(Float.parseFloat(values[1].replaceAll("\\s+","")));
                        // add description
                    }
                    if(values[0].contains("damage")){
                        newWeapon.setValue(Integer.parseInt(values[1].replaceAll("\\s+","")));
                        //handle items
                        if(itemsDefined.containsKey(newWeapon.itemName)){
                            System.err.println("Duplicate define, exiting with ERROR STATUS OF 1");
                            System.exit(1);
                        }
                        newWeapon.itemName = newWeapon.itemName.trim();
                        itemsDefined.put(newWeapon.itemName, newWeapon);
                    }

                }
                 if (line.contains("EAST")) {
                     directions = line.split("EAST");
                     if(roomsTreeMap.containsKey(directions[0].trim())&& roomsTreeMap.containsKey(directions[1].trim())){
                         roomsTreeMap.get(directions[0].trim()).roomToEast = roomsTreeMap.get(directions[1].trim());
                         roomsTreeMap.get(directions[0].trim()).connectingRooms.add(roomsTreeMap.get(directions[1].trim()));

                     }
                 } else if (line.contains("WEST")) {
                     directions = line.split("WEST");
                     if(roomsTreeMap.containsKey(directions[0].trim())&& roomsTreeMap.containsKey(directions[1].trim())){
                         roomsTreeMap.get(directions[0].trim()).roomToWest = roomsTreeMap.get(directions[1].trim());
                         roomsTreeMap.get(directions[0].trim()).connectingRooms.add(roomsTreeMap.get(directions[1].trim()));
                     }
                 } else if (line.contains("SOUTH")) {
                     directions = line.split("SOUTH");
                     if(roomsTreeMap.containsKey(directions[0].trim())&& roomsTreeMap.containsKey(directions[1].trim())){
                         roomsTreeMap.get(directions[0].trim()).roomToSouth = roomsTreeMap.get(directions[1].trim());
                         roomsTreeMap.get(directions[0].trim()).connectingRooms.add(roomsTreeMap.get(directions[1].trim()));
                     }
                 } else if (line.contains("NORTH")) {
                     directions = line.split("NORTH");
                     if(roomsTreeMap.containsKey(directions[0].trim())&& roomsTreeMap.containsKey(directions[1].trim())){
                         roomsTreeMap.get(directions[0].trim()).roomToNorth = roomsTreeMap.get(directions[1].trim());
                         roomsTreeMap.get(directions[0].trim()).connectingRooms.add(roomsTreeMap.get(directions[1].trim()));
                     }
                 }

            }
        } catch (IOException e) {
            System.err.println("Error, invalid text file");
            System.exit(1);
            e.printStackTrace();
        }

    }

}

