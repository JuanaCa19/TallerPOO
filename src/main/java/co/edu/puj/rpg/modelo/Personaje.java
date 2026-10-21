package co.edu.puj.rpg.modelo;

public abstract class Personaje implements ICombatiente {
    protected String nombre;
    protected int vida;
    protected int vidaMaxima;
    protected int ataqueBase;
    protected int posicionX;
    protected int posicionY;

    public Personaje(String nombre, int vida, int ataqueBase, int posicionX, int posicionY) {
        this.nombre = nombre;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataqueBase = ataqueBase;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public abstract void realizarAtaqueEspecial(Personaje objetivo);

    public abstract char getSimbolo();

    @Override
    public void defender(int danoRecibido) {
        recibirDano(danoRecibido);
        System.out.printf("%s recibe %d de daño. Vida: %d/%d%n", nombre, danoRecibido, vida, vidaMaxima);
        if (!estaVivo()) {
            System.out.printf("%s ha caído en batalla.%n", nombre);
        }
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void recibirDano(int cantidad) {
        if (cantidad > 0) {
            vida = vida - cantidad;
        }
        if (vida < 0) {
            vida = 0;
        }
    }

    public void curar(int cantidad) {
        if (cantidad > 0) {
            vida = vida + cantidad;
        }
        if (vida > vidaMaxima) {
            vida = vidaMaxima;
        }
    }

    public String getEstado() {
        return String.format("%s | Vida: %d/%d", nombre, vida, vidaMaxima);
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getAtaqueBase() {
        return ataqueBase;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicion(int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }
}
