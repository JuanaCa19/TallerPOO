package com.taller.poo;

import java.util.Locale;

public class Principal {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Vehiculo vehiculo1 = new Vehiculo();
        Vehiculo vehiculo2 = new Vehiculo("Toyota", "Corolla", 15000.50, 180, true);
        Vehiculo vehiculo3 = new Vehiculo("Mazda", "CX-5", 32000.00, 200, false);

        Estudiante estudiante1 = new Estudiante("E001", "Laura Martínez", 4.35, true);
        Estudiante estudiante2 = new Estudiante("E002", "Pedro Ramírez", 3.80, true);
        Estudiante estudiante3 = new Estudiante("E003", "Sofía Torres", 2.90, false);

        CuentaBancaria cuenta1 = new CuentaBancaria("1001", "Carlos Pérez", 1000.00, 500.0);
        CuentaBancaria cuenta2 = new CuentaBancaria("1002", "Ana Gómez");
        CuentaBancaria cuenta3 = new CuentaBancaria();

        cuenta1.depositar(250.75);
        cuenta2.depositar(50.00);
        cuenta3.depositar(300.00);
        boolean retiroCuenta3 = cuenta3.retirar(100.00);
        boolean retiroCuenta2 = cuenta2.retirar(500.00);

        System.out.printf("=== REPORTE DE VEHÍCULOS ===%n");
        System.out.printf("[1] Marca: %s | Modelo: %s | KM: %.2f | VelMax: %d km/h | Encendido: %b%n",
                vehiculo2.getMarcaVehiculo(), vehiculo2.getModeloVehiculo(), vehiculo2.getKilometraje(),
                vehiculo2.getVelocidadMaxima(), vehiculo2.isEncendidoMotor());
        System.out.printf("[2] Marca: %s | Modelo: %s | KM: %.2f | VelMax: %d km/h | Encendido: %b%n",
                vehiculo1.getMarcaVehiculo(), vehiculo1.getModeloVehiculo(), vehiculo1.getKilometraje(),
                vehiculo1.getVelocidadMaxima(), vehiculo1.isEncendidoMotor());
        System.out.printf("[3] Marca: %s | Modelo: %s | KM: %.2f | VelMax: %d km/h | Encendido: %b%n",
                vehiculo3.getMarcaVehiculo(), vehiculo3.getModeloVehiculo(), vehiculo3.getKilometraje(),
                vehiculo3.getVelocidadMaxima(), vehiculo3.isEncendidoMotor());

        System.out.printf("%n=== REPORTE DE ESTUDIANTES ===%n");
        System.out.printf("%s%n", estudiante1.obtenerReporteAcademicos());
        System.out.printf("%s%n", estudiante2.obtenerReporteAcademicos());
        System.out.printf("%s%n", estudiante3.obtenerReporteAcademicos());

        System.out.printf("%n=== REPORTE DE CUENTAS BANCARIAS ===%n");
        System.out.printf("Cuenta: %s | Titular: %s | Saldo Final: %s | Crédito: %s%n",
                cuenta1.getNumeroCuenta(), cuenta1.getTitular(),
                ConversorMoneda.formatearMoneda(cuenta1.getSaldo()),
                ConversorMoneda.formatearMoneda(cuenta1.getLimiteCredito()));
        System.out.printf("Cuenta: %s | Titular: %s | Saldo Final: %s | Crédito: %s%n",
                cuenta2.getNumeroCuenta(), cuenta2.getTitular(),
                ConversorMoneda.formatearMoneda(cuenta2.getSaldo()),
                ConversorMoneda.formatearMoneda(cuenta2.getLimiteCredito()));
        System.out.printf("Cuenta: %s | Titular: %s | Saldo Final: %s | Crédito: %s%n",
                cuenta3.getNumeroCuenta(), cuenta3.getTitular(),
                ConversorMoneda.formatearMoneda(cuenta3.getSaldo()),
                ConversorMoneda.formatearMoneda(cuenta3.getLimiteCredito()));

        System.out.printf("%n=== OPERACIONES ===%n");
        System.out.printf("Retiro de $100.00 en cuenta %s exitoso: %b%n", cuenta3.getNumeroCuenta(), retiroCuenta3);
        System.out.printf("Retiro de $500.00 en cuenta %s exitoso: %b%n", cuenta2.getNumeroCuenta(), retiroCuenta2);

        double tasaCambio = 0.92;
        double saldoEnEuros = ConversorMoneda.convertirDolaresAEuros(cuenta1.getSaldo(), tasaCambio);
        System.out.printf("Saldo de %s en euros (tasa %.2f): %s%n", cuenta1.getTitular(), tasaCambio,
                ConversorMoneda.formatearMoneda(saldoEnEuros));
    }
}
