package co.edu.puj.rpg.modelo.enemigo;

import co.edu.puj.rpg.modelo.Personaje;

public class Enemigo {
    private String nombre;
    private int puntosVida;
    private int vidaMaxima;
    private int puntosAtaque;
    private int nivel;
    private double oro;
    private boolean estaVivo;

    public Enemigo(String nombre,int vidaMaxima,int puntosAtaque,double oro){
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.puntosVida = vidaMaxima;
        this.puntosAtaque = puntosAtaque;
        this.nivel = 1;
        this.oro = oro;
        this.estaVivo = true;
    }

    public void atacar(Personaje heroe){
        heroe.recibirDano(this.puntosAtaque);
    }

    public void usarPocion(int cantidadRestaurada){
        if (cantidadRestaurada > 0){
            setPuntosVida(this.puntosVida + cantidadRestaurada);
        }
    }

    public void recibirDano(int cantidadDano){
        if (cantidadDano > 0){
            setPuntosVida(this.puntosVida - cantidadDano);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getPuntosAtaque() {
        return puntosAtaque;
    }

    public int getNivel() {
        return nivel;
    }

    public double getOro() {
        return oro;
    }

    public boolean isEstaVivo() {
        return estaVivo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public void setPuntosAtaque(int puntosAtaque) {
        this.puntosAtaque = puntosAtaque;
    }

    public void setOro(double oro) {
        this.oro = oro;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    public void setPuntosVida(int vida){
        if (vida <= 0){
            this.puntosVida = 0;
            this.estaVivo = false;
        } else if (vida > this.vidaMaxima) {
            this.puntosVida = this.vidaMaxima;
            this.estaVivo = true;
        }else{
            this.puntosVida = vida;
            this.estaVivo = true;
        }
    }



    public void setNivel(int nivel){
        if(nivel >= 1 ){
            this.nivel = nivel;
        }
    }
}
