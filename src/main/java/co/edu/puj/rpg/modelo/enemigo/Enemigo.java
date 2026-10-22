package co.edu.puj.rpg.modelo.enemigo;

import co.edu.puj.rpg.modelo.Personaje;

public class Enemigo extends Personaje {

    public Enemigo(String nombre, int vida, int ataqueBase, int posicionX, int posicionY) {
        super(nombre, vida, ataqueBase, posicionX, posicionY);
    }

    @Override
    public void realizarAtaqueEspecial(Personaje objetivo) {
        System.out.printf("%s ataca a %s%n", nombre, objetivo.getNombre());
        objetivo.defender(calcularAtaqueTotal());
    }

    @Override
    public char getSimbolo() {
        return 'E';
    }
}
