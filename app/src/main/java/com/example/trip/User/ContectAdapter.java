package com.example.trip.User;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trip.R;

import java.util.ArrayList;

public class ContectAdapter extends BaseAdapter {
    ArrayList <LvItem> arrayList;
    Context context;
    LayoutInflater layoutInflater;


    public ContectAdapter(ArrayList<LvItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view1 = layoutInflater.inflate( R.layout.reminder,parent,false);
        TextView textTitle = view1.findViewById(R.id.textView1);
        TextView textTime = view1.findViewById(R.id.textView2);



        textTitle.setText(arrayList.get(i).getTitle());
        textTime.setText(arrayList.get(i).getTime());


        return view1;
    }
}
