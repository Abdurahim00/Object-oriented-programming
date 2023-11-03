package assignment2.pokemon;

import java.util.Objects;

/**
 *
 * @author
 */

//Represent a skill of Pokemon
public class Skill {

    //attributes of a skill
    private final String name; //name
    private final int ap; //AP
    private final int ec; //EC

    //constructor of Skill class
    Skill(String name, int ap, int ec) {
        this.name = name;
        this.ap = ap;
        this.ec = ec;
    }

    //get ec
    public int getEC() {
        //return
        return this.ec;
    }

    //get name
    public String getName() {
        //return
        return this.name;
    }

    //get AP
    public int getAP() {
        //return 
        return this.ap;
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
        Skill obj = (Skill) o;
        // field comparison
        return obj.getName().equals(this.name) && ap==obj.getAP() && this.ec==obj.getEC();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + this.ap;
        hash = 23 * hash + this.ec;
        return hash;
    }

    @Override
    public String toString() {
        return name + " - AP: " + ap + " EC: " + ec;
    }
}
