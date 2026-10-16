package com.taller.poo;

public class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private Double limiteCredito;

    public CuentaBancaria() {
        this.numeroCuenta = "000000";
        this.titular = "Sin Nombre";
        this.saldo = 0.0;
        this.limiteCredito = 0.0;
    }

    public CuentaBancaria(String numeroCuenta, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = 0.0;
        this.limiteCredito = 100.0;
    }

    public CuentaBancaria(String numeroCuenta, String titular, double saldo, Double limiteCredito) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
        this.limiteCredito = limiteCredito;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(Double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public boolean depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            return true;
        } else {
            return false;
        }
    }

    public boolean retirar(double monto) {
        double disponible = this.saldo;
        if (this.limiteCredito != null) {
            disponible += this.limiteCredito;
        }
        if (monto > 0 && monto <= disponible) {
            this.saldo -= monto;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", titular='" + titular + '\'' +
                ", saldo=" + saldo +
                ", limiteCredito=" + limiteCredito +
                '}';
    }

    /*
     * PREGUNTAS DE ANÁLISIS:
     * 1. ¿Qué diferencia ocurre a nivel de memoria y asignación por defecto al
     * declarar 'saldo' como double primitivo frente a 'limiteCredito' como Wrapper
     * Double?
     * R: 'saldo' es un primitivo: guarda el número directamente (8 bytes) y, si no se
     * inicializa, vale 0.0 por defecto. 'limiteCredito' es un objeto Double: en la
     * variable solo se guarda una referencia a un objeto en el heap (usa más memoria) y,
     * si no se inicializa, vale null porque aún no apunta a ningún objeto.
     * 2. ¿Por qué una operación aritmética sobre un atributo no inicializado de
     * tipo Double provoca una NullPointerException, mientras que sobre un double
     * primitivo opera con 0.0?
     * R: Para operar con un Double Java hace unboxing, es decir, llama internamente a
     * doubleValue() sobre el objeto. Si el atributo es null no hay objeto sobre el cual
     * llamar ese método y se lanza NullPointerException. El double primitivo nunca es
     * null: ya tiene el valor 0.0, por eso la operación se realiza sin problema.
     */
}
