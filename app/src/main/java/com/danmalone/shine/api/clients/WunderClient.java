package com.danmalone.shine.api.clients;

import com.danmalone.shine.api.models.Weather;
import com.danmalone.shine.api.models.wunder.daily.TenDay;
import com.danmalone.shine.api.models.wunder.hourly.Hourly;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by danmalone on 10/09/2014.
 */
public interface WunderClient {

    static String BASE_URL = "http://api.wunderground.com/api/";
    static String API_KEY = "69103a86f0c5ade6/";

//    http://api.wunderground.com/api/69103a86f0c5ade6/forecast/q/CA/San_Francisco.json

    @GET("/forecast/q/{country}/{city}.json")
    Weather getThreeDayForecast(@Path("country") String country, @Path("city") String city);


    @GET("/forecast10day/q/{country}/{city}.json")
    TenDay getTenDayForecast(@Path("country") String country, @Path("city") String city);

//    http://api.wunderground.com/api/69103a86f0c5ade6/conditions/q/CA/San_Francisco.json
    @GET("/conditions/q/{country}/{city}.json")
    com.danmalone.shine.api.models.ForecastModels.Forecast conditions(@Path("country") String country, @Path("city") String city);

//    http://api.wunderground.com/api/69103a86f0c5ade6/hourly/q/CA/San_Francisco.json
    @GET("/hourly/q/{country}/{city}.json")
    Hourly getHourlyForecast(@Path("country") String country, @Path("city") String city);

}

