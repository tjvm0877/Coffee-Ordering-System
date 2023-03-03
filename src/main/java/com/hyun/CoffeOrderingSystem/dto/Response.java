package com.hyun.CoffeOrderingSystem.dto;

public class Response<T> {
    private String result;
    private String msg;
    private T data;

    public Response(String result, String msg, T data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
    }
}
