package com.danmalone.shine.api.clients;

import com.danmalone.shine.api.models.CityResponse;
import com.danmalone.shine.api.models.DailyModels.DailyForecast;
import com.danmalone.shine.api.models.Weather;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by danmalone on 10/09/2014.
 */
public interface AutoWunderClient {

    static String BASE_URL = "http://autocomplete.wunderground.com";
    static String API_KEY = "69103a86f0c5ade6";

    @GET("ag")
    DailyForecast forecastWeatherAtCityDaily(@Query("query") String name);

//    GET http://autocomplete.wunderground.com/aq?query=San%20F
}

