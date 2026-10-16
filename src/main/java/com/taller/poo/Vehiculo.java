package com.taller.poo;

/*
 * Al usar Wrappers (Double, Integer, Boolean) en lugar de primitivos, cada valor es un
 * objeto en el heap: ocupa más memoria (cabecera del objeto + referencia) y es más lento
 * que un primitivo, que se guarda directamente. A cambio, un Wrapper admite el valor null,
 * lo que permite representar "dato desconocido o no asignado". Un primitivo nunca puede
 * ser null: si no se inicializa toma un valor por defecto (0, 0.0 o false).
 */
public class Vehiculo {
    private String marcaVehiculo;
    private String modeloVehiculo;
    // double primitivo: el kilometraje siempre tiene valor (empieza en 0.0) y ahorra memoria.
    private double kilometraje;
    // int primitivo: la velocidad máxima siempre es un número definido, nunca debe ser null.
    private int velocidadMaxima;
    // boolean primitivo: el motor solo está encendido o apagado, no existe un tercer estado.
    private boolean encendidoMotor;

    public Vehiculo() {
        this.marcaVehiculo = "Genérico";
        this.modeloVehiculo = "Sin Modelo";
        this.kilometraje = 0.0;
        this.velocidadMaxima = 120;
        this.encendidoMotor = false;
    }

    public Vehiculo(String marcaVehiculo, String modeloVehiculo, double kilometraje, int velocidadMaxima, boolean encendidoMotor) {
        this.marcaVehiculo = marcaVehiculo;
        this.modeloVehiculo = modeloVehiculo;
        this.kilometraje = kilometraje;
        this.velocidadMaxima = velocidadMaxima;
        this.encendidoMotor = encendidoMotor;
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
