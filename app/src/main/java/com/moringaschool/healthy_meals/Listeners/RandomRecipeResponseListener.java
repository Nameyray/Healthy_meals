package com.moringaschool.healthy_meals.Listeners;

import com.moringaschool.healthy_meals.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomRecipeApiResponse response, String message);
    //message for handling errors
    void didError(String message);
}
