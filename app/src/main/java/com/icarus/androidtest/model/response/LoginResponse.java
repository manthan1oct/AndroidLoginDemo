package com.icarus.androidtest.model.response;


import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;


@Parcel
public class LoginResponse {
    @SerializedName("errorCode")
    public String errorCode;
    @SerializedName("errorMessage")
    public String errorMessage;

}
