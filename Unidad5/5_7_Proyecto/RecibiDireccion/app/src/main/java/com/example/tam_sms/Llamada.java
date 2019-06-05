package com.example.tam_sms;

public class Llamada {
    private  String id;
    private String numero;
    private String body;

    public Llamada(String numero, String body) {
        this.id = id;
        this.numero = numero;
        this.body = body;
    }
    public  Llamada(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
