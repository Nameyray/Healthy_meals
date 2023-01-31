package com.moringaschool.healthy_meals;

import android.content.Context;

import com.moringaschool.healthy_meals.Listeners.RandomRecipeResponseListener;
import com.moringaschool.healthy_meals.Models.RandomRecipeApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    //create retrofit and context object
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //create a constructor

    public RequestManager(Context context) {
        this.context = context;
    }

    //method
    public void getRandomRecipes(RandomRecipeResponseListener listener){
        //instance of call random recipes
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key), "10" );
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
               if (!response.isSuccessful()){
                  listener.didError(response.message());
                  return;
               }
               listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
              listener.didError(t.getMessage());
            }
        });
    }

    //interface for the random recipe api
    private interface CallRandomRecipes{
        //call method
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipe(
                //api parameters
                @Query("apiKey") String apiKey,
                @Query("number") String number
                );
    }
}
