package mx.edu.ittepic.listadesensores;

public class Sensor {
    private String nombre;
    private String fabricante;
    private int version;

    public Sensor(String nombre, String fabricante, int version) {
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.version = version;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
