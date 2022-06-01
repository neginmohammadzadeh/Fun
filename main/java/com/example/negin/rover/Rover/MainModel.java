package com.example.negin.rover.Rover;

import android.content.Context;
import android.widget.Toast;

import com.example.negin.rover.R;
import com.example.negin.rover.utils.Constants;
import com.example.negin.rover.utils.CustomGridAdapter;
import com.example.negin.rover.utils.Models.Point;
import com.example.negin.rover.utils.Models.RoverPattern;
import com.example.negin.rover.utils.PublicMethods;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Negin on 11/12/2018.
 */

public class MainModel {

    MainContract.Presenter presenter;
    Context mContext;
    RoverPattern rp;

    public void attachContext(Context mContext) {
        this.mContext = mContext;
    }

    public MainModel(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public int convertToPosition(Point p) {
        int position = 0;
        position = p.getY()* 10 + p.getX()+1;
        return position;

    }

    public List<Integer> convertToPosition(List<Point> list) {
        List<Integer> weirds = new ArrayList<Integer>();
        for (Point p : list) {
            weirds.add(p.getY() * 10 + p.getX() +1);
        }
        return weirds;

    }

   public void getData(String id)
    {
        rp = new RoverPattern();
        Constants.restClient.reposForRover(id).enqueue(new Callback<RoverPattern>() {
            @Override
            public void onResponse(Call<RoverPattern> call, Response<RoverPattern> response) {
                rp = response.body();
                if (rp !=null){
                    presenter.successData(rp);
                }
            }

            @Override
            public void onFailure(Call<RoverPattern> call, Throwable t) {
                presenter.failureData(t.getMessage());
            }

        });
    }
}
