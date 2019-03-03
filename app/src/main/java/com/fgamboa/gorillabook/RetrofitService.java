package com.fgamboa.gorillabook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

class RetrofitService {

    private static final String ENDPOINT = "http://gl-endpoint.herokuapp.com";

    private EndpointInterface networkService;

    RetrofitService() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Gson gson = new GsonBuilder().create();

        Retrofit retrofitClient = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        networkService = retrofitClient.create(EndpointInterface.class);
    }

    public EndpointInterface getNetworkService() {
        return networkService;
    }

    public void setNetworkService(EndpointInterface networkService) {
        this.networkService = networkService;
    }

    interface EndpointInterface {

        @GET("/feed")
        Call<List<Post>> getFeedContent();
    }
}
