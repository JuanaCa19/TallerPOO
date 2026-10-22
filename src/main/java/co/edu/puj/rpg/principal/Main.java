package co.edu.puj.rpg.principal;

import co.edu.puj.rpg.modelo.*;
import co.edu.puj.rpg.modelo.enemigo.Enemigo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static final int TAM = 5;
    static final int COFRE_X = 2;
    static final int COFRE_Y = 2;

    public static void main(String[] args) {

        Scanner consola = new Scanner(System.in);

        char[][] tablero = new char[TAM][TAM];

        List<Personaje> heroes = new ArrayList<>();
        List<Personaje> enemigos = new ArrayList<>();
        Inventario<Pocion> inventario = new Inventario<>();

        heroes.add(new Guerrero("Thorin", 120, 15, 0, 0, 10));
        heroes.add(new Mago("Gandalf", 80, 25, 0, 1, 50));

        enemigos.add(new Enemigo("Orco", 50, 12, 4, 0));
        enemigos.add(new Enemigo("Trasgo", 40, 10, 0, 4));
        enemigos.add(new Enemigo("Troll", 70, 15, 4, 4));

        inventario.agregar(new Pocion("Poción Menor", 30));
        inventario.agregar(new Pocion("Poción Menor", 30));
        inventario.agregar(new Pocion("Poción Mayor", 50));

        Personaje principal = heroes.get(0);

        boolean cofreAlcanzado = false;
        boolean salir = false;

        do {

            dibujar(tablero, heroes, enemigos);
            mostrarEstado(heroes, enemigos);

            for (Personaje heroe : heroes) {

                if (heroe.estaVivo() && contarVivos(enemigos) > 0 && !cofreAlcanzado && !salir) {

                    boolean turnoTerminado = false;

                    while (!turnoTerminado) {

                        System.out.printf("%n--- Turno de %s ---%n", heroe.getNombre());
                        System.out.print("""
                                1. Atacar
                                2. Usar Inventario
                                3. Mover en Tablero
                                4. Salir
                                Ingrese una opción: """);

                        int op = leerOpcion(consola);

                        switch (op) {
                            case 1 -> {
                                if (atacar(consola, heroe, enemigos)) {
                                    turnoTerminado = true;
                                }
                            }
                            case 2 -> {
                                if (usarInventario(consola, heroe, inventario)) {
                                    turnoTerminado = true;
                                }
                            }
                            case 3 -> {
                                if (mover(consola, heroe, heroes, enemigos)) {
                                    turnoTerminado = true;
                                    if (heroe == principal && heroe.getPosicionX() == COFRE_X && heroe.getPosicionY() == COFRE_Y) {
                                        cofreAlcanzado = true;
                                    }
                                }
                            }
                            case 4 -> {
                                salir = true;
                                turnoTerminado = true;
                            }
                            default -> System.out.println("Opción Invalida!!!");
                        }
                    }

                    dibujar(tablero, heroes, enemigos);
                }
            }

            if (contarVivos(enemigos) > 0 && !cofreAlcanzado && !salir) {
                turnoEnemigos(heroes, enemigos);
            }

            limpiarBajas(heroes);
            limpiarBajas(enemigos);

        } while (!heroes.isEmpty() && !enemigos.isEmpty() && !cofreAlcanzado && !salir);

        System.out.println();
        if (cofreAlcanzado) {
            System.out.printf("¡Victoria! %s alcanzó el cofre del tesoro.%n", principal.getNombre());
        } else if (enemigos.isEmpty()) {
            System.out.println("¡Victoria! Todos los enemigos fueron eliminados.");
        } else if (heroes.isEmpty()) {
            System.out.println("Derrota. Todos los héroes han caído.");
        } else {
            System.out.println("Partida terminada.");
        }
    }

    static int leerOpcion(Scanner consola) {
        String entrada = consola.nextLine().trim();
        if (entrada.length() == 1 && Character.isDigit(entrada.charAt(0))) {
            return Integer.parseInt(entrada);
        }
        return -1;
    }

    static void mostrarTablero(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "]");
            }
            System.out.println();
        }
    }

    static void actualizarTablero(char[][] tablero, List<Personaje> heroes, List<Personaje> enemigos) {
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                tablero[i][j] = '.';
            }
        }
        tablero[COFRE_Y][COFRE_X] = 'C';
        for (Personaje enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                tablero[enemigo.getPosicionY()][enemigo.getPosicionX()] = enemigo.getSimbolo();
            }
        }
        for (Personaje heroe : heroes) {
            if (heroe.estaVivo()) {
                tablero[heroe.getPosicionY()][heroe.getPosicionX()] = heroe.getSimbolo();
            }
        }
    }

    static void dibujar(char[][] tablero, List<Personaje> heroes, List<Personaje> enemigos) {
        actualizarTablero(tablero, heroes, enemigos);
        System.out.println();
        mostrarTablero(tablero);
    }

    static void mostrarEstado(List<Personaje> heroes, List<Personaje> enemigos) {
        System.out.println("--- HÉROES ---");
        for (Personaje heroe : heroes) {
            if (heroe.estaVivo()) {
                System.out.println(heroe.getEstado());
            }
        }
        System.out.println("--- ENEMIGOS ---");
        for (Personaje enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                System.out.println(enemigo.getEstado());
            }
        }
    }

    static int contarVivos(List<Personaje> lista) {
        int vivos = 0;
        for (Personaje p : lista) {
            if (p.estaVivo()) {
                vivos++;
            }
        }
        return vivos;
    }

    static boolean atacar(Scanner consola, Personaje heroe, List<Personaje> enemigos) {
        System.out.println("Enemigos disponibles:");
        for (int i = 0; i < enemigos.size(); i++) {
            Personaje enemigo = enemigos.get(i);
            if (enemigo.estaVivo()) {
                System.out.println((i + 1) + ". " + enemigo.getEstado());
            }
        }
        System.out.print("Elige el objetivo: ");
        int eleccion = leerOpcion(consola);
        if (eleccion < 1 || eleccion > enemigos.size() || !enemigos.get(eleccion - 1).estaVivo()) {
            System.out.println("Objetivo inválido.");
            return false;
        }
        heroe.realizarAtaqueEspecial(enemigos.get(eleccion - 1));
        return true;
    }

    static boolean usarInventario(Scanner consola, Personaje heroe, Inventario<Pocion> inventario) {
        if (inventario.estaVacio()) {
            System.out.println("No quedan pociones.");
            return false;
        }
        if (heroe.getVida() == heroe.getVidaMaxima()) {
            System.out.printf("%s ya tiene la vida completa.%n", heroe.getNombre());
            return false;
        }
        System.out.println("Pociones disponibles:");
        for (int i = 0; i < inventario.tamano(); i++) {
            Pocion p = inventario.obtener(i);
            System.out.printf("%d. %s (+%d de vida)%n", i + 1, p.getNombre(), p.getCura());
        }
        System.out.print("Elige la poción: ");
        int eleccion = leerOpcion(consola);
        if (eleccion < 1 || eleccion > inventario.tamano()) {
            System.out.println("Poción inválida.");
            return false;
        }
        Pocion pocion = inventario.obtener(eleccion - 1);
        heroe.curar(pocion.getCura());
        inventario.remover(eleccion - 1);
        System.out.printf("%s usa %s. Vida: %d/%d%n", heroe.getNombre(), pocion.getNombre(), heroe.getVida(), heroe.getVidaMaxima());
        return true;
    }

    static boolean celdaOcupada(int x, int y, List<Personaje> heroes, List<Personaje> enemigos) {
        for (Personaje heroe : heroes) {
            if (heroe.estaVivo() && heroe.getPosicionX() == x && heroe.getPosicionY() == y) {
                return true;
            }
        }
        for (Personaje enemigo : enemigos) {
            if (enemigo.estaVivo() && enemigo.getPosicionX() == x && enemigo.getPosicionY() == y) {
                return true;
            }
        }
        return false;
    }

    static boolean mover(Scanner consola, Personaje heroe, List<Personaje> heroes, List<Personaje> enemigos) {
        System.out.print("Dirección (W arriba, S abajo, A izquierda, D derecha): ");
        String entrada = consola.nextLine().trim().toUpperCase();
        int nuevoX = heroe.getPosicionX();
        int nuevoY = heroe.getPosicionY();
        if (entrada.equals("W")) {
            nuevoY--;
        } else if (entrada.equals("S")) {
            nuevoY++;
        } else if (entrada.equals("A")) {
            nuevoX--;
        } else if (entrada.equals("D")) {
            nuevoX++;
        } else {
            System.out.println("Dirección inválida.");
            return false;
        }
        if (nuevoX < 0 || nuevoX >= TAM || nuevoY < 0 || nuevoY >= TAM) {
            System.out.println("Movimiento fuera del tablero.");
            return false;
        }
        if (celdaOcupada(nuevoX, nuevoY, heroes, enemigos)) {
            System.out.println("La celda está ocupada.");
            return false;
        }
        heroe.setPosicion(nuevoX, nuevoY);
        System.out.printf("%s se mueve a la posición (%d, %d)%n", heroe.getNombre(), nuevoX, nuevoY);
        return true;
    }

    static Personaje elegirVivo(List<Personaje> lista, Random azar) {
        Personaje elegido = lista.get(azar.nextInt(lista.size()));
        while (!elegido.estaVivo()) {
            elegido = lista.get(azar.nextInt(lista.size()));
        }
        return elegido;
    }

    static void turnoEnemigos(List<Personaje> heroes, List<Personaje> enemigos) {
        Random azar = new Random();
        System.out.println();
        System.out.println("--- Turno enemigo ---");
        for (Personaje enemigo : enemigos) {
            if (enemigo.estaVivo() && contarVivos(heroes) > 0) {
                Personaje objetivo = elegirVivo(heroes, azar);
                enemigo.realizarAtaqueEspecial(objetivo);
            }
        }
    }

    static void limpiarBajas(List<Personaje> lista) {
        for (int i = lista.size() - 1; i >= 0; i--) {
            if (!lista.get(i).estaVivo()) {
                lista.remove(i);
            }
        }
    }
}
