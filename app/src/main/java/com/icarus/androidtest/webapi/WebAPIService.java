package com.icarus.androidtest.webapi;


import com.icarus.androidtest.model.request.LoginRequest;
import com.icarus.androidtest.model.response.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WebAPIService {


    @POST("login")
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);

}
