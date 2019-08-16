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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Connard {

    protected static final double SPEED = 0.6; // Attack per second

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
    protected int range;
    protected Position pos;
    protected ArrayList<Item> myItems;
    protected int rank;
    protected boolean invisible;
    public Connard target;
    public Position dest_target;

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public boolean isInvisible() {
        return invisible;
    }

    @Override
    public String toString(){
        String res = this.name + "\n";
        res += "Health : " + this.health + "\n";
        res += "Price : " + this.rank + "\n";
        res += this.pos + "\n";
        return res;
    }

    public void setTarget(ArrayList<Connard> enemies, ArrayList<Connard> allies){
        if(enemies.isEmpty()) return;
        ArrayList<Position> alliesPos = new ArrayList<Position>();
        for (Connard c:allies){
            alliesPos.add(c.pos);
        }
        ArrayList<Connard> new_enemies = this.sort(enemies);
        for (Connard connard:enemies){
            ArrayList<Position> surround = connard.getSurround();
            if (alliesPos.containsAll(surround) && this.range == 1){
                new_enemies.remove(connard);
            }
        }
        if(this.target.equals(new_enemies.get(0))){
            try{
                this.target = new_enemies.get(1);
            } catch (IndexOutOfBoundsException e){}
        } else{
            this.target = new_enemies.get(0);
        }
    }

    // TODO : range > 1 AND check if move is possible (static method in Map ?)
    public void reachTarget(ArrayList<Connard> allies){
        if (this.target == null) return;
        if (this.range == 1) {
            ArrayList<Position> reachable = this.target.getSurround();
            for (Connard ally : allies) {
                if (ally.target.equals(this.target)) {
                    reachable.remove(ally.dest_target);
                }
            }
            for (Position pos : reachable) {
                if (this.dest_target == null) this.dest_target = pos;
                else {
                    if (this.pos.getDistance(dest_target) > this.pos.getDistance(pos)) {
                        this.dest_target = pos;
                    }
                }
            }
        }
        if (Math.abs(this.dest_target.getX() - this.pos.getX()) > this.dest_target.getY() - this.pos.getY()){
            if (this.dest_target.getX() - this.pos.getX() > 0){
                this.pos.move_right();
            } else {
                if(this.dest_target.getX() - this.pos.getX() < 0) {
                    this.pos.move_left();
                }
            }
        } else {
            if (this.dest_target.getY() - this.pos.getY() > 0) {
                this.pos.move_down();
            } else {
                if (this.dest_target.getY() - this.pos.getY() < 0) {
                    this.pos.move_up();
                }
            }
        }
    }

    public ArrayList<Position> getSurround(){
        ArrayList<Position> surround = new ArrayList<Position>();
        surround.add(new Position(this.pos.getX(),this.pos.getY() + 1));
        surround.add(new Position(this.pos.getX(),this.pos.getY() - 1));
        surround.add(new Position(this.pos.getX() + 1, this.pos.getY()));
        surround.add(new Position(this.pos.getX() - 1, this.pos.getY()));
        return surround;
    }

    public static Connard getInstance(int lvl) throws FileNotFoundException {
        return null;
    }

    public double getDistance( Connard c){
        return this.pos.getDistance(c.pos);
    }

    public ArrayList<Connard> sort(ArrayList<Connard> connards){
        int n = 0;
        while (n != connards.size()-1){
            n = 0;
            for (int i=0; i<connards.size() - 1; i++){
                if(this.getDistance(connards.get(i)) <= this.getDistance(connards.get(i+1))){
                    n++;
                }
                else{
                    Collections.swap(connards, i, i+1);
                }
            }
        }
        return connards;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        ArrayList<Connard> connards = new ArrayList<Connard>();
//        Connard p1 = Perceval.getInstance(1);
//        Connard p2 = Perceval.getInstance(1);
//        Connard a1 = Arthur.getInstance(1);
//        p1.pos = new Position(1,2);
//        p2.pos = new Position(5,6);
//        a1.pos = new Position(2,0);
//        Connard monConnard = Arthur.getInstance(1);
//        monConnard.pos = new Position(0,0);
//        connards.add(p1);
//        connards.add(p2);
//        connards.add(a1);
//        System.out.println(connards);
//        System.out.println(monConnard.sort(connards));
        ArrayList<Integer> entiers = new ArrayList<Integer>();
        entiers.add(1);
        System.out.println(entiers.get(1));
    }
}


