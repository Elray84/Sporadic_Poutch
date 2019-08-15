/*
 * Copyright (c) 2019.
 * Author : Elian Raynaud
 * All Rights Reserved
 */

package Connards;
import Items.Item;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import utils.Position;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public abstract class Connard {

    protected String name;
    protected int health;
    protected int mana;
    protected int attack;
    protected int armor;
    protected int magic_res;
    protected float speed;
    protected int magic_damage;
    protected int destin;
    protected int lvl;
    protected Position pos;
    protected ArrayList<Item> myItems;
    protected int rank;
    protected boolean invisible;

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public boolean getInvisible() {
        return invisible;
    }

    @Override
    public String toString(){
        String res = this.name + "\n";
        res += "Health : " + this.health + "\n";
        res += "Price : " + this.rank + "\n";
        return res;
    }

    public static Connard getInstance(int lvl) throws FileNotFoundException {
        return null;
    }

}
