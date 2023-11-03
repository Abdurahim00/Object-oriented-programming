package assignment2;

import java.util.ArrayList;

/**
 *
 * @author
 */
public class ItemBag {

    //Attributes
    private final double maximum_weight;
    private double currentWeight;
    private int numberOfItems;
    private ArrayList<Item> items;

    public double getCurrentWeight() {
        return currentWeight;
    }

    public int getNumOfItems() {
        return numberOfItems;
    }

    public double getMaxWeight() {
        return maximum_weight;
    }

    //constructor
    public ItemBag(double maxWeight) {
        this.maximum_weight = maxWeight;
        this.currentWeight = 0;
        this.numberOfItems = 0;
        this.items = new ArrayList<>();
    }

    //To add an item to the bag
    public int addItem(Item item) {

        //if there is no enough space
        if (item.getWeight() + this.currentWeight > this.maximum_weight) {
            return -1;//terminate
        }

        /*otherwise*/
        int index = 0;
        //if there are no itmes, just add the item
        if (items.isEmpty()) {
            items.add(item);
            //increase weight, and number of items
            this.currentWeight += item.getWeight();
            this.numberOfItems++;
            return 0;

            //otherwise, we should find the correct place to add the item
        } else {

            //ittereate throught the list
            for (index = 0; index < this.numberOfItems; index++) {
                //if this is the correct place to insert the item
                if (items.get(index).getWeight() <= item.getWeight()) {
                    items.add(index, item);
                    break;

                    //if this is the last item
                } else if (index == items.size() - 1) {
                    items.add(item);
                    index++;
                    break;
                }

            }
        }

        //increase weight, and number of items
        this.currentWeight += item.getWeight();
        this.numberOfItems++;
        return index;
    }

    //To remove an item from the bag
    public Item removeItemAt(int index) {
        //if index is out of bound
        if (index > this.numberOfItems - 1) {
            return null;//return null
        }

        //get item
        Item removed = items.get(index);
        //remove
        items.remove(index);

        //decrease the current weight, and number of items
        this.currentWeight -= removed.getWeight();
        this.numberOfItems--;

        //return
        return removed;
    }

    //To peak an item from the bag
    public String peekItemAt(int index) {
        //if index is out of bound
        if (index > this.numberOfItems - 1 || index < 0) {
            return "";
        }
        //return the element
        //as a string
        return items.get(index).toString();
    }

    //To pop out heavist item from the bag
    public Item popItem() {
        //if bag is empty
        if (items.isEmpty()) {
            return null;
        }

        //else
        Item removed = items.get(0);
        
        items.remove(0);
        currentWeight -= removed.getWeight();
        //return
        return removed;
    }

}
