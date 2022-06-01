package com.example.negin.rover.Rover;

import com.example.negin.rover.utils.Models.Point;
import com.example.negin.rover.utils.Models.RoverPattern;

import java.util.List;

/**
 * Created by Negin on 11/12/2018.
 */

public interface MainContract {

    interface View{
        void successData(RoverPattern rp);
        void failureData(String error);
    }
    interface Presenter{
        void attachView(View view) ;
        void getData(String id);
        void successData(RoverPattern rp);
        int convertToPosition(Point p);
        void failureData(String error);
        List<Integer> convertToPosition(List<Point> p) ;
    }

}
