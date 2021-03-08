package com.example.trip.HelperClasses.HomeAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.trip.R;

import java.util.ArrayList;
import java.util.List;

public class MostAdapter extends RecyclerView.Adapter<MostAdapter.MostViewHolder> {

    Context context;

    List<MostHelperClass> mostView;

    ImageLoader imageLoader2;


    public MostAdapter(List<MostHelperClass> mostView,Context context) {
        super();
        this.mostView = mostView;
        this.context=context;

    }

    @NonNull
    @Override
    public MostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.most_viewed_card_design,parent,false);
        MostViewHolder mostViewHolder = new MostViewHolder(view);
        return mostViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewHolder holder, int position) {


        MostHelperClass mostHelperClass1 = mostView.get( position );

        imageLoader2 = ServerImageParseAdapter.getInstance(context).getImageLoader();

        imageLoader2.get(mostHelperClass1.getImage(),
                ImageLoader.getImageListener(
                        holder.imagePoint,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );

        holder.imagePoint.setImageUrl(mostHelperClass1.getImage(),imageLoader2);
        holder.titlePoint.setText(mostHelperClass1.getTitle());
        holder.nameCity.setText(mostHelperClass1.getNameCity());
        // holder.desc.setText(featuredHelperClass1.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),discriptionPoint.class);
                i.putExtra("image_name", mostHelperClass1.title);
                i.putExtra("image_Url", mostHelperClass1.image);
                i.putExtra("description", mostHelperClass1.description);
                i.putExtra("city_name", mostHelperClass1.nameCity);
                v.getContext().startActivity(i);

            }
        });

    }


    @Override
    public int getItemCount() {
        return mostView.size();
    }

    public void filterList(ArrayList<MostHelperClass> filterItem) {
        mostView=filterItem;
        notifyDataSetChanged();
    }

    public static class MostViewHolder extends RecyclerView.ViewHolder{

        public NetworkImageView imagePoint;
        TextView titlePoint,nameCity;


        public  MostViewHolder(@NonNull View itemView) {
            super( itemView );

            //hooks
            imagePoint = itemView.findViewById( R.id.mv_imag);
            titlePoint = itemView.findViewById( R.id.mv_title );
            nameCity= itemView.findViewById(R.id.mv_desc) ;

        }
    }
}