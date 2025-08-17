package com.example.demo.model;

public enum GameStatus {
    NAO_INICIADO("Não iniciado"),
    INICIADO("Iniciado"),
    FINALIZADO("Finalizado");

    private String statusString;

    GameStatus(String statusString) {
        this.statusString = statusString ;
    }

    public String getStatusString() {
        return statusString;
    }
}
