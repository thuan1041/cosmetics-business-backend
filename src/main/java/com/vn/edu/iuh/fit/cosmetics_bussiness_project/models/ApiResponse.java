package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;

public class ApiResponse<T> {
    private int errorCode;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(int errorCode, String message, T data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}