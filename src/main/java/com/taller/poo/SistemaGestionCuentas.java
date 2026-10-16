package com.taller.poo;

import java.util.Locale;
import java.util.Scanner;

public class SistemaGestionCuentas {

    private static CuentaBancaria buscarCuenta(CuentaBancaria[] cuentas, String numeroCuenta) {
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i].getNumeroCuenta().equals(numeroCuenta)) {
                return cuentas[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        CuentaBancaria[] cuentas = new CuentaBancaria[3];
        cuentas[0] = new CuentaBancaria("1001", "Carlos Pérez", 1000.00, 500.0);
        cuentas[1] = new CuentaBancaria("1002", "Ana Gómez");
        cuentas[2] = new CuentaBancaria("1003", "Luis Rojas", 200.00, 300.0);

        int opcion;
        do {
            System.out.printf("%n=== SISTEMA DE GESTIÓN DE CUENTAS ===%n");
            System.out.printf("1. Depositar%n");
            System.out.printf("2. Retirar%n");
            System.out.printf("3. Ver reporte consolidado%n");
            System.out.printf("4. Salir%n");
            System.out.printf("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.printf("Número de cuenta: ");
                    String cuentaDeposito = scanner.next();
                    System.out.printf("Monto a depositar: ");
                    double montoDeposito = scanner.nextDouble();
                    CuentaBancaria destino = buscarCuenta(cuentas, cuentaDeposito);
                    if (destino == null) {
                        System.out.printf("Error: la cuenta %s no existe.%n", cuentaDeposito);
                    } else if (montoDeposito <= 0) {
                        System.out.printf("Error: el monto debe ser mayor a cero.%n");
                    } else {
                        destino.depositar(montoDeposito);
                        System.out.printf("Depósito exitoso. Nuevo saldo: %s%n",
                                ConversorMoneda.formatearMoneda(destino.getSaldo()));
                    }
                    break;
                case 2:
                    System.out.printf("Número de cuenta: ");
                    String cuentaRetiro = scanner.next();
                    System.out.printf("Monto a retirar: ");
                    double montoRetiro = scanner.nextDouble();
                    CuentaBancaria origen = buscarCuenta(cuentas, cuentaRetiro);
                    if (origen == null) {
                        System.out.printf("Error: la cuenta %s no existe.%n", cuentaRetiro);
                    } else if (montoRetiro <= 0) {
                        System.out.printf("Error: el monto debe ser mayor a cero.%n");
                    } else if (origen.retirar(montoRetiro)) {
                        System.out.printf("Retiro exitoso. Nuevo saldo: %s%n",
                                ConversorMoneda.formatearMoneda(origen.getSaldo()));
                    } else {
                        System.out.printf("Error: fondos insuficientes.%n");
                    }
                    break;
                case 3:
                    StringBuilder reporte = new StringBuilder();
                    double saldoTotal = 0.0;
                    for (int i = 0; i < cuentas.length; i++) {
                        reporte.append(String.format("Cuenta: %s | Titular: %s | Saldo: %.2f%n",
                                cuentas[i].getNumeroCuenta(), cuentas[i].getTitular(), cuentas[i].getSaldo()));
                        saldoTotal += cuentas[i].getSaldo();
                    }
                    System.out.printf("%n=== REPORTE CONSOLIDADO ===%n");
                    System.out.printf("%s", reporte);
                    System.out.printf("Saldo total acumulado: %.2f%n", saldoTotal);
                    break;
                case 4:
                    System.out.printf("Saliendo del sistema...%n");
                    break;
                default:
                    System.out.printf("Opción no válida.%n");
            }
        } while (opcion != 4);

        scanner.close();
    }
}
