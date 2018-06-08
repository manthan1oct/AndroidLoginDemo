package com.icarus.androidtest.model.request;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;


@Parcel
public class LoginRequest {

    @SerializedName("username")
    public String username;

    @SerializedName("password")
    public String password;


}
