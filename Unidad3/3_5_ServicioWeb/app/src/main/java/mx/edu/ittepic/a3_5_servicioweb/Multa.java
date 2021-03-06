package mx.edu.ittepic.a3_5_servicioweb;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Multa {

    private String id;
    private int numero;
    private String celular;
    private String correo;
    private int monto;
    private int puntos;

    public Multa(){
    }

    public Multa(int numero, String celular, String correo, int monto, int puntos) {
        this.numero = numero;
        this.celular = celular;
        this.correo = correo;
        this.monto = monto;
        this.puntos = puntos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Multa(String id, int numero, String celular, String correo, int monto, int puntos){
        this.id = id;
        this.numero = numero;
        this.celular = celular;
        this.correo = correo;

        this.monto = monto;
        this.puntos = puntos;
    }


}

