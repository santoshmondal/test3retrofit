package com.example.santosh.test3;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by santosh on 5/14/16.
 */
public interface AppApi {

    @GET("/pub/hello")
    public Call<SamplePojo> hello();

    @GET("/pub/hello1")
    public Call<String> hello1();


}
