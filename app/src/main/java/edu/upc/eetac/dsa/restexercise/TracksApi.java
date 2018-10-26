package edu.upc.eetac.dsa.restexercise;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface TracksApi {
    String BASE_URL = "http://147.83.7.155:8080/dsaApp/";

    @GET("tracks")
    Call<List<Track>> getTracks();

    @GET("tracks/{id}")
    Call<Track> getTrack(@Path("id")String id) ;

    // create new Track
    @POST("tracks")
    Call<ResponseBody> createTrack(@Body Track track);

    // update existing Gist
    @PUT("tracks/{id}")
    Call<ResponseBody> replaceTrack(@Path("id") String id, @Body Track track);

    //delete a track
    @DELETE("tracks/{id}")
    Call<ResponseBody> deleteTrack(@Path("id") String id);

}
