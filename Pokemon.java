package assignment2;

import static java.lang.Math.round;
import java.util.Objects;

/**
 *
 * @author
 */
public class Pokemon {

    //Attributes of a pokemon
    private final Type type; //Type
    private final int max_hp;//max HP

    private String name; //Name
    private int hp; //HP
    private int ep;//EP
    private Skill skill; //Skill

    //Enum for type
    public enum Type {
        Fire, Water, Grass, Normal
    }

    //constructor of Pokemon class
    public Pokemon(String name, int hp, String type) {
        this.name = name;
        this.type = Type.valueOf(type);
        this.hp = hp;
        this.max_hp = hp;
        //set ep = 100
        this.ep = 100;
    }

    //Learn a Skill
    public void learnSkill(String name, int ap, int ec) {
        //create a new skill with the given info
        this.skill = new Skill(name, ap, ec);
    }

    //To check whether he know a skill or not
    public boolean knowsSkill() {
        //if pokemeon knows a skill, then it returns True
        //otherwise returns False
        return this.skill != null;
    }

    //Forget a skill
    public void forgetSkill() {
        //remove the skill
        this.skill = null;
    }

    //To receive a damage
    public void damage(int damage) {
        this.hp -= damage;//reduce hp
        //if hp goes below 0
        if (this.hp < 0) {
            this.hp = 0;//fainted
        }
    }

    //To heal the pokemon
    public void rest() {
        //increase hp
        //if it is not a fainted pokemon
        if (this.hp != 0) {
            this.hp += 20;
        }
        //if hp has exceed max HP, then set it to 100
        if (this.hp > this.max_hp) {
            this.hp = max_hp;
        }
    }

    //To spend energy
    public void spend() {
        this.ep -= this.skill.getEC();
        //if energy is gone below 0
        if (this.ep < 0) {
            this.ep = 0;
        }
    }

    //To restore energy
    public void recoverEnergy() {
        //if it is not a fainted pokemon
        if (this.hp != 0) {
            this.ep += 25;//increment by 25 EP
        }
        //if ep has exceeded 100, then set it to 100
        if (this.ep > 100) {
            this.ep = 100;
        }
    }

    //To heal a pokemon using an itme
    public String useItem(Item item) {
        
        //if hp is full
        if(hp==max_hp){
            return "Magikarp could not use Potion. HP is already full.";
        }
        
        //if cant use full portion
        if(hp+item.getHealingPower() > max_hp) {
            int used = max_hp-hp;
            hp = max_hp;
            return name + " used Potion. It healed "+(used)+" HP.";
        }
        
        //otherwise
        //increase hp
        //even it is  a fainted pokemon
        this.hp += item.getHealingPower();
        return name+" used Potion. It healed "+item.getHealingPower()+" HP.";
        
    }

    
    /*getters*/
    //get type
    public String getType() {
        //return type
        return this.type.toString();
    }

    //get HP
    public int getCurrentHP() {
        //return
        return this.hp;
    }

    //get Name
    public String getName() {
        //return name
        return this.name;
    }

    //get EP
    public int getEnergy() {
        //return
        return this.ep;
    }

    //get skill
    public Skill getSkill() {
        //return
        return this.skill;
    }
    
    //get max HP
    public int getMAX_HP() {
        return this.max_hp;
    }

    /*setters*/
    //set name
    public void setName(String name) {
        this.name = name;
    }

    
    @Override
    public String toString() {
        String string = name + " (" + type + ")";
        if (skill != null) {
            string = string + ". Knows " + skill.toString();
        }
        
        return string;
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
        Pokemon obj = (Pokemon) o;
        
        //check skills
        boolean skillEqual = (skill==null && obj.getSkill()==null) ? true : this.skill.equals(obj.getSkill());
        
        // field comparison
        return obj.getName().equals(this.name) && (obj.getCurrentHP()==this.hp) && (obj.getEnergy()==this.ep) && (obj.getMAX_HP()==this.max_hp) 
                && skillEqual && this.type.toString().equals(obj.getType());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.type);
        hash = 23 * hash + this.max_hp;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + this.hp;
        hash = 23 * hash + this.ep;
        hash = 23 * hash + Objects.hashCode(this.skill);
        return hash;
    }

    
    //To attck another Pokemon
    public String attack(Pokemon defender) {

        //When attack is failed
        //attacker is fainted
        if (getCurrentHP() == 0) {
            return "Attack failed. " + getName() + " fainted.";
            //target is fainted
        } else if (defender.getCurrentHP() == 0) {
            return "Attack failed. " + getName() + " fainted.";
            //attacker doesn't know skills 
        } else if (!knowsSkill()) {
            return "Attack failed. " + getName() + " attacker does not know a skll.";
            //Ep is not enough to attack
        } else if (getEnergy() < getSkill().getEC()) {
            return "Attack failed. " + getName() + " lacks energy: "
                    + getEnergy() + "/" + getSkill().getEC();
        }

        //Successfull attack
        String opt_effect;
        String opt_fainted = "";

        //attack
        opt_effect = damageTarget(getSkill().getAP(), defender);
        //spend enegry
        spend();
        //if target is fainted after the attack
        if (defender.getCurrentHP() == 0) {
            opt_fainted = " "+defender.getName() + " faints.";
        }

        return getName() + " uses " + getSkill().getName() + " on " + defender.getName() + "."
                + opt_effect + System.lineSeparator() + defender.getName() + " has " + defender.getCurrentHP() + " HP left." + opt_fainted;
    }
    
    //to damage a target
    //this is helper maethod for attack() method
    private String damageTarget(int damage, Pokemon target) {

        String effect = "";

        switch (this.type) {
            //attcks with fire type
            case Fire -> {
                switch (Type.valueOf(target.getType())) {
                    //if taget has type grass, then it is super effective
                    case Grass -> {
                        target.damage(damage * 2);
                        effect = " It is super effective!";
                    }
                    //it will be not very effective against fire and water types
                    case Fire, Water -> {
                        target.damage((int) (damage * 0.5));
                        effect = " It is not very effective...";
                    }
                    //otherwise
                    default ->
                        target.damage(damage);
                }
            }
            //attacks with water type
            case Water -> {
                switch (Type.valueOf(target.getType())) {
                    //if taget has type fire, then it is super effective
                    case Fire -> {
                        target.damage(damage * 2);
                        effect = " It is super effective!";
                    }
                    //it will be not very effective against grass and water types
                    case Grass, Water -> {
                        target.damage((int) (damage * 0.5));
                        effect = " It is not very effective...";
                    }
                    //otherwise
                    default ->
                        target.damage(damage);
                }
            }
            //attcks with grass type
            case Grass -> {
                switch (Type.valueOf(target.getType())) {
                    //if taget has type water, then it is super effective
                    case Water -> {
                        target.damage(damage * 2);
                        effect = " It is super effective!";
                    }
                    //it will be not very effective against grass and fire types
                    case Grass, Fire -> {
                        target.damage((int) (damage * 0.5));
                        effect = " It is not very effective...";
                    }
                    //otherwise
                    default ->
                        target.damage(damage);
                }
            }
            //attacks with normal type
            default -> //do it normally
                target.damage(damage);
        }
        //return effect     
        return effect;
    }


}
