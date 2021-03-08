package com.example.trip.User;
import android.content.Context;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.trip.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.trip.HelperClasses.HomeAdapter.ServerImageParseAdapter;
import com.example.trip.HelperClasses.HomeAdapter.discription;
import com.example.trip.R;

import java.util.List;

/**
 * Created by JUNED on 6/16/2016.
 */
public class RecyclerPlan extends RecyclerView.Adapter<RecyclerPlan.ViewHolder> {

    Context context;

    List<LvItem> getDataAdapter;

    ImageLoader imageLoader1;

    public RecyclerPlan(List<LvItem> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tourplan, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LvItem getDataAdapter1 =  getDataAdapter.get(position);


        holder.NameView.setText(getDataAdapter1.getTitle());
        holder.DATE.setText(getDataAdapter1.getTime());



    }


    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView NameView;
         public TextView DATE;


        public ViewHolder(View itemView) {

            super(itemView);

           NameView = (TextView) itemView.findViewById(R.id.text1) ;

            DATE = itemView.findViewById(R.id.date) ;




        }


    }
}