package co.edu.puj.rpg.modelo;

public abstract class Personaje implements ICombatiente {
    protected String nombre;
    protected int vida;
    protected int vidaMaxima;
    protected int ataqueBase;
    protected int posicionX;
    protected int posicionY;
    private int nivel;
    private double oro;
    private Arma armaEquipada;
    private Gremio gremio;
    private CorazonRPG corazon;

    public Personaje(String nombre, int vida, int ataqueBase, int posicionX, int posicionY) {
        this(nombre, vida, ataqueBase, posicionX, posicionY, 0);
    }

    public Personaje(String nombre, int vida, int ataqueBase, int posicionX, int posicionY, int manaBase) {
        this.nombre = nombre;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataqueBase = ataqueBase;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.nivel = 1;
        this.oro = 0.0;
        this.armaEquipada = null;
        this.gremio = null;
        this.corazon = new CorazonRPG(vida, manaBase);
    }

    public abstract void realizarAtaqueEspecial(Personaje objetivo);

    public abstract char getSimbolo();

    public void atacar(Personaje objetivo) {
        objetivo.defender(calcularAtaqueTotal());
    }

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
            setPuntosVida(vida - cantidad);
        }
    }

    public void curar(int cantidad) {
        if (cantidad > 0) {
            setPuntosVida(vida + cantidad);
        }
    }

    public void usarPocion(int cantidadRestaurada) {
        curar(cantidadRestaurada);
    }

    public void equiparArma(Arma nuevaArma) {
        if (nuevaArma == null) {
            System.out.println("No se puede equipar un arma nula.");
        } else {
            this.armaEquipada = nuevaArma;
            System.out.printf("%s equipó el arma %s.%n", nombre, nuevaArma.getNombre());
        }
    }

    public void desequiparArma() {
        if (this.armaEquipada != null) {
            System.out.printf("%s guardó el arma %s.%n", nombre, armaEquipada.getNombre());
            this.armaEquipada = null;
        } else {
            System.out.printf("%s no tiene un arma para retirar.%n", nombre);
        }
    }

    public int calcularAtaqueTotal() {
        int total = this.ataqueBase;
        if (this.armaEquipada != null) {
            total = total + this.armaEquipada.getDanioBase();
        }
        return total;
    }

    public void unirseAGremio(Gremio nuevoGremio) {
        if (nuevoGremio != null && nuevoGremio != this.gremio) {
            if (this.gremio != null) {
                salirDelGremio();
            }
            this.gremio = nuevoGremio;
            nuevoGremio.agregarMiembro(this);
        }
    }

    public void salirDelGremio() {
        if (this.gremio != null) {
            Gremio anterior = this.gremio;
            this.gremio = null;
            anterior.removerMiembro(this);
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

    public int getPuntosVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getAtaqueBase() {
        return ataqueBase;
    }

    public int getPuntosAtaque() {
        return ataqueBase;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public int getNivel() {
        return nivel;
    }

    public double getOro() {
        return oro;
    }

    public boolean isEstaVivo() {
        return estaVivo();
    }

    public Arma getArmaEquipada() {
        return armaEquipada;
    }

    public Gremio getGremio() {
        return gremio;
    }

    public int getSaludBase() {
        return corazon.getSaludBase();
    }

    public int getManaBase() {
        return corazon.getManaBase();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVidaMaxima(int vidaMaxima) {
        if (vidaMaxima >= 1) {
            this.vidaMaxima = vidaMaxima;
            if (this.vida > vidaMaxima) {
                this.vida = vidaMaxima;
            }
        }
    }

    public void setPuntosAtaque(int puntosAtaque) {
        if (puntosAtaque >= 0) {
            this.ataqueBase = puntosAtaque;
        }
    }

    public void setPuntosVida(int puntosVida) {
        if (puntosVida <= 0) {
            this.vida = 0;
        } else if (puntosVida > this.vidaMaxima) {
            this.vida = this.vidaMaxima;
        } else {
            this.vida = puntosVida;
        }
    }

    public void setEstaVivo(boolean estaVivo) {
        if (!estaVivo) {
            this.vida = 0;
        } else if (this.vida == 0) {
            this.vida = this.vidaMaxima;
        }
    }

    public void setOro(double oro) {
        if (oro < 0.0) {
            System.out.println("Saldo insuficiente");
        } else {
            this.oro = oro;
        }
    }

    public void setNivel(int nivel) {
        if (nivel >= 1) {
            this.nivel = nivel;
        }
    }

    public void setPosicion(int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append(" | Nivel: ").append(nivel);
        sb.append(" | ").append(corazon);
        sb.append(" | Arma: ");
        if (armaEquipada != null) {
            sb.append(armaEquipada);
        } else {
            sb.append("Desarmado");
        }
        sb.append(" | Gremio: ");
        if (gremio != null) {
            sb.append(gremio.getNombre());
        } else {
            sb.append("Sin gremio");
        }
        return sb.toString();
    }
}
