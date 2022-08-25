package com.example.arrestmanagement.exception.handling;

public class ClientIncorrectData {
    private String info;

    public ClientIncorrectData(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public ClientIncorrectData() {
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

