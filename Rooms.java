import java.util.ArrayList;
import java.util.List;

public class Rooms {
     public String name;
     public String description;
     public List<Items> itemsInRoom = new ArrayList<>();
     public List<Mobs> mobsInRoom = new ArrayList<>();
     public List<Rooms> connectingRooms = new ArrayList<>();
     public int itemCount=0;
     public Rooms roomToSouth=null;

    public boolean spawnRoomOrNot;

    public void addItems(Items add){
        itemsInRoom.add(add);
    }
    public void addMobs(Mobs add){
        mobsInRoom.add(add);
    }


    public Rooms roomToNorth=null;
     public Rooms roomToEast=null;
     public Rooms roomToWest=null;

    public Rooms() {
        this.name = name;
        this.description = description;
        this.itemsInRoom = itemsInRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
