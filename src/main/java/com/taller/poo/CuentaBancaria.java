package com.taller.poo;

public class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private Double limiteCredito;

    public CuentaBancaria() {
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

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", titular='" + titular + '\'' +
                ", saldo=" + saldo +
                ", limiteCredito=" + limiteCredito +
                '}';
    }

    public boolean depositar(double monto){
        if(monto > 0){
            this.saldo += monto;
            return true;
        }else{
            return false;
        }
    }
    public boolean retirar(double monto){
        if (monto > 0 && monto <= this.saldo+this.limiteCredito){
            this.saldo -= monto;
            return true;
        }else{
            return false;
        }
    }
/*
        * PREGUNTAS DE ANÁLISIS:
        * 1. ¿Qué diferencia ocurre a nivel de memoria y asignación por defecto al
        declarar 'saldo' como double primitivo frente a 'limiteCredito' como Wrapper
        Double?
        * 2. ¿Por qué una operación aritmética sobre un atributo no inicializado de
        tipo Double provoca una NullPointerException, mientras que sobre un double
        primitivo opera con 0.0?
    */
}
