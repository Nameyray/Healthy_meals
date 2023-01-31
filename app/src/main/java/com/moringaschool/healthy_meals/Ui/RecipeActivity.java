package com.moringaschool.healthy_meals.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.moringaschool.healthy_meals.Adapters.RandomRecipeAdapter;
import com.moringaschool.healthy_meals.Listeners.RandomRecipeResponseListener;
import com.moringaschool.healthy_meals.Models.RandomRecipeApiResponse;
import com.moringaschool.healthy_meals.R;
import com.moringaschool.healthy_meals.RequestManager;

public class RecipeActivity extends AppCompatActivity {
    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener);

        dialog.show();
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            recyclerView = findViewById(R.id.recycler);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(RecipeActivity.this,1));

            //set the adapter
            randomRecipeAdapter = new RandomRecipeAdapter(RecipeActivity.this, response.recipes);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeActivity.this, "oops! something went wrong", Toast.LENGTH_SHORT).show();

        }
    };

}