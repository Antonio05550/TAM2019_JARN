package mx.edu.ittepic.a3_4_sw;


public class Equipo {

    private String id;
    private String nombre;
    private String desc;

    public Equipo(String id, String nombre, String desc) {
        this.id = id;
        this.nombre = nombre;
        this.desc = desc;
    }

    public Equipo(String nombre, String desc) {
        this.nombre = nombre;
        this.desc = desc;
    }

    public Equipo(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

