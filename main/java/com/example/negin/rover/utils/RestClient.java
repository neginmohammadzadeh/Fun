package com.example.negin.rover.utils;

import com.example.negin.rover.utils.Models.RoverPattern;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Negin on 09/12/2018.
 */

public interface RestClient {
        @FormUrlEncoded
        @POST("/")
        Call<RoverPattern> reposForRover(@Field("rover_id") String rover_id);
}
