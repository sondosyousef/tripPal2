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
import com.example.trip.HelperClasses.HomeAdapter.MostHelperClass;
import com.example.trip.HelperClasses.HomeAdapter.ServerImageParseAdapter;
import com.example.trip.HelperClasses.HomeAdapter.discription;
import com.example.trip.HelperClasses.HomeAdapter.discriptionPoint;
import com.example.trip.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JUNED on 6/16/2016.
 */
public class RecyclerPoint extends RecyclerView.Adapter<RecyclerPoint.ViewHolder> {

    Context context;

    List<MostHelperClass> getDataAdapter;

    ImageLoader imageLoader1;

    public RecyclerPoint(List<MostHelperClass> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_point, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MostHelperClass getDataAdapter1 =  getDataAdapter.get(position);

        imageLoader1 = ServerImageParseAdapter.getInstance(context).getImageLoader();

        imageLoader1.get(getDataAdapter1.getImage(),
                ImageLoader.getImageListener(
                        holder.networkImageView,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );

        holder.networkImageView.setImageUrl(getDataAdapter1.getImage(), imageLoader1);
        holder.ImageTitleNameView.setText(getDataAdapter1.getTitle());
        holder.nameCity.setText(getDataAdapter1.getNameCity());
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), discriptionPoint.class);
                i.putExtra("image_name", getDataAdapter1.title);
                i.putExtra("image_Url", getDataAdapter1.image);
                i.putExtra("description", getDataAdapter1.description);
                i.putExtra("city_name", getDataAdapter1.nameCity);
                v.getContext().startActivity(i);

            }
        });



    }


    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    public void filterList(ArrayList<MostHelperClass> filterItem) {
        getDataAdapter=filterItem;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ImageTitleNameView,nameCity;
        public NetworkImageView networkImageView ;

        public ViewHolder(View itemView) {

            super(itemView);

            ImageTitleNameView = (TextView) itemView.findViewById(R.id.textView_item2) ;

            networkImageView = (NetworkImageView) itemView.findViewById(R.id.VollyNetworkImageView2) ;
            nameCity= itemView.findViewById(R.id.nameCityd) ;




        }


    }
}