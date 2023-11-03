package assignment2;

/**
 *
 * @author
 */
public class Item {
    
    //attributes
    private final String name;//name of the item
    private final int healingPower;//healing power
    private final double weight;//weight of the item
    
    //Constructor
    public Item(String name, int healingPow, double weight) {
        this.name = name;
        this.healingPower = healingPow;
        this.weight = weight;
    }
    
    //get healing power
    //get Name
    public String getName() {
        //return
        return this.name;
    }
    
    public int getHealingPower() {
        //return
        return this.healingPower;
    }
    //get weight
    public double getWeight() {
        //return
        return this.weight;
    }
    
        //equals method
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (o.getClass() != getClass()) {
            return false;
        }
        Item obj = (Item) o;
        // field comparison
        return obj.getName().equals(this.name) && (obj.getWeight()==this.weight) 
                && (obj.getHealingPower()==this.healingPower);
    }
    
    //toString() method
    //to print an Item
    @Override
    public String toString() {
        return this.name +" heals " + this.healingPower + " HP. (" + String.format("%.2f", (Math.floor(this.weight * 100)/100)) + ")";
    }
}
