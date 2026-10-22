package co.edu.puj.rpg.modelo;

public class Guerrero extends Personaje {
    protected int fuerzaEscudo;

    public Guerrero(String nombre, int vida, int ataqueBase, int posicionX, int posicionY, int fuerzaEscudo) {
        super(nombre, vida, ataqueBase, posicionX, posicionY);
        this.fuerzaEscudo = fuerzaEscudo;
    }

    public void atacar(Personaje objetivo) {
        atacar(objetivo, false);
    }

    public void atacar(Personaje objetivo, boolean modoFuria) {
        int dano = calcularAtaqueTotal() + fuerzaEscudo;
        if (modoFuria) {
            dano = dano * 2;
            System.out.printf("%s entra en modo furia y golpea a %s%n", nombre, objetivo.getNombre());
        } else {
            System.out.printf("%s golpea con su escudo a %s%n", nombre, objetivo.getNombre());
        }
        objetivo.defender(dano);
    }

    @Override
    public void realizarAtaqueEspecial(Personaje objetivo) {
        boolean furia = vida * 2 < vidaMaxima;
        atacar(objetivo, furia);
    }

    @Override
    public void defender(int danoRecibido) {
        int reducido = danoRecibido - fuerzaEscudo / 2;
        if (reducido < 1) {
            reducido = 1;
        }
        super.defender(reducido);
    }

    @Override
    public char getSimbolo() {
        return 'G';
    }

    @Override
    public String getEstado() {
        return super.getEstado() + String.format(" | Escudo: %d", fuerzaEscudo);
    }
}
