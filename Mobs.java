class Mobs {
    /*
* Mob class - Creates a mob class with it's basic configurations. - Name, Description , Power Level
*
*/
    public String name;
    private String description;
    public int  powerLevel;

    public Mobs() {
        this.name = name;
        this.description = description;
        this.powerLevel = powerLevel;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }
}
