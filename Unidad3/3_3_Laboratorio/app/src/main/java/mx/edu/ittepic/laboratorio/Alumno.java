package mx.edu.ittepic.laboratorio;

public class Alumno {

    public int nctrl;
    public String name;
    public int u1;
    public int u2;
    public int u3;

    public Alumno(int nctrl, String name, int u1, int u2, int u3) {
        this.nctrl = nctrl;
        this.name = name;
        this.u1 = u1;
        this.u2 = u2;
        this.u3 = u3;
    }

    public int getNctrl() {
        return nctrl;
    }

    public void setNctrl(int nctrl) {
        this.nctrl = nctrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getU1() {
        return u1;
    }

    public void setU1(int u1) {
        this.u1 = u1;
    }

    public int getU2() {
        return u2;
    }

    public void setU2(int u2) {
        this.u2 = u2;
    }

    public int getU3() {
        return u3;
    }

    public void setU3(int u3) {
        this.u3 = u3;
    }
}
