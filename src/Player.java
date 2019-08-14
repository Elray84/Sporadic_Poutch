/*
 * Copyright (c) 2019.
 * Author : Elian Raynaud
 * All Rights Reserved
 */

import Connards.Connard;

import java.util.ArrayList;

public class Player {

    private String name;
    private int life;
    private int gold;
    private int xp;
    private int lvl;
    private ArrayList<Connard> myConnards;
    private Shop myShop;
    private Map myMap;
    private Player opponent;

    public Player(int life, int gold, int xp, int lvl, Shop myShop, Map myMap) {
        this.life = life;
        this.gold = gold;
        this.xp = xp;
        this.lvl = lvl;
        this.myShop = myShop;
        this.myMap = myMap;
    }

    public String toString(){
        String str_result = name + " :\n[\n";
        str_result += "Life : " + life + "/100,\n";
        str_result += "Gold : " + gold + " pièces d'or,\n";
        str_result += "Level " + life +",\n";
        str_result += "Experience : " + xp + "xp,\n";
        str_result += "Connards : " + myConnards.toString() + ",\n";
        return str_result;
    }

}
