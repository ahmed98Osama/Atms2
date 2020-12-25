package com.example.atms;

public class requestDataModel {

    String  id,message,name,status;

    public requestDataModel() {
    }

    public requestDataModel(String id, String message, String name, String status) {
        this.id = id;
        this.message = message;
        this.name = name;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
