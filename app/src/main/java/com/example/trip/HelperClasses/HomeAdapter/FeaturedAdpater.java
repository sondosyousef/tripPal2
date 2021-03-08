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

public class FeaturedAdpater  extends RecyclerView.Adapter<FeaturedAdpater.FeaturedViewHolder> {


    Context context;

     List<FeaturedHelperClass> featuredLocations;

    ImageLoader imageLoader1;

    public FeaturedAdpater(List<FeaturedHelperClass> featuredLocations, Context context) {
        super();
        this.featuredLocations = featuredLocations;
        this.context = context;
    }



    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.featured_card_design,parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {


        FeaturedHelperClass featuredHelperClass1 = featuredLocations.get( position );
        imageLoader1 = ServerImageParseAdapter.getInstance(context).getImageLoader();

        imageLoader1.get(featuredHelperClass1.getImage(),
                ImageLoader.getImageListener(
                        holder.image,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );

        holder.image.setImageUrl(featuredHelperClass1.getImage(),imageLoader1);
        holder.title.setText(featuredHelperClass1.getTitle());
       // holder.desc.setText(featuredHelperClass1.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),discription.class);
                i.putExtra("image_name", featuredHelperClass1.title);
                i.putExtra("image_Url", featuredHelperClass1.image);
                i.putExtra("description", featuredHelperClass1.description);
                i.putExtra("point1", featuredHelperClass1.poin1);
                i.putExtra("point2", featuredHelperClass1.point2);
                i.putExtra("point3", featuredHelperClass1.point3);
                i.putExtra("point4", featuredHelperClass1.point4);
                i.putExtra("img_point1", featuredHelperClass1.imgPoint1);
                i.putExtra("img_point2", featuredHelperClass1.imgPoint2);
                i.putExtra("img_point3", featuredHelperClass1.imgPoint3);
                i.putExtra("img_point4", featuredHelperClass1.imgPoint4);
                i.putExtra("des1", featuredHelperClass1.des1);
                i.putExtra("des2", featuredHelperClass1.des2);
                i.putExtra("des3", featuredHelperClass1.des3);
                i.putExtra("des4", featuredHelperClass1.des4);



                v.getContext().startActivity(i);

            }
        });



    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }
    public void filterList(ArrayList<FeaturedHelperClass> filterItem) {
        featuredLocations=filterItem;
        notifyDataSetChanged();

    }


    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        public NetworkImageView image;
        TextView title;

        public FeaturedViewHolder(@NonNull View itemView) {
            super( itemView );

            //hooks
            image = itemView.findViewById( R.id.featured_image );
            title = itemView.findViewById( R.id.featured_title );
          //  desc = itemView.findViewById( R.id.featured_desc );
        }
    }
}
