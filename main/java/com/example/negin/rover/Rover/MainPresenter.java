package com.example.negin.rover.Rover;

import com.example.negin.rover.utils.Models.Point;
import com.example.negin.rover.utils.Models.RoverPattern;

import java.util.List;

/**
 * Created by Negin on 11/12/2018.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    MainModel model = new MainModel(this) ;
    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getData(String id) {
        model.getData(id);

    }
    @Override
    public void failureData(String error) {
        view.failureData(error);
    }
    @Override
    public void successData(RoverPattern rp) {
        view.successData(rp);
    }
    @Override
    public int convertToPosition(Point p) {
        return model.convertToPosition(p);
    }
    @Override
    public List<Integer> convertToPosition(List<Point> p) {
       return model.convertToPosition(p);
    }


}
