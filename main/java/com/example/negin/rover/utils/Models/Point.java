package com.example.negin.rover.utils.Models;

/**
 * Created by Negin on 09/12/2018.
 */

public class Point {
    private int x;
    private int y;
    public Point(){}
     public Point(int x,int y){
         this.setX(x);
         this.setY(y);
     }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
