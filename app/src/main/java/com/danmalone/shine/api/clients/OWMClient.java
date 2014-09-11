package com.danmalone.shine.api.clients;

import com.danmalone.shine.api.models.Weather;

import java.util.Locale;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by danmalone on 10/09/2014.
 */
public interface OWMClient {

    static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    static String IMG_URL = "http://openweathermap.org/img/w/";
    static String API_KEY = "9148af30bec092c9f88702588c5bdacb";

  /*  @GET("/users/{user}/repos")
    List<Repo> listRepos(@Path("user") String user);


    @GET("/repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );*/

    @GET("/")
    Weather getCity(@Query("q") String cityAndCountry);


    @GET("/find/name")
    Weather findByName(@Query("q") String cityNameAndCountryCode);

    @GET("forecast/city/{city_id}?type=json&units=metric")
    Weather forcastWeatherAtCity(@Path("city_id") String cityId);

}

