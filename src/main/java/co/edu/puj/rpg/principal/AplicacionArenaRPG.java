package co.edu.puj.rpg.principal;

import co.edu.puj.rpg.modelo.Arma;
import co.edu.puj.rpg.modelo.Escuadron;
import co.edu.puj.rpg.modelo.Gremio;
import co.edu.puj.rpg.modelo.Guerrero;
import co.edu.puj.rpg.modelo.Mago;
import co.edu.puj.rpg.modelo.Personaje;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AplicacionArenaRPG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        List<Personaje> personajes = new ArrayList<>();
        List<Gremio> gremios = new ArrayList<>();
        List<Escuadron> escuadrones = new ArrayList<>();

        do {
            System.out.println("\n=== MENÚ ARENA DE HÉROES RPG ===");
            System.out.println("1. Crear Personaje");
            System.out.println("2. Crear Gremio");
            System.out.println("3. Reclutar Personaje a Escuadrón");
            System.out.println("4. Equipar Arma a Personaje");
            System.out.println("5. Mostrar Estado Detallado (Personaje y Corazón RPG)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1 -> crearPersonaje(scanner, personajes);
                case 2 -> crearGremio(scanner, personajes, gremios);
                case 3 -> gestionarEscuadron(scanner, personajes, escuadrones);
                case 4 -> gestionarArma(scanner, personajes);
                case 5 -> mostrarEstado(personajes, gremios, escuadrones);
                case 6 -> System.out.println("Cerrando la Arena de Héroes...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }

    static int leerEntero(Scanner scanner) {
        String entrada = scanner.nextLine().trim();
        if (entrada.matches("[0-9]{1,6}")) {
            return Integer.parseInt(entrada);
        }
        return -1;
    }

    static String leerTexto(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    static Personaje elegirPersonaje(Scanner scanner, List<Personaje> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay personajes disponibles.");
            return null;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, lista.get(i).getNombre());
        }
        System.out.print("Seleccione el personaje: ");
        int eleccion = leerEntero(scanner);
        if (eleccion < 1 || eleccion > lista.size()) {
            System.out.println("Selección inválida.");
            return null;
        }
        return lista.get(eleccion - 1);
    }

    static Escuadron buscarEscuadron(List<Escuadron> escuadrones, String nombre) {
        for (Escuadron e : escuadrones) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    static void crearPersonaje(Scanner scanner, List<Personaje> personajes) {
        String nombre = leerTexto(scanner, "Nombre del personaje: ");
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        System.out.print("Nivel del personaje (mínimo 1): ");
        int nivel = leerEntero(scanner);
        if (nivel < 1) {
            System.out.println("Nivel inválido.");
            return;
        }
        System.out.println("1. Guerrero");
        System.out.println("2. Mago");
        System.out.print("Clase del personaje: ");
        int tipo = leerEntero(scanner);
        Personaje nuevo;
        if (tipo == 1) {
            nuevo = new Guerrero(nombre, 100 + nivel * 20, nivel * 5, 0, 0, 10);
        } else if (tipo == 2) {
            nuevo = new Mago(nombre, 100 + nivel * 20, nivel * 5, 0, 0, 50 + nivel * 10);
        } else {
            System.out.println("Clase inválida.");
            return;
        }
        nuevo.setNivel(nivel);
        personajes.add(nuevo);
        System.out.println("Personaje creado: " + nuevo);
    }

    static void crearGremio(Scanner scanner, List<Personaje> personajes, List<Gremio> gremios) {
        String nombre = leerTexto(scanner, "Nombre del gremio: ");
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        Gremio nuevo = new Gremio(nombre);
        gremios.add(nuevo);
        System.out.println("Gremio creado: " + nuevo);

        boolean seguir = true;
        while (seguir && !personajes.isEmpty()) {
            String respuesta = leerTexto(scanner, "¿Inscribir un personaje en el gremio? (s/n): ");
            if (respuesta.equalsIgnoreCase("s")) {
                Personaje p = elegirPersonaje(scanner, personajes);
                if (p != null) {
                    p.unirseAGremio(nuevo);
                    System.out.printf("%s ahora pertenece al gremio %s.%n", p.getNombre(), nuevo.getNombre());
                }
            } else {
                seguir = false;
            }
        }
    }

    static void gestionarEscuadron(Scanner scanner, List<Personaje> personajes, List<Escuadron> escuadrones) {
        String nombre = leerTexto(scanner, "Nombre del escuadrón: ");
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        Escuadron escuadron = buscarEscuadron(escuadrones, nombre);
        if (escuadron == null) {
            escuadron = new Escuadron(nombre);
            escuadrones.add(escuadron);
            System.out.println("Escuadrón creado: " + nombre);
        }
        System.out.println("1. Reclutar personaje");
        System.out.println("2. Expulsar personaje");
        System.out.print("Seleccione una opción: ");
        int opcion = leerEntero(scanner);
        if (opcion == 1) {
            Personaje p = elegirPersonaje(scanner, personajes);
            if (p != null) {
                escuadron.reclutarPersonaje(p);
            }
        } else if (opcion == 2) {
            Personaje p = elegirPersonaje(scanner, escuadron.getMiembros());
            if (p != null) {
                escuadron.expulsarPersonaje(p);
            }
        } else {
            System.out.println("Opción inválida.");
        }
    }

    static void gestionarArma(Scanner scanner, List<Personaje> personajes) {
        Personaje p = elegirPersonaje(scanner, personajes);
        if (p == null) {
            return;
        }
        System.out.println("1. Equipar nueva arma");
        System.out.println("2. Desequipar arma");
        System.out.print("Seleccione una opción: ");
        int opcion = leerEntero(scanner);
        if (opcion == 1) {
            String nombreArma = leerTexto(scanner, "Nombre del arma: ");
            if (nombreArma.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
                return;
            }
            System.out.print("Daño base del arma (mínimo 1): ");
            int danio = leerEntero(scanner);
            if (danio < 1) {
                System.out.println("Daño inválido.");
                return;
            }
            p.equiparArma(new Arma(nombreArma, danio));
        } else if (opcion == 2) {
            p.desequiparArma();
        } else {
            System.out.println("Opción inválida.");
        }
    }

    static void mostrarEstado(List<Personaje> personajes, List<Gremio> gremios, List<Escuadron> escuadrones) {
        System.out.println("\n--- PERSONAJES ---");
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes creados.");
        } else {
            System.out.printf("%-12s %5s %7s %6s %-16s %7s %-12s%n", "Nombre", "Nivel", "Salud", "Maná", "Arma", "Ataque", "Gremio");
            for (Personaje p : personajes) {
                String nombreArma = "Desarmado";
                if (p.getArmaEquipada() != null) {
                    nombreArma = p.getArmaEquipada().getNombre();
                }
                String nombreGremio = "Sin gremio";
                if (p.getGremio() != null) {
                    nombreGremio = p.getGremio().getNombre();
                }
                System.out.printf("%-12s %5d %7d %6d %-16s %7d %-12s%n", p.getNombre(), p.getNivel(), p.getSaludBase(), p.getManaBase(), nombreArma, p.calcularAtaqueTotal(), nombreGremio);
            }
        }

        System.out.println("\n--- GREMIOS ---");
        if (gremios.isEmpty()) {
            System.out.println("No hay gremios creados.");
        }
        for (Gremio g : gremios) {
            System.out.println(g);
        }

        System.out.println("\n--- ESCUADRONES ---");
        if (escuadrones.isEmpty()) {
            System.out.println("No hay escuadrones creados.");
        }
        for (Escuadron e : escuadrones) {
            System.out.println(e);
        }
    }
}
