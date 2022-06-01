package com.example.negin.rover.utils;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.negin.rover.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Negin on 09/12/2018.
 */

public class CustomGridAdapter extends BaseAdapter {

    Context context;
    List<Integer> weirs;
    int start_position;
    int new_start_position;
    LayoutInflater layoutInflater;
    Holder holder;
     boolean error;

    public CustomGridAdapter(Context context, List<Integer> weirs, int startpoint) {
        this.context = context;
        this.weirs = weirs;
        this.start_position = startpoint;
        this.new_start_position= startpoint;
        this.error=false;
    }

    @Override
    public int getCount() {
        return 200;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }


    @Override
    public long getItemId(int i) {
        return 200 - i;
    }

    public void updateAdapter(char c) {
        start_position=new_start_position;
        switch (c) {
            case 'M':
                if (start_position > 0 && !weirs.contains(start_position + 10) && start_position < 191) {
                    new_start_position = new_start_position + 10;
                    error=false;
                } else {
                    error=true;
                    PublicMethods.playTone();
                    PublicMethods.showToast(this.context, this.context.getResources().getString(R.string.error_up));
                }
                break;
            case 'R':
                if (start_position > -1 && !weirs.contains(start_position + 1) && start_position % 10 != 0) {
                    new_start_position = new_start_position + 1;
                    error=false;
                } else {
                    error=true;
                    PublicMethods.playTone();
                    PublicMethods.showToast(this.context, this.context.getResources().getString(R.string.error_right));
                }
                break;
            case 'L':
                if (start_position > 0 && !weirs.contains(start_position - 1) && start_position % 10 != 1) {
                    new_start_position = new_start_position - 1;
                    error=false;
                } else {
                    error=true;
                    PublicMethods.playTone();
                    PublicMethods.showToast(this.context, this.context.getResources().getString(R.string.error_left));
                }
                break;
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        position = 200 - position;


        if (view == null) {
            holder = new Holder();
            layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_layout, null, false);
            holder.tv = (TextView) view.findViewById(R.id.item);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        if(start_position==position)
        {
            holder.tv.setText("");
            holder.tv.setBackgroundColor(context.getResources().getColor(R.color.green));
        }
        if (new_start_position == position) {
            holder.tv.setText("↑");
            if(error)
            {
                holder.tv.setBackgroundColor(context.getResources().getColor(R.color.red));
            }
        }
        if (weirs != null) {
            for (int i = 0; i < weirs.size(); i++) {
                if (weirs.get(i) == position)
                    holder.tv.setText("#");
            }
        }

        return view;
    }

    public class Holder {
        TextView tv;

    }
}
