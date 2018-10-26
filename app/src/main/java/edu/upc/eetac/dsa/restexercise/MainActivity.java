package edu.upc.eetac.dsa.restexercise;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  {

    private TracksApi tracksApi;
    private Spinner trackSpinner;
    private RecyclerView list;
    boolean isEdit;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            trackSpinner = (Spinner) findViewById(R.id.trackSpinner);

            list = (RecyclerView) findViewById(R.id.trackList);
            list.setHasFixedSize(true);
            list.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            createTracksAPI();
            tracksApi.getTracks().enqueue(tracksCallBack);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    Callback<List<Track>> tracksCallBack = new Callback<List<Track>>() {
        @Override
        public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
            if (response.isSuccessful()) {

                List<Track> data = new ArrayList<>();
                data.addAll(response.body());
                list.setAdapter(new RecyclerViewAdapter(data));
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<List<Track>> call, Throwable t) {
            t.printStackTrace();
        }
    };
    private void createTracksAPI() {
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TracksApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        tracksApi = retrofit.create(TracksApi.class);
    }

    public void addTrackBtn(View view) {
        isEdit = false;
        Intent intent = new Intent(this,Main2Activity.class);

        startActivity(intent);


    }
    public void deleteTrackBtn(View view){

        //delete a track


    }
    public void editTrackBtn(){
        Intent intent = new Intent(this,Main2Activity.class);
        isEdit = true;
        startActivity(intent);


    }


}
