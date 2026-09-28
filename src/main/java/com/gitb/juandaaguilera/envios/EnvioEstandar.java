package com.gitb.juandaaguilera.Envios;

public class EnvioEstandar implements Envio {

    public static final double TARIFA_KG = 5000;

    private String codigo;
    private String destinatario;
    private double peso;
    private String direccion;

    public EnvioEstandar(String codigo, String destinatario, double peso, String direccion) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.peso = peso;
        this.direccion = direccion;
    }

    @Override
    public double calcularCosto() {
        return this.peso * TARIFA_KG;
    }

    @Override
    public String getCodigo() {
        return this.codigo;
    }

    @Override
    public String getDestinatario() {
        return this.destinatario;
    }

    @Override
    public double getPeso() {
        return this.peso;
    }

    @Override
    public String getTipo() {
        return "Estándar";
    }

    @Override
    public String getDatoAdicional() {
        return this.direccion;
    }

    public String getDireccion() {
        return this.direccion;
    }
}
