package com.ruby.facebook.models;

import java.util.Map;

public class Response<T> {
    private T data;
    private boolean isSuccess;
    private Map<String, String> errors;

    public boolean isSuccess() {
        return isSuccess;
    }

    public Response(boolean isSuccess) {
        this(null, isSuccess);
    }

    public Response(T data) {
        this(data, true);
    }

    public Response(T data, boolean isSuccess) {
        this.data = data;
        this.isSuccess = isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
