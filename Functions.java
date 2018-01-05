import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Functions {
     /*
   *
   * this is a assist've method used to search the room for Mobs and various objects and print out properly to stdout.
   */

    private void sortAndPrint(List<Items> zz) {
        //takes the items and sorts them alphabetically by first coverting hte array to array of strings;
        ArrayList<String> array = new ArrayList<>();
        for (int i = 0; i < zz.size(); i++) {
            array.add(zz.get(i).itemName);
        }
        Collections.sort(array, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < array.size(); i++) {
            System.out.print(" " + array.get(i));
            if(i<array.size()-1){
                System.out.print(",");
            }
        }
        System.out.println();
    }
 /*
   *
   * this is a assist've method used to search the room for Mobs and various objects and print out properly to stdout.
   */

    private void sortAndPrintMobs(List<Mobs> zz) {
        //takes the mobs and sorts them alphabetically by first coverting the array to array of strings;
        ArrayList<String> array = new ArrayList<>();
        for (int i = 0; i < zz.size(); i++) {
            array.add(zz.get(i).name);
        }
        Collections.sort(array, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < array.size(); i++) {
            System.out.print(" " + array.get(i));
        }
        System.out.println();
    }
     /*
   *
   * this is a assist've method used to search the room for Mobs and various objects and print out properly to stdout.
   */

    private void searchItems(List<Items> zz, String itemName) {
        //search for items within the list given and prints out if its a weapon or item
        itemName = itemName.toUpperCase();
        ArrayList<String> array = new ArrayList<>();
        boolean itemFound = false;
        for (int i = 0; i < zz.size(); i++) {
            array.add(zz.get(i).itemName);
            if (zz.get(i).itemName.trim().toUpperCase().equals(itemName.trim())) {
                if (zz.get(i).isWeapon()) {
                    System.out.println("Name: " + zz.get(i).itemName);
                    System.out.println("Weight: " + zz.get(i).getItemWeight());
                    System.out.println("Power: " + zz.get(i).getValue());
                    itemFound = true;
                }
                if (!zz.get(i).isWeapon()) {
                    System.out.println("Name: " + zz.get(i).itemName);
                    System.out.println("Weight: " + zz.get(i).getItemWeight());
                    System.out.println("Value: " + zz.get(i).getValue());
                    itemFound = true;
                }
            }

        }
        if (!itemFound) {
            System.out.println("Invalid command");
        }

    }
    /*
   *
   * this is a assist've method used to search the room for Mobs and various objects and print out properly to stdout.
   */

    private void searchMobs(List<Mobs> zz, String mobName) {
        //takes the items and sorts them alphabetically by first coverting hte array to array of strings;
        ArrayList<String> array = new ArrayList<>();
        mobName = mobName.toUpperCase();
        boolean mobFound = false;
        for (int i = 0; i < zz.size(); i++) {
            array.add(zz.get(i).name);
            if (zz.get(i).name.toUpperCase().trim().equals(mobName.trim())) {
                System.out.println("Name: " + zz.get(i).name);
                System.out.println("Description: " + zz.get(i).getDescription().trim());
                mobFound = true;
            }

        }
        if (!mobFound) {
            System.out.println("Invalid command.");
        }
    }
    /*
   *
   * This command allows you to examine mobs and items
   *  in a room. TYPE may be either “mob” or “item” (case insensitive). NAME is then
   *   the name of the mob or item we would like to examine.
   */

    public void look(Classes player) {
        System.out.println("Name: " + player.currentLocation.getName());
        System.out.println("Description: " + player.currentLocation.description.trim());
        System.out.print("There are connections in the following directions: ");
        if (player.currentLocation.roomToEast != null) {
            System.out.print("EAST");
            if (player.currentLocation.roomToNorth != null) {
                System.out.print(", ");
            }else{
                if (player.currentLocation.roomToSouth != null) {
                    System.out.print(", ");
                }else{
                    if (player.currentLocation.roomToWest != null) {
                        System.out.print(", ");
                    }
                }
            }

        }
        if (player.currentLocation.roomToNorth != null) {
            System.out.print("NORTH");
            if (player.currentLocation.roomToSouth != null) {
                System.out.print(", ");
            }else{
                if (player.currentLocation.roomToWest != null) {
                    System.out.print(", ");
                }
            }
        }
        if (player.currentLocation.roomToSouth != null) {
            System.out.print("SOUTH");
            if (player.currentLocation.roomToWest != null) {
                System.out.print(", ");
            }
        }
        if (player.currentLocation.roomToWest != null) {
            System.out.print("WEST");

        }
        System.out.println();
        // prints the directions in the order if they aren't null;
        if (player.currentLocation.itemsInRoom.size() == 0) {
            System.out.println("Items: none");
        } else {
            System.out.print("Items:");
            sortAndPrint(player.currentLocation.itemsInRoom);
        }
        if (player.currentLocation.mobsInRoom.size() == 0) {
            System.out.println("Mob: none");
        } else {
            System.out.print("Mob:");
            sortAndPrintMobs(player.currentLocation.mobsInRoom);
        }

    }

    /*
   *
   * This command allows you to examine mobs and items
   *  in a room. TYPE may be either “mob” or “item” (case insensitive). NAME is then
   *   the name of the mob or item we would like to examine.
   */
    public void examine(Classes player, String type, String name) {
        if (type.equals("MOB")) {
            // if the type is MOBS
            searchMobs(player.currentLocation.mobsInRoom, name.trim());
        } else if (type.equals("ITEM")) {
            searchItems(player.currentLocation.itemsInRoom, name.trim());
        } else {
            for(int i = 0; i < player.arrowCount;i++){
                System.out.print("> ");
            }
            player.arrowCount=0;
            System.out.println("Invalid command");
        }


    }
    /*
   *
   * This command allows the user to move the character in whatever direction
   * speciefied.
   */
    public void move(Classes player, String direction) {
        direction = direction.toUpperCase();
        switch (direction) {
            case "NORTH":
                if (player.currentLocation.roomToNorth != null) {
                    player.currentLocation = player.currentLocation.roomToNorth;
                    player.numMoves++;
                    break;
                } else {
                    for(int i = 0; i < player.arrowCount;i++){
                        System.out.print("> ");
                    }
                    player.arrowCount=0;
                    System.out.println("Invalid direction.");
                    break;
                }
            case "SOUTH":
                if (player.currentLocation.roomToSouth != null) {
                    player.currentLocation = player.currentLocation.roomToSouth;
                    player.numMoves++;
                    break;
                } else {
                    for(int i = 0; i < player.arrowCount;i++){
                        System.out.print("> ");
                    }
                    player.arrowCount=0;
                    System.out.println("Invalid direction.");
                    break;
                }
            case "WEST":
                if (player.currentLocation.roomToWest != null) {
                    player.currentLocation = player.currentLocation.roomToWest;
                    player.numMoves++;
                    break;
                } else {
                    for(int i = 0; i < player.arrowCount;i++){
                        System.out.print("> ");
                    }
                    player.arrowCount=0;
                    System.out.println("Invalid direction.");
                    break;
                }
            case "EAST":
                if (player.currentLocation.roomToEast != null) {
                    player.currentLocation = player.currentLocation.roomToEast;
                    player.numMoves++;
                    break;
                } else {
                    for(int i = 0; i < player.arrowCount;i++){
                        System.out.print("> ");
                    }
                    player.arrowCount=0;
                    System.out.println("Invalid direction.");
                    break;
                }
            default:
                for(int i = 0; i < player.arrowCount;i++){
                    System.out.print("> ");
                }
                player.arrowCount=0;
                System.out.println("Invalid direction.");
        }

    }
    /*
   *
   * This command allows you pick up an item located within the current location of the room the player is within
   *
   */
    public void pickup(Classes player, String itemName) {
        //takes the items and sorts them alphabetically by first coverting hte array to array of strings;
        boolean itemFound= false;
        ArrayList<String> array = new ArrayList<>();
        itemName = itemName.toUpperCase();
        for (int i = 0; i < player.currentLocation.itemsInRoom.size(); i++) {
            array.add(player.currentLocation.itemsInRoom.get(i).itemName);
            if (player.currentLocation.itemsInRoom.get(i).itemName.toUpperCase().trim().equals(itemName.trim())) {
                if (player.currentLocation.mobsInRoom.size() == 0) {
                    if (player.currentLocation.itemsInRoom.get(i).getItemWeight() > (player.maxWeight - player.currentWeight)) {
                        for(int z = 0; z < player.arrowCount;z++){
                            System.out.print("> ");
                        }
                        player.arrowCount=0;
                        System.out.println("I cannot carry this item. ");
                        itemFound=true;
                        break;
                    } else {
                        player.currentWeight += player.currentLocation.itemsInRoom.get(i).itemWeight;
                        player.Inventory.add(player.currentLocation.itemsInRoom.get(i));
                        player.currentLocation.itemsInRoom.remove(i);
                        itemFound = true;
                    }

                } else {
                    for(int z = 0; z < player.arrowCount;z++){
                        System.out.print("> ");
                    }
                    player.arrowCount=0;
                    System.out.println("You must destroy the mob first.");
                    itemFound = true;
                    break;

                }
            }
        }
           if(itemFound==false){
               for(int z = 0; z < player.arrowCount;z++){
                   System.out.print("> ");
               }
               player.arrowCount=0;
               System.out.println("Invalid item.");
           }
    }
    /*
   *
   * This command allows the player to drop an object located within the current room.
   */
    public void dropItem(Classes player, String itemName) {
        itemName = itemName.toUpperCase();
        ArrayList<String> array = new ArrayList<>();
        boolean itemFound = false;
        for (int i = 0; i < player.Inventory.size(); i++) {
            array.add(player.Inventory.get(i).itemName);
            if (player.Inventory.get(i).itemName.trim().toUpperCase().equals(itemName.trim())) {
                player.currentWeight -= player.Inventory.get(i).getItemWeight();
                player.currentLocation.itemsInRoom.add(player.Inventory.get(i));
                player.Inventory.remove(i);
                itemFound = true;
                break;
            }
        }
        if (!itemFound) {
            for(int i = 0; i < player.arrowCount;i++){
                System.out.print("> ");
            }
            player.arrowCount=0;
            System.out.println("Invalid item.");
        }
    }
    /*
   *
   * This command allows the player to stash objects within the statsh room which is the start location of the game
   */
    public void stash(Classes player, String itemName) {
        itemName = itemName.toUpperCase();
        ArrayList<String> array = new ArrayList<>();
        boolean itemFound = false;
        for (int i = 0; i < player.Inventory.size(); i++) {
            array.add(player.Inventory.get(i).itemName);
            if (player.Inventory.get(i).itemName.trim().toUpperCase().equals(itemName.trim())) {
                player.currentWeight -= player.Inventory.get(i).getItemWeight();
                player.finalScore+=player.Inventory.get(i).value;
                player.Inventory.remove(i);
                itemFound = true;
                break;
            }
        }
        if (!itemFound) {
            for(int i = 0; i < player.arrowCount;i++){
                System.out.print("> ");
            }
            player.arrowCount=0;
            System.out.println("Invalid item.");
        }
    }
    /*
   *
   * This command allows the player to fight agaisnt the mob located within the room
   * by selecting the item name they would like to use agaisnt it.
   */
    public void fight(Classes player, String itemName) {
        itemName = itemName.toUpperCase();
        ArrayList<String> array = new ArrayList<>();
        boolean itemFound = false;
        for (int i = 0; i < player.Inventory.size(); i++) {
            array.add(player.Inventory.get(i).itemName);
            if (player.Inventory.get(i).itemName.trim().toUpperCase().equals(itemName.trim()) && player.Inventory.get(i).isWeapon()) {
                itemFound = true;
                if (player.Inventory.get(i).value+player.powerLevel > player.currentLocation.mobsInRoom.get(0).powerLevel) {
                    player.numMoves++;
                    System.out.println("You destroyed " + player.currentLocation.mobsInRoom.get(0).name + " with power level " + player.currentLocation.mobsInRoom.get(0).powerLevel + "!");
                    player.currentLocation.mobsInRoom.remove(0);
                } else {
                    player.numMoves++;
                    System.out.println(player.currentLocation.mobsInRoom.get(0).name + " defeated you! Try a different weapon.");
                }
                break;
            }
        }
        if (!itemFound) {
            System.out.println("Invalid weapon.");
        }

    }

    public void inventory(List<Items> zz,Classes player) {
        for(int i = 0; i < player.arrowCount;i++){
            System.out.print("> ");
        }
        player.arrowCount=0;
        if (zz.size() > 0) {
            Collections.sort(zz, new Comparator<Items>() {
                @Override
                public int compare(Items item1, Items object2) {
                    return item1.itemName.compareTo(object2.itemName);
                }
            });
        }
        for (int i = 0; i < zz.size(); i++) {
            if (zz.get(i).isWeapon()) {
                System.out.println("Name: " + zz.get(i).itemName);
                System.out.println("Weight: " + zz.get(i).getItemWeight());
                System.out.println("Power: " + zz.get(i).getValue());
                System.out.println();
            }
            if (!zz.get(i).isWeapon()) {
                System.out.println("Name: " + zz.get(i).itemName);
                System.out.println("Weight: " + zz.get(i).getItemWeight());
                System.out.println("Value: " + zz.get(i).getValue());
                System.out.println();
            }
        }


    }
    /*
   *
   * This command allows calculates the final score and divdes by two if the user is a ranger
   */
    public void calculate(Classes player) {
        for (int i = 0; i < player.startLocation.itemsInRoom.size(); i++) {
            if(!(player.startLocation.itemsInRoom.get(i).isWeapon())) {
              player.finalScore+=player.startLocation.itemsInRoom.get(i).value;
            }
        }
        if(player.isRanger){
         player.numMoves/=2;
        }
        if(player.finalScore!=0)
        player.finalScore/=player.numMoves;
        for(int i = 0; i < player.arrowCount;i++){
            System.out.print("> ");
        }
        System.out.println("Finishing game...");
        System.out.println("Final score: " +player.finalScore);
}









}
