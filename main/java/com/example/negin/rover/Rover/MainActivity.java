package com.example.negin.rover.Rover;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.negin.rover.R;
import com.example.negin.rover.utils.BaseActivity;
import com.example.negin.rover.utils.Constants;
import com.example.negin.rover.utils.CustomGridAdapter;
import com.example.negin.rover.utils.Models.RoverPattern;
import com.example.negin.rover.utils.PublicMethods;

public class MainActivity extends BaseActivity implements MainContract.View {

    CustomGridAdapter adapter;
    GridView gridView;
    Button btnStart;
    Button btnStop;
    MainContract.Presenter presenter;
    Handler handler;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter();
        presenter.attachView(this);

        btnStart=findViewById(R.id.btn_start);
        btnStop=findViewById(R.id.btn_stop);


        adapter = new CustomGridAdapter(MainActivity.this, null, 0);
        gridView= findViewById(R.id.gridview);
        gridView.setAdapter(adapter);


        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1500);
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(handler !=null)
                {
                    handler.removeCallbacksAndMessages(null);
                }
                PublicMethods.showProgress(mContext);
                presenter.getData(Constants.id);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(handler !=null)
                {
                    handler.removeCallbacksAndMessages(null);
                    PublicMethods.showToast(mContext,mContext.getResources().getString(R.string.alarm_stop));
                }
            }
        });

    }
    @Override
    public void successData(final RoverPattern rp) {

        adapter = new CustomGridAdapter(MainActivity.this, presenter.convertToPosition(rp.getWeirs()), presenter.convertToPosition(rp.getStart_point()));
        gridView.setAdapter(adapter);
        showMovement(rp);
        PublicMethods.dismissProgress();
    }

    @Override
    public void failureData(String error) {
        PublicMethods.dismissProgress();
        PublicMethods.showToast(mContext,mContext.getResources().getString(R.string.error_retrofit));

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void showMovement(final RoverPattern rp) {
        handler = new Handler();
        for (int a = 1; a <= rp.getCommand().length(); a++) {
            final int finalA = a;
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    adapter.updateAdapter(rp.getCommand().charAt(finalA - 1));
                    adapter.notifyDataSetChanged();
                }
            }, 1500 * a);
        }
    }
}
