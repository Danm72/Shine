package com.danmalone.shine.api.clients;

import com.danmalone.shine.api.models.CityResponse;
import com.danmalone.shine.api.models.DailyModels.DailyForecast;
import com.danmalone.shine.api.models.Forecast;
import com.danmalone.shine.api.models.Weather;

import java.util.Locale;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by danmalone on 10/09/2014.
 */
public interface OWMClient {

    static String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    static String IMG_URL = "http://openweathermap.org/img/w/";
    static String API_KEY = "9148af30bec092c9f88702588c5bdacb";

  /*  @GET("/users/{user}/repos")
    List<Repo> listRepos(@Path("user") String user);


    @GET("/repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );*/

    @GET("/weather/")
    Weather getCityWeather(@Query("q") String cityAndCountry);


    @GET("/find")
    CityResponse findCityByNameAndCode(@Query("q") String cityNameAndCountryCode);

    @GET("/forecast/?type=json&units=metric")
    com.danmalone.shine.api.models.ForecastModels.Forecast forcastWeatherAtCity(@Query("q") String cityName);

    @GET("/find")
    CityResponse findCityByName(@Query("q") String name, @Query("type") String like);

    @GET("/forecast/daily?type=json&units=metric")
    DailyForecast forecastWeatherAtCityDaily(@Query("q") String name);
//    http://api.openweathermap.org/data/2.5/forecast/daily?q=london&units=metric&cnt=14&_=1410608843646
}

