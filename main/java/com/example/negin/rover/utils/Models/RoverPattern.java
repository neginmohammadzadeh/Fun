package com.example.negin.rover.utils.Models;

import com.example.negin.rover.utils.Models.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Negin on 09/12/2018.
 */

public class RoverPattern {

//    int [][] start_point;
//    HashMap<String, Integer>;
    private Point start_point;
    private List<Point> weirs;
    private String command;


   public RoverPattern(){
        start_point= new Point();
        weirs= new ArrayList<Point>();
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setStart_point(Point start_point) {
        this.start_point = start_point;
    }

    public void setWeirs(List<Point> weirs) {
        this.weirs = weirs;
    }

    public String getCommand() {
        return command;
    }

    public Point getStart_point() {
        return start_point;
    }

    public List<Point> getWeirs() {
        return weirs;
    }
}
