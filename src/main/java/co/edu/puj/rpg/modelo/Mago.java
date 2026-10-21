package co.edu.puj.rpg.modelo;

public class Mago extends Personaje {
    protected int puntosMana;

    public Mago(String nombre, int vida, int ataqueBase, int posicionX, int posicionY, int puntosMana) {
        super(nombre, vida, ataqueBase, posicionX, posicionY);
        this.puntosMana = puntosMana;
    }

    public void atacar(Personaje objetivo) {
        atacar(objetivo, false);
    }

    public void atacar(Personaje objetivo, boolean modoFuria) {
        if (modoFuria) {
            puntosMana = puntosMana - 10;
            System.out.printf("%s lanza una Bola de Fuego sobre %s%n", nombre, objetivo.getNombre());
            objetivo.defender(ataqueBase + 15);
        } else {
            puntosMana = puntosMana + 5;
            System.out.printf("%s golpea con su bastón a %s y recupera 5 de maná%n", nombre, objetivo.getNombre());
            objetivo.defender(ataqueBase / 2);
        }
    }

    @Override
    public void realizarAtaqueEspecial(Personaje objetivo) {
        atacar(objetivo, puntosMana >= 10);
    }

    @Override
    public char getSimbolo() {
        return 'M';
    }

    @Override
    public String getEstado() {
        return super.getEstado() + String.format(" | Maná: %d", puntosMana);
    }
}
