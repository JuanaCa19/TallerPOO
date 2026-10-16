package com.taller.poo;

public class Estudiante {
    private String identificacion;
    private String nombreCompleto;
    private double promedioCalificaciones;
    private boolean estaMatriculado;

    public Estudiante() {
    }

    public Estudiante(String identificacion, String nombreCompleto, double promedioCalificaciones, boolean estaMatriculado) {
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
        this.promedioCalificaciones = promedioCalificaciones;
        this.estaMatriculado = estaMatriculado;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public double getPromedioCalificaciones() {
        return promedioCalificaciones;
    }

    public void setPromedioCalificaciones(double promedioCalificaciones) {
        this.promedioCalificaciones = promedioCalificaciones;
    }

    public boolean isEstaMatriculado() {
        return estaMatriculado;
    }

    public void setEstaMatriculado(boolean estaMatriculado) {
        this.estaMatriculado = estaMatriculado;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "identificacion='" + identificacion + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", promedioCalificaciones=" + promedioCalificaciones +
                ", estaMatriculado=" + estaMatriculado +
                '}';
    }

}
