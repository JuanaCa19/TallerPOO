package co.edu.puj.rpg.principal;

import co.edu.puj.rpg.modelo.Personaje;
import co.edu.puj.rpg.modelo.enemigo.Enemigo;

import java.util.Scanner;

public class Main {

    static final int CURA_POCION = 50;
    static final double COSTO_POCION = 25.0;

    public static void main(String[] args) {

        Scanner consola = new Scanner(System.in);

        int op = 0;
        int pociones = 1;
        boolean huir = false;

        StringBuilder historial = new StringBuilder();

        Personaje heroe = new Personaje("Aragorn", 100, 15, 50.0);

        heroe.setEstaVivo(true);

        Enemigo enemigo = new Enemigo("Monuel",100,12,0);

        enemigo.setEstaVivo(true);

        do {

            System.out.print("""
                    ==========MENÚ==========
                    1. Atacar
                    2. Usar Poción
                    3. Ver Estado
                    4. Huir
                    5. Tienda
                    Ingrese una opción: """);

            String entrada = consola.nextLine();

            if (entrada.length() == 1 && Character.isDigit(entrada.charAt(0))) {
                op = Integer.parseInt(entrada);
            } else {
                op = 0;
            }

            switch (op){
                case 1 ->{
                    int vidaAntes = enemigo.getPuntosVida();
                    heroe.atacar(enemigo);
                    int dano = vidaAntes - enemigo.getPuntosVida();
                    historial.append(heroe.getNombre()).append(" ataca a ").append(enemigo.getNombre()).append(" y causa ").append(dano).append(" de daño.\n");
                    if (enemigo.isEstaVivo()) {
                        turnoEnemigo(heroe, enemigo, historial);
                    } else {
                        historial.append(enemigo.getNombre()).append(" ha sido derrotado.\n");
                    }
                }
                case 2 ->{
                    if (pociones <= 0) {
                        System.out.println("No tienes pociones. Compra una en la tienda.");
                    } else if (heroe.getPuntosVida() == heroe.getVidaMaxima()) {
                        System.out.println("Tu vida ya está completa.");
                    } else {
                        int vidaAntes = heroe.getPuntosVida();
                        heroe.usarPocion(CURA_POCION);
                        pociones--;
                        int curado = heroe.getPuntosVida() - vidaAntes;
                        historial.append(heroe.getNombre()).append(" usa una poción y recupera ").append(curado).append(" de vida.\n");
                        turnoEnemigo(heroe, enemigo, historial);
                    }
                }
                case 3 ->{
                    mostrarEstado(heroe, enemigo, pociones);
                }
                case 4 ->{
                    huir = true;
                    historial.append(heroe.getNombre()).append(" huye del combate.\n");
                }
                case 5 ->{
                    int compradas = tienda(consola, heroe);
                    if (compradas > 0) {
                        pociones = pociones + compradas;
                        historial.append(heroe.getNombre()).append(" compra una Poción Grande de Salud.\n");
                    }
                }
                default -> System.out.println("Opción Invalida!!!");
            }

            if (op == 1 || op == 2) {
                System.out.println("====== HISTORIAL DE BATALLA ======");
                System.out.print(historial.toString());
                System.out.println("==================================");
            }

        }while(heroe.isEstaVivo() && enemigo.isEstaVivo() && !huir);

        if (!heroe.isEstaVivo()) {
            System.out.printf("%s ha sido derrotado. Fin del juego.%n", heroe.getNombre());
        } else if (!enemigo.isEstaVivo()) {
            System.out.printf("¡Victoria! %s ha derrotado a %s.%n", heroe.getNombre(), enemigo.getNombre());
        } else {
            System.out.println("Has huido del combate.");
        }
    }

    static void turnoEnemigo(Personaje heroe, Enemigo enemigo, StringBuilder historial) {
        int vidaAntes = heroe.getPuntosVida();
        enemigo.atacar(heroe);
        int dano = vidaAntes - heroe.getPuntosVida();
        historial.append(enemigo.getNombre()).append(" ataca a ").append(heroe.getNombre()).append(" y causa ").append(dano).append(" de daño.\n");
        if (!heroe.isEstaVivo()) {
            historial.append(heroe.getNombre()).append(" ha caído en batalla.\n");
        }
    }

    static void mostrarEstado(Personaje heroe, Enemigo enemigo, int pociones) {
        System.out.println("========== ESTADO ==========");
        System.out.printf("Héroe: %s | Nivel: %d%n", heroe.getNombre(), heroe.getNivel());
        System.out.printf("Vida: %d/%d | Ataque: %d%n", heroe.getPuntosVida(), heroe.getVidaMaxima(), heroe.getPuntosAtaque());
        System.out.printf("Oro: %.2f | Pociones: %d%n", heroe.getOro(), pociones);
        System.out.printf("Enemigo: %s | Nivel: %d%n", enemigo.getNombre(), enemigo.getNivel());
        System.out.printf("Vida: %d/%d | Ataque: %d%n", enemigo.getPuntosVida(), enemigo.getVidaMaxima(), enemigo.getPuntosAtaque());
        System.out.println("============================");
    }

    static int tienda(Scanner consola, Personaje heroe) {
        System.out.println("=========== TIENDA ===========");
        System.out.printf("Tu oro: %.2f%n", heroe.getOro());
        System.out.printf("1. Poción Grande de Salud (cura %d) - %.2f de oro%n", CURA_POCION, COSTO_POCION);
        System.out.println("2. Volver");
        System.out.print("Ingrese una opción: ");

        String entrada = consola.nextLine();

        if (entrada.equals("1")) {
            double oroAntes = heroe.getOro();
            double nuevoSaldo = oroAntes - COSTO_POCION;
            heroe.setOro(nuevoSaldo);
            if (heroe.getOro() != oroAntes) {
                System.out.printf("Compra exitosa. Oro restante: %.2f%n", heroe.getOro());
                return 1;
            } else {
                System.out.println("Compra rechazada.");
                return 0;
            }
        } else if (entrada.equals("2")) {
            return 0;
        } else {
            System.out.println("Opción Invalida!!!");
            return 0;
        }
    }
}
