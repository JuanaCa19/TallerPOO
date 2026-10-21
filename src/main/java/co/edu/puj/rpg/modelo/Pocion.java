package co.edu.puj.rpg.modelo;

public class Pocion {
    private String nombre;
    private int cura;

    public Pocion(String nombre, int cura) {
        this.nombre = nombre;
        this.cura = cura;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCura() {
        return cura;
    }
}
