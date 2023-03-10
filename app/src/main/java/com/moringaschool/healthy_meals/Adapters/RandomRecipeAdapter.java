package com.moringaschool.healthy_meals.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.healthy_meals.Models.Recipe;
import com.moringaschool.healthy_meals.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{
    Context context;
    List<Recipe> list;

    //constructors for the context and list

    public RandomRecipeAdapter(Context context, List<Recipe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
      holder.textTitle.setText(list.get(position).title);
      holder.textTitle.setSelected(true);
      holder.likes.setText(list.get(position).aggregateLikes+" Likes");
      holder.servings.setText(list.get(position).servings+" Servings");
      holder.time.setText(list.get(position).cookingMinutes+"");
      Picasso.get().load(list.get(position).image).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.random_list_container) CardView randomList;
    @BindView(R.id.text_title) TextView textTitle;
    @BindView(R.id.textview_servings) TextView servings;
    @BindView(R.id.textview_likes) TextView likes;
    @BindView(R.id.textview_time) TextView time;
    @BindView(R.id.imageView_image) ImageView image;



    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }
}
