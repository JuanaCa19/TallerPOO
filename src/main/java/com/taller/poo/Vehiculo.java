package com.taller.poo;

public class Vehiculo {
    private String marcaVehiculo;
    private String modeloVehiculo;
    private double kilometraje;
    private int velocidadMaxima;
    private boolean encendidoMotor;

    public Vehiculo() {
    }

    public Vehiculo(boolean encendidoMotor, int velocidadMaxima, double kilometraje, String modeloVehiculo, String marcaVehiculo) {
        this.encendidoMotor = encendidoMotor;
        this.velocidadMaxima = velocidadMaxima;
        this.kilometraje = kilometraje;
        this.modeloVehiculo = modeloVehiculo;
        this.marcaVehiculo = marcaVehiculo;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(int velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public boolean isEncendidoMotor() {
        return encendidoMotor;
    }

    public void setEncendidoMotor(boolean encendidoMotor) {
        this.encendidoMotor = encendidoMotor;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "marcaVehiculo='" + marcaVehiculo + '\'' +
                ", modeloVehiculo='" + modeloVehiculo + '\'' +
                ", kilometraje=" + kilometraje +
                ", velocidadMaxima=" + velocidadMaxima +
                ", encendidoMotor=" + encendidoMotor +
                '}';
    }
}
