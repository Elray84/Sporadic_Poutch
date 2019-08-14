/*
 * Copyright (c) 2019.
 * Author : Elian Raynaud
 * All Rights Reserved
 */

package utils;public class Position {

    private int x;
    private int y;
    private static int xLeftBound = 0;
    private static int xRightBound = 15; // TODO
    private static int yUpperBound = 0;
    private static int yLowerBound = 15; // TODO

    public void move_left(){
        if(x - 1 >= xLeftBound){
           x --;
        }
    }

    public void move_right(){
        if(x + 1 >= xRightBound){
            x ++;
        }
    }

    public void move_up(){
        if(y - 1 >= yUpperBound){
            y --;
        }
    }

    public void move_down(){
        if(y + 1 >= yLowerBound){
            y ++;
        }
    }

}
