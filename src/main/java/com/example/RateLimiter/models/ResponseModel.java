package com.example.RateLimiter.models;

import lombok.Data;

import java.util.List;

@Data
public class ResponseModel {
    String refreshedAuth;
    Object object;
    public ResponseModel(String token, Object obj)
    {
        refreshedAuth=token;
        object=obj;
    }
}

