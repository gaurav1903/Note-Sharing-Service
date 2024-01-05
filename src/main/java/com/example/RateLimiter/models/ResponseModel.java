package com.example.RateLimiter.models;

public class ResponseModel {
    String refreshedAuth;
    Object object;
    public ResponseModel(String token, Object obj)
    {
        refreshedAuth=token;
        object=obj;
    }
}

