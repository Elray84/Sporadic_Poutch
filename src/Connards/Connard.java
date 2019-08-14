/*
 * Copyright (c) 2019.
 * Author : Elian Raynaud
 * All Rights Reserved
 */

package Connards;
import Items.Item;
import utils.Position;

import java.util.ArrayList;

public class Connard {

    private String name;
    private int life;
    private int mana;
    private int attack;
    private int armor;
    private int magic_res;
    private int attack_speed;
    private int destin;
    private int lvl;
    private Position pos;
    private ArrayList<Item> myItems;
    private int rank;
    private boolean invisible;

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public boolean getInvisible() {
        return invisible;
    }

    public Connard(String name){
        // Just to test
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
