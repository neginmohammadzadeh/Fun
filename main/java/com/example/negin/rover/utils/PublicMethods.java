package com.example.negin.rover.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.util.Log;
import android.widget.Toast;

import com.example.negin.rover.R;

/**
 * Created by Negin on 11/12/2018.
 */

public class PublicMethods {
   static ProgressDialog progressDialog;
    public static void showToast(Context mContext , String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
    public static void playTone(){

        ToneGenerator toneGen = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
        try {
            toneGen.startTone(ToneGenerator.TONE_PROP_BEEP, 150);
            toneGen.release();
        }catch (Exception ex)
        {
            toneGen.release();
            Log.d("tone",ex.getMessage());
        }
    }
    public static void showProgress(Context mContext)
    {


        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage(mContext.getResources().getString(R.string.alarm_loading));
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(true);
            progressDialog.setProgress(0);
            progressDialog.setCancelable(false);
            progressDialog.show();


        } else {
            progressDialog.show();
        }
    }
    public static void dismissProgress()
    {
        progressDialog.dismiss();
    }
}
