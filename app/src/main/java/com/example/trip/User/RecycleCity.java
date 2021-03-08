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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JUNED on 6/16/2016.
 */
public class RecycleCity extends RecyclerView.Adapter<RecycleCity.ViewHolder> {

    Context context;

    List<FeaturedHelperClass> getDataAdapter;

    ImageLoader imageLoader1;

    public RecycleCity(List<FeaturedHelperClass> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycle_city, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeaturedHelperClass getDataAdapter1 =  getDataAdapter.get(position);

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), discription.class);
                i.putExtra("image_name", getDataAdapter1.title);
                i.putExtra("image_Url", getDataAdapter1.image);
                i.putExtra("description", getDataAdapter1.description);
                i.putExtra("point1", getDataAdapter1.poin1);
                i.putExtra("point2", getDataAdapter1.point2);
                i.putExtra("point3", getDataAdapter1.point3);
                i.putExtra("point4", getDataAdapter1.point4);
                i.putExtra("img_point1", getDataAdapter1.imgPoint1);
                i.putExtra("img_point2", getDataAdapter1.imgPoint2);
                i.putExtra("img_point3", getDataAdapter1.imgPoint3);
                i.putExtra("img_point4", getDataAdapter1.imgPoint4);
                i.putExtra("des1", getDataAdapter1.des1);
                i.putExtra("des2", getDataAdapter1.des2);
                i.putExtra("des3", getDataAdapter1.des3);
                i.putExtra("des4", getDataAdapter1.des4);
                v.getContext().startActivity(i);

            }
        });



    }


    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }
    public void filterList(ArrayList<FeaturedHelperClass> filterItem) {
        getDataAdapter=filterItem;
        notifyDataSetChanged();

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ImageTitleNameView;
        public NetworkImageView networkImageView ;


        public ViewHolder(View itemView) {

            super(itemView);

            ImageTitleNameView = (TextView) itemView.findViewById(R.id.textView_item) ;

            networkImageView = (NetworkImageView) itemView.findViewById(R.id.VollyNetworkImageView1) ;




        }


    }
}