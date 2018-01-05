import java.util.ArrayList;
import java.util.List;
/*
* Classes  defines the basic things, powerlevel, maxweight , current weight, spawn locations, current location, and inventory
*
*/
class Classes extends  Warrior{
    public int powerLevel;
    public float maxWeight;
    public int numMoves;
    public int finalScore=0;
    public Rooms currentLocation;
    public Rooms startLocation;
    public List<Items> Inventory = new ArrayList<>();
    boolean isRanger = false;
    int arrowCount = 0;

    public int currentWeight=0;

    public Classes(int powerLevel, float maxWeight, int numMoves) {
        this.powerLevel = powerLevel;
        this.maxWeight = maxWeight;
        this.numMoves = numMoves;
    }





}
