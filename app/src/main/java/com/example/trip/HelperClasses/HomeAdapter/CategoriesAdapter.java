package com.example.trip.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trip.R;

import java.util.ArrayList;

public class CategoriesAdapter  extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    ArrayList<CategoriesHelperClass> categoriesTitle;


    public CategoriesAdapter(ArrayList<CategoriesHelperClass> categoriesTitle) {
        this.categoriesTitle = categoriesTitle;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.categories_card_design,parent,false);
        CategoriesViewHolder categoriesViewHolder = new CategoriesViewHolder(view);
        return categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {


        CategoriesHelperClass categoriesHelperClass = categoriesTitle.get( position );

        holder.image.setImageResource(categoriesHelperClass.getImage());
        holder.title.setText(categoriesHelperClass.getTitle());



    }

    @Override
    public int getItemCount() {
        return categoriesTitle.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;

        public  CategoriesViewHolder(@NonNull View itemView) {
            super( itemView );

            //hooks
            image = itemView.findViewById( R.id.categories_image);
            title = itemView.findViewById( R.id.categories_title );
        }
    }
}
