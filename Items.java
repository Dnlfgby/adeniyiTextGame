class Items {
    /*
* Items class - Creates a item class with it's basic configurations. -
*
*/
    public int value;
    public String itemName;
    public float itemWeight;
    private boolean weapon=false;


    public void setWeapon(boolean weapon) {
        this.weapon = weapon;
    }


    public boolean isWeapon() {
        return weapon;
    }

    public Items() {
        this.itemName = itemName;
        this.itemWeight = itemWeight;
        this.value = value;
    }

    public float getItemWeight() {
        return itemWeight;
    }

    public int getValue() {
        return value;
    }


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemWeight(float itemWeight) {
        this.itemWeight = itemWeight;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
